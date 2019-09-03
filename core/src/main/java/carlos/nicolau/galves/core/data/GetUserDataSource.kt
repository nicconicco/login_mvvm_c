package carlos.nicolau.galves.core.data

import carlos.nicolau.galves.login_mvpc.domain.model.login.GetUserResponse
import carlos.nicolau.galves.login_mvpc.domain.model.login.User
import io.reactivex.Single

interface GetUserDataSource {
    fun execute(user: User) : Single<GetUserResponse>
}