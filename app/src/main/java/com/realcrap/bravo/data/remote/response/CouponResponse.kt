package com.realcrap.bravo.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CouponResponse(

        @SerializedName("status")
        @Expose
        val status : String,

        @SerializedName("message")
        @Expose
        val message : String,

        @SerializedName("coupanamt")
        @Expose
        val coupanamt : String,

        @SerializedName("totalamt")
        @Expose
        val totalamt : String,

        @SerializedName("savingamt")
        @Expose
        val savingamt : String




        )