package carlos.nicolau.galves.login_mvvm_c.framework.service

import carlos.nicolau.galves.login_mvvm_c.BuildConfig
import carlos.nicolau.galves.login_mvvm_c.framework.Service
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object GenericApi {

    fun <T> createApi(endpointApi: Class<T>): T {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor(logging)

        isMockFlavorSelected()?.let {
            httpClient.addInterceptor(it)
        }

        val gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.URL_API)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build())
            .build()

        return retrofit.create(endpointApi)
    }

    private fun isMockFlavorSelected(): Interceptor? {
        // debug mock flavor selected alway true but release not
        if (BuildConfig.BUILD_TYPE + BuildConfig.FLAVOR == "debugmock") {
            return ServerMockInterceptor()
        }

        return null
    }

    fun <T> createApiMock(endpointApi: Class<T>): T {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor(logging)

        isMockFlavorSelected()?.let {
            httpClient.addInterceptor(it)
        }

        val gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.URL_API)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build())
            .build()

        return retrofit.create(endpointApi)
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