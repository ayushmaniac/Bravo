package com.realcrap.bravo.data.remote.response.servicelist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.realcrap.bravo.ui.allsalons.salon.Salon
import com.realcrap.bravo.ui.buisnesspage.services.Services

data class ServiceResponse(
        @SerializedName("status") val status : Int,
        @SerializedName("merchants") val merchants : Merchants,
        @SerializedName("merchantlist") val servicesData : List<Services>
)