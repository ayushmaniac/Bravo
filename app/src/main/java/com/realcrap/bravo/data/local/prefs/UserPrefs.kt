package com.realcrap.bravo.data.local.prefs

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferences @Inject constructor(private val prefs: SharedPreferences) {

    companion object {
        const val KEY_USER_ID = "PREF_KEY_USER_ID"
        const val KEY_USER_NAME = "PREF_KEY_USER_NAME"
        const val KEY_USER_EMAIL = "PREF_KEY_USER_EMAIL"
        const val KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN"
        const val KEY_STATUS= "PREF_STATUS"
        const val GET_LOC = "PREF_GET_LOC"
        const val KEY_USER_MOBILE = "PREF_MOB"
    }

    fun getToken(): String? =
            prefs.getString(KEY_USER_ID, null)

    fun setToken(userId: String) =
            prefs.edit().putString(KEY_USER_ID, userId).apply()


    fun getUserText(): String? =
            prefs.getString(KEY_USER_ID, null)

    fun setUserText(userText: String) =
            prefs.edit().putString(KEY_USER_ID, userText).apply()

    fun removeUserId() =
            prefs.edit().remove(KEY_USER_ID).apply()

    fun getUserName(): String? =
            prefs.getString(KEY_USER_NAME, null)

    fun setUserName(userName: String) =
            prefs.edit().putString(KEY_USER_NAME, userName).apply()

    fun removeUserName() =
            prefs.edit().remove(KEY_USER_NAME).apply()

    fun getUserEmail(): String? =
            prefs.getString(KEY_USER_EMAIL, null)

    fun setUserEmail(email: String) =
            prefs.edit().putString(KEY_USER_EMAIL, email).apply()

    fun removeUserEmail() =
            prefs.edit().remove(KEY_USER_EMAIL).apply()

    fun getAccessToken(): String? =
            prefs.getString(KEY_ACCESS_TOKEN, null)

    fun setAccessToken(token: String) =
            prefs.edit().putString(KEY_ACCESS_TOKEN, token).apply()

    fun removeAccessToken() =
            prefs.edit().remove(KEY_ACCESS_TOKEN).apply()

    fun getUserStatus(): String? =
            prefs.getString(KEY_USER_NAME, null)

    fun setUserStatus(userStatus: String) =
            prefs.edit().putString(KEY_USER_NAME, userStatus).apply()

    fun getUserLoc(): String? =
            prefs.getString(GET_LOC, null)

    fun setUserLoc(userStatus: String) =
            prefs.edit().putString(GET_LOC, userStatus).apply()


    fun saveNewUserStatus(userNewStatus: String) =
            prefs.edit().putString(KEY_STATUS, userNewStatus).apply()

    fun getNewUserStatus(): String? =
            prefs.getString(KEY_STATUS, null)


    fun saveNewUserMobile(userNewMobile: String) =
            prefs.edit().putString(KEY_USER_MOBILE, userNewMobile).apply()

    fun getNewUserMobile(): String? =
            prefs.getString(KEY_USER_MOBILE, null)


    fun saveForgotUserId(forgotUserId: String) =
            prefs.edit().putString(KEY_USER_MOBILE, forgotUserId).apply()

    fun getForgotUserId(): String? =
            prefs.getString(KEY_USER_MOBILE, null)


}