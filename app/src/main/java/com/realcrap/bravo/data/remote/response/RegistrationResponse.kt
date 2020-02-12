package com.realcrap.bravo.data.remote.response
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RegistrationResponse(


        @SerializedName("status")
        @Expose
        var status : Int,

        @SerializedName("usersid")
        @Expose
        var usersid : String,

        @SerializedName("text")
        @Expose
        var text : String
)