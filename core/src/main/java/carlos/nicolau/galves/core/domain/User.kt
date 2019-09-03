package carlos.nicolau.galves.login_mvpc.domain.model.login

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("password")
    var password : String? =  null
)

