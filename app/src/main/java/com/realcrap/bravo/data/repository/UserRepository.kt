package com.realcrap.bravo.data.repository

import com.realcrap.bravo.data.local.prefs.UserPreferences
import com.realcrap.bravo.data.model.User
import com.realcrap.bravo.data.remote.NetworkService
import com.realcrap.bravo.data.remote.response.LoginResponse
import com.realcrap.bravo.data.remote.response.RegistrationResponse
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
        private val networkService: NetworkService,
        private val userPreferences: UserPreferences
) {

    fun saveCurrentUser(user: LoginResponse) {
        userPreferences.setUserStatus(user.status)
        userPreferences.setUserText(user.text)
        userPreferences.setAccessToken(user.usersid)
    }

    fun removeCurrentUser() {
        userPreferences.removeUserId()
        userPreferences.removeUserName()
        userPreferences.removeUserEmail()
        userPreferences.removeAccessToken()
    }

    fun getCurrentUser(): User? {

        val userStatus = userPreferences.getUserStatus();
        val userId = userPreferences.getAccessToken()
        val userText = userPreferences.getUserText()

        return if ( userStatus != null && userId != null && userText!=null)
            User(userStatus, userText, userId)
        else
            null
    }

    fun doUserLogin(email: String, password: String): Single<LoginResponse> =
            networkService.doLogin("login", email, password).map {

                        LoginResponse(
                                it.status,
                                it.text,
                                it.usersid

                        )
            }

    fun createAccount(name: String, mobile: String, email: String, password: String): Single<RegistrationResponse> =

        networkService.doRegistration("registration", name, email, mobile, password).map {

            RegistrationResponse(
                    it.status,
                    it.text,
                    it.usersid

            )
        }


    }


