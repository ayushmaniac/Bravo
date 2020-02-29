package com.realcrap.bravo.ui.buisnesspage.services

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Services(

        @SerializedName("id") val id : String,
        @SerializedName("title") val title : String,
        @SerializedName("excerpt") val excerpt : String,
        @SerializedName("price") val price : String,
        @SerializedName("saleprice") val saleprice : String,
        @SerializedName("image") val image : String




)