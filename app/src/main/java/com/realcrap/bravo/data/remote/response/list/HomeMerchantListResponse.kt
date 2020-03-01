package com.realcrap.bravo.data.remote.response.list

import com.google.gson.annotations.SerializedName
import com.realcrap.bravo.ui.allsalons.salon.Salon
import com.realcrap.bravo.ui.home.homesalons.HomeSalons

data class HomeMerchantListResponse (

        @SerializedName("status") val status : Int,
        @SerializedName("merchantlist") val data : List<HomeSalons>
)