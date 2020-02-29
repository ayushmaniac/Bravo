package com.realcrap.bravo.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UsersIdResponse(

        @Expose
        @SerializedName("status")
        var status: String,

        @Expose
        @SerializedName("text")
        var text: String,

        @Expose
        @SerializedName("usersid")
        var usersid: String
)