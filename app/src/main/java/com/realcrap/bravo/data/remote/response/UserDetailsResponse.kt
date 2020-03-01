package com.realcrap.bravo.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserDetailsResponse(

        @SerializedName("status") val status : Int,
        @SerializedName("users") val users : Users
        )
{

    data class Users(

            @SerializedName("id")
            @Expose
            var id : String,


            @SerializedName("name")
            @Expose
            var name : String,

            @SerializedName("email")
            @Expose
            var email : String,

            @SerializedName("coin")
            @Expose
            var coin : String,

            @SerializedName("mobile")
            @Expose
            var mobile : String,

            @SerializedName("profilepic")
            @Expose
            var profilepic : String





    )
}