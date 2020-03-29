package com.realcrap.bravo.ui.buisnesspage.services

interface ServiceClickListener {

    fun onServiceBookClicked(id : String, title : String, price : String)


    fun onServiceCanceledClicked(id : String, title : String, price : String)

}