package com.realcrap.bravo.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(


        @Expose
        @SerializedName("status")
        val status: String,

        @Expose
        @SerializedName("text")
        val text: String,

        @Expose
        @SerializedName("usersid")
        val usersid: String

)