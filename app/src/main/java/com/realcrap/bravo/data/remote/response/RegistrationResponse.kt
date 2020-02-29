package com.realcrap.bravo.data.remote.response
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RegistrationResponse(


        @SerializedName("status")
        @Expose
        var status : Int,

        @SerializedName("text")
        @Expose
        var text : String,

        @SerializedName("usersid")
        @Expose
        var usersid : String,


        @SerializedName("name")
        @Expose
        var name : String,

        @SerializedName("email")
        @Expose
        var email : String,


        @SerializedName("mobile")
        @Expose
        var mobile : String


        )