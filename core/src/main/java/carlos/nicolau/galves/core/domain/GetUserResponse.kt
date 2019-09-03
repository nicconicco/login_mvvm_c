package carlos.nicolau.galves.login_mvpc.domain.model.login

import com.google.gson.annotations.SerializedName

open class GetUserResponse(
    @SerializedName("status")
    var status: Boolean,
    @SerializedName("message")
    var message: String
)