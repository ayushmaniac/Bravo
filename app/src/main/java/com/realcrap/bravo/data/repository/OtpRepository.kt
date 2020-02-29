package com.realcrap.bravo.data.repository

import android.annotation.SuppressLint
import com.realcrap.bravo.data.local.prefs.UserPreferences
import com.realcrap.bravo.data.remote.NetworkService
import com.realcrap.bravo.data.remote.response.GeneralResponse
import com.realcrap.bravo.data.remote.response.UsersIdResponse
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OtpRepository @Inject constructor(
        private val networkService: NetworkService,
        private val userPreferences: UserPreferences
) {

    fun saveNewUserId(userId : UsersIdResponse){
        userPreferences.saveForgotUserId(userId.usersid)
    }

    @SuppressLint("CheckResult")
    fun generateOtp(usernanme: String): Single<UsersIdResponse> = networkService.generateOtp("forgotpassword", usernanme)
                .map {

                    UsersIdResponse(
                            it.status,
                            it.text,
                            it.usersid

                    )

                }



    fun verifyOtp(otp: String): Single<UsersIdResponse> = networkService.verifyOtp("forgotpassword-otp", userPreferences.getForgotUserId().toString(), otp)
                .map {

                    UsersIdResponse(
                            it.status,
                            it.text,
                            it.usersid


                    )
                }

    fun updatePassword(password : String) : Single<GeneralResponse> = networkService.updateNewPassword("changepassword", userPreferences.getForgotUserId().toString(), password)
            .map {
                GeneralResponse(
                        it.status,
                        it.text
                )
            }

    }
