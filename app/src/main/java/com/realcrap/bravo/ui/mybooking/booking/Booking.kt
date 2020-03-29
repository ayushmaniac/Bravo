package com.realcrap.bravo.ui.mybooking.booking

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.realcrap.bravo.data.remote.response.list.Service

data class Booking (

    @SerializedName("couponamount")
    @Expose
    val couponamount: Int,
    @SerializedName("merchant_id")
    @Expose
    val merchant_id: String,
    @SerializedName("order_id")
    @Expose
    val order_id: String,
    @SerializedName("orderprocess")
    @Expose
    val orderprocess: String,
    @SerializedName("paidstatus")
    @Expose
    val paidstatus: String,
    @SerializedName("paymenttype")
    @Expose
    val paymenttype: String,
    @SerializedName("services")
    @Expose
    val services: String,
    @SerializedName("storename")
    @Expose
    val storename: String,
    @SerializedName("totalamount")
    @Expose
    val totalamount: String,
    @SerializedName("unique_id")
    @Expose
    val unique_id: String,
    @SerializedName("username")
    @Expose
    val username: String,
    @SerializedName("verify")
    @Expose
    val verify: String
)