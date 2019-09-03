package carlos.nicolau.galves.login_mvvm_c.framework.service

import carlos.nicolau.galves.login_mvvm_c.BuildConfig
import carlos.nicolau.galves.login_mvvm_c.framework.Service
import com.google.gson.GsonBuilder
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitConfig {
    private var retrofit: Retrofit

    init {

        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val client: OkHttpClient = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()

        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    fun getServiceRX(): Service {
        val client: OkHttpClient.Builder =
            when {
                BuildConfig.BUILD_TYPE + BuildConfig.FLAVOR == "debugmock" -> OkHttpClient.Builder()
                    .addInterceptor(ServerMockInterceptor())
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                else -> OkHttpClient.Builder()
                    .addInterceptor(ServerMockInterceptor())
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
            }
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd").create()

        val build = Retrofit.Builder()
            .baseUrl(BuildConfig.URL_API)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client.build())
            .build()

        return build.create(Service::class.java)
    }
}