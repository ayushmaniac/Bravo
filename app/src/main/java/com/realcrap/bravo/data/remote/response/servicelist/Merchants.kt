package com.realcrap.bravo.data.remote.response.servicelist

import com.google.gson.annotations.SerializedName

data class Merchants(
        @SerializedName("id") val id : String,
        @SerializedName("unique_id") val unique_id : String,
        @SerializedName("name") val name : String,
        @SerializedName("email") val email : String,
        @SerializedName("storename") val storename : String,
        @SerializedName("storetype") val storetype : String,
        @SerializedName("address") val address : String,
        @SerializedName("state") val state : String,
        @SerializedName("city") val city : String,
        @SerializedName("location") val location : String,
        @SerializedName("latitude") val latitude : String,
        @SerializedName("longitude") val longitude : String,
        @SerializedName("rating") val rating : Int,
        @SerializedName("logo") val logo : String,
        @SerializedName("coverpic") val coverpic : String
)