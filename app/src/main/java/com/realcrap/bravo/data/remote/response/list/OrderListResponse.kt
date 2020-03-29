package com.realcrap.bravo.data.remote.response.list

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.realcrap.bravo.ui.mybooking.booking.Booking

data class OrderListResponse(


        @SerializedName("orders")
        @Expose
         val orders: List<Booking>,
        @SerializedName("status")
        @Expose
        val status: String
)



 class Service(
        @SerializedName("id")
        @Expose
          val id: String,
        @SerializedName("name")
        @Expose
         val name: String
)