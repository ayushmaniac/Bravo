package com.realcrap.bravo.ui.uploadpic

import androidx.lifecycle.MutableLiveData
import com.realcrap.bravo.R
import com.realcrap.bravo.data.repository.ProfilePictureRepository
import com.realcrap.bravo.data.repository.UserRepository
import com.realcrap.bravo.ui.base.BaseViewModel
import com.realcrap.bravo.util.common.FileUtils
import com.realcrap.bravo.util.common.Resource
import com.realcrap.bravo.util.log.Logger
import com.realcrap.bravo.util.network.NetworkHelper
import com.realcrap.bravo.util.rx.SchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import java.io.File
import java.io.InputStream

class UploadPictureViewModel (compositeDisposable: CompositeDisposable, networkHelper: NetworkHelper,
                              schedulerProvider: SchedulerProvider,
                              private val userRepository: UserRepository,
                              private val profilePictureRepository: ProfilePictureRepository,
                              private val directory: File

) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    val loading: MutableLiveData<Boolean> = MutableLiveData()

    override fun onCreate() {}



    fun onGalleryImageSelected(inputStream: InputStream) {
        loading.postValue(true)
        compositeDisposable.add(
                Single.fromCallable {
                    FileUtils.saveInputStreamToFile(
                            inputStream, directory, "gallery_img_temp", 500
                    )
                }
                        .subscribeOn(schedulerProvider.io())
                        .subscribe(
                                {
                                    if (it != null) {
                                        FileUtils.getImageSize(it)?.run {
                                            uploadPhoto(it)
                                        }
                                    } else {
                                        loading.postValue(false)
                                        messageStringId.postValue(Resource.error(R.string.try_again))

                                    }
                                },
                                {
                                    loading.postValue(false)
                                    messageStringId.postValue(Resource.error(R.string.try_again))
                                }
                        )
        )
    }

    fun onCameraImageTaken(cameraImageProcessor: () -> String) {
        loading.postValue(true)
        compositeDisposable.add(
                Single.fromCallable { cameraImageProcessor() }
                        .subscribeOn(schedulerProvider.io())
                        .subscribe(
                                {
                                    File(it).apply {
                                        FileUtils.getImageSize(this)?.let { size ->
                                            uploadPhoto(this)
                                        } ?: loading.postValue(false)
                                    }
                                },
                                {
                                    loading.postValue(false)
                                    messageStringId.postValue(Resource.error(R.string.try_again))
                                }
                        )
        )
    }


    private fun uploadPhoto(imageFile: File) {
        Logger.d("DEBUG", imageFile.path)
        compositeDisposable.add(
                profilePictureRepository.uploadPhoto(imageFile)
                        .subscribeOn(schedulerProvider.io())
                        .subscribe(
                                {
                                    loading.postValue(false)
                                },
                                {
                                    handleNetworkError(it)
                                    loading.postValue(false)
                                }
                        )

        )
    }
}

