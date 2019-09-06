package carlos.nicolau.galves.login_mvvm_c.framework.service

import android.util.Log
import carlos.nicolau.galves.core.data.GetUserDataSource
import carlos.nicolau.galves.login_mvpc.domain.model.login.GetUserResponse
import carlos.nicolau.galves.login_mvpc.domain.model.login.User
import carlos.nicolau.galves.login_mvvm_c.framework.Service
import carlos.nicolau.galves.login_mvvm_c.framework.ServiceMock
import carlos.nicolau.galves.login_mvvm_c.framework.service.GenericApi.createApi
import carlos.nicolau.galves.login_mvvm_c.framework.service.GenericApi.createApiMock
import io.reactivex.Single

class RetrofitGetUserDataSource : GetUserDataSource{

    val service: ServiceMock = createApiMock(ServiceMock::class.java)

    override fun execute(user: User): Single<GetUserResponse> {
        return service.getUser(user)
            .map {
                val objectReturn = GetUserResponse(it.status, it.message)
                Log.d("Chaaaaene", objectReturn.toString())
                it.status = true
                it
            }

    }
}