package carlos.nicolau.galves.login_mvvm_c.framework.service

import carlos.nicolau.galves.login_mvvm_c.framework.utils.Assets
import okhttp3.*

class ServerMockInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val uri = chain.request().url().uri().toString()

        val method = chain.request().method()

        val responseString : String  = when {
            uri.contains("/login/auth") -> getUser
            else->""
        }

        return chain.proceed(chain.request())
            .newBuilder()
            .code(200)
            .protocol(Protocol.HTTP_2)
            .message(responseString)
            .body(
                ResponseBody.create(
                    MediaType.parse("application/json"),
                    responseString.toByteArray()))
            .addHeader("content-type", "application/json")
            .build()
    }

    val getUser = Assets.readJsonFile("create_auth.json")
}