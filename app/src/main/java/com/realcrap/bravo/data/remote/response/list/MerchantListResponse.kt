package com.realcrap.bravo.data.remote.response.list

import com.google.gson.annotations.SerializedName
import com.realcrap.bravo.ui.allsalons.salon.Salon

data class MerchantListResponse (

        @SerializedName("status") val status : Int,
        @SerializedName("merchantlist") val data : List<Salon>
)