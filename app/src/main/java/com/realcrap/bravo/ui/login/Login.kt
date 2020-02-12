package com.realcrap.bravo.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.realcrap.bravo.R
import com.realcrap.bravo.di.component.ActivityComponent
import com.realcrap.bravo.ui.base.BaseActivity
import com.realcrap.bravo.ui.loginemail.LoginEmail
import com.realcrap.bravo.ui.signup.Registration
import com.realcrap.bravo.util.display.Toaster
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class Login : BaseActivity<LoginViewModel>() {



    companion object {
        const val CODE_SIGN_IN = 200

        fun getLaunchIntent(context: Context): Intent {
            val intent = Intent(context, Login::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            return intent
        }
    }

    private lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initGoogleLogin()
        viewModel.loginResult.observe(this, Observer {
            if (it == true){


            }
            else {


            }
        })


    }


    private fun initGoogleLogin() {

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
    }


    override fun provideLayoutId() = R.layout.activity_login


    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)


    }

    override fun setupView(savedInstanceState: Bundle?) {

        loginGoogleButton.setOnClickListener{

                googleClientConfig()

        }

        loginManual.setOnClickListener{
            startActivity(Intent(this,LoginEmail::class.java))
        }

        signupButton.setOnClickListener{
            startActivity(Intent(this,Registration::class.java))

        }









    }

    private fun googleClientConfig() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, CODE_SIGN_IN)    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CODE_SIGN_IN) {
            try {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    viewModel.firebaseAuthWithGoogle(account)
                }
            } catch (e: ApiException) {

            }

        }

    }
}
