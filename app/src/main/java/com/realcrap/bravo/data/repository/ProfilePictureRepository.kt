package com.realcrap.bravo.data.repository

import com.realcrap.bravo.data.local.prefs.UserPreferences
import com.realcrap.bravo.data.model.User
import com.realcrap.bravo.data.remote.NetworkService
import io.reactivex.Single
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import javax.inject.Inject

class ProfilePictureRepository @Inject constructor(private val networkService: NetworkService,
                                                   private val userPreferences: UserPreferences
) {

    fun uploadPhoto(file: File): Single<String> {
        return MultipartBody.Part.createFormData(
                "profilepic", file.name, RequestBody.create("image/*".toMediaTypeOrNull(), file)
        ).run {
            return@run networkService.uploadProfilePic(this, userPreferences.getAccessToken().toString())
                    .map {
                            it.profilepic }
        }
    }
}