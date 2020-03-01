package com.realcrap.bravo.ui.uploadpic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mindorks.paracamera.Camera
import com.realcrap.bravo.R
import com.realcrap.bravo.di.component.ActivityComponent
import com.realcrap.bravo.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_upload_picture.*
import java.io.FileNotFoundException
import javax.inject.Inject

class UploadPicture : BaseActivity<UploadPictureViewModel>() {

    companion object {
        const val RESULT_GALLERY_IMG = 1001
    }

    @Inject
    lateinit var camera: Camera

    override fun provideLayoutId(): Int = R.layout.activity_upload_picture


    override fun injectDependencies(activityComponent: ActivityComponent) = activityComponent.inject(this)

    override fun setupView(savedInstanceState: Bundle?) {

        cameraCard.setOnClickListener{
            try {
                camera.takePicture()
            } catch (e: Exception) {
                e.printStackTrace()
            }


        }

        galleryCard.setOnClickListener{

            Intent(Intent.ACTION_PICK)
                    .apply {
                        type = "image/*"
                    }.run {
                        startActivityForResult(this, Companion.RESULT_GALLERY_IMG)
                    }

        }
    }

    override fun onActivityResult(reqCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(reqCode, resultCode, intent)
        if (resultCode == RESULT_OK) {
            when (reqCode) {
                RESULT_GALLERY_IMG -> {
                    try {
                        intent?.data?.let {
                            applicationContext?.contentResolver?.openInputStream(it)?.run {
//                                viewModel.onGalleryImageSelected(this)
                            }
                        } ?: showMessage("Please try again")
                    } catch (e: FileNotFoundException) {
                        e.printStackTrace()
                        showMessage("Please try again")
                    }
                }
                Camera.REQUEST_TAKE_PHOTO -> {
//                    viewModel.onCameraImageTaken { camera.cameraBitmapPath }
                }
            }
        }
    }


}
