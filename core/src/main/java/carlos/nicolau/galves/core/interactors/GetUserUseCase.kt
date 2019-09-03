//package carlos.nicolau.galves.login_mvpc.domain.usecase
//
//import carlos.nicolau.galves.login_mvpc.data.service.Service
//import carlos.nicolau.galves.login_mvpc.domain.model.login.GetUserResponse
//import carlos.nicolau.galves.login_mvpc.domain.model.login.User
//import io.reactivex.Single
//
//class GetUserUseCase(private val service: Service) : IGetUserUseCase {
//    var running = false
//    override fun execute(user: User): Single<GetUserResponse> {
//        running = true
//
//        return service.getUser(user)
//            .map {
//                val objectReturn = Object()
//
//                it.status = true
//                running = false
//                it
//            }
//
//    }
//
//    override fun IsRunning(): Boolean {
//        return running
//    }
//}