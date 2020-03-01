package com.realcrap.bravo.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProfilePicResponse(


        @SerializedName("status")
        @Expose
        val status : String,

        @SerializedName("text")
        @Expose
        val text : String,

        @SerializedName("profilepic")
        @Expose
        val profilepic : String
)

