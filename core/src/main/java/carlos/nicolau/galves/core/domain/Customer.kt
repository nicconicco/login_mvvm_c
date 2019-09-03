package carlos.nicolau.galves.login_mvpc.domain.model.login

import com.google.gson.annotations.SerializedName
import java.util.*

class Customer(

    @SerializedName("userId")
    var userId: Long? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("birthdate")
    var birthdate: Date? = null,
    @SerializedName("dependents")
    var dependents: Array<Customer>? = null

)


