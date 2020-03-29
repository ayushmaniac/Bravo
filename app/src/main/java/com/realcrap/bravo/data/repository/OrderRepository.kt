package com.realcrap.bravo.data.repository

import com.realcrap.bravo.data.local.prefs.UserPreferences
import com.realcrap.bravo.data.remote.NetworkService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OrderRepository @Inject constructor(
        private val networkService: NetworkService,
        private val userPreferences: UserPreferences
){


    fun doOrderByCash() {



    }


    fun doOrderByPayTm() {



    }



}