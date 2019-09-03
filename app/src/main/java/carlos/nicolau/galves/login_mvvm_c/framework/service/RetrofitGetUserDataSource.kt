package carlos.nicolau.galves.login_mvvm_c.framework.service

import carlos.nicolau.galves.core.data.GetUserDataSource
import carlos.nicolau.galves.login_mvpc.domain.model.login.GetUserResponse
import carlos.nicolau.galves.login_mvpc.domain.model.login.User
import carlos.nicolau.galves.login_mvvm_c.framework.Service
import carlos.nicolau.galves.login_mvvm_c.framework.service.GenericApi.createApi
import io.reactivex.Single

class RetrofitGetUserDataSource : GetUserDataSource{

    val service: Service = createApi(Service::class.java)

    override fun execute(user: User): Single<GetUserResponse> {
        return service.getUser(user)
            .map {
                val objectReturn = Object()

                it.status = true
                it
            }

    }
}