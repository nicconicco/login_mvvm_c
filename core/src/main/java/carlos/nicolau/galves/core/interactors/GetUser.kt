package carlos.nicolau.galves.core.interactors

import carlos.nicolau.galves.core.data.GetUserRepository
import carlos.nicolau.galves.login_mvpc.domain.model.login.User

class GetUser(private val getUserRepository: GetUserRepository) {
    operator fun invoke(user: User) =
        getUserRepository.execute(user)
}