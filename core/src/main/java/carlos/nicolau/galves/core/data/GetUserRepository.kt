package carlos.nicolau.galves.core.data

import carlos.nicolau.galves.login_mvpc.domain.model.login.User

class GetUserRepository(private val dataSource: GetUserDataSource) {
    fun execute(user: User) =
        dataSource.execute(user)
}