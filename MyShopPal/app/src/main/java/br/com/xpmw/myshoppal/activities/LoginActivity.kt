package br.com.xpmw.myshoppal.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import br.com.xpmw.myshoppal.R
import br.com.xpmw.myshoppal.firestore.FirestoreClass
import br.com.xpmw.myshoppal.model.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setUpFullScreen()
        optionClick()
    }

    private fun setUpFullScreen() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }

    private fun optionClick() {
        tv_forgot_password.setOnClickListener {
            val intent = Intent(this@LoginActivity, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
        btn_login.setOnClickListener {
            loginRegisteredUser()
        }
        tv_register.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }


    private fun validateLoginDetails(): Boolean {
        return when {
            TextUtils.isEmpty(et_mail.text.toString().trim() { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
                false
            }
            TextUtils.isEmpty(et_password.text.toString().trim() { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_password), true)
                false
            }
            else -> {
                true
            }
        }
    }

    private fun loginRegisteredUser() {
        if (validateLoginDetails()) {
            showProgressDialog(resources.getString(R.string.please_wait))

            val email = et_mail.text.toString().trim() { it <= ' ' }
            val password = et_password.text.toString().trim() { it <= ' ' }

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        FirestoreClass().getUserDetails(this@LoginActivity)
                    } else {
                        hideProgressDialog()
                        showErrorSnackBar(task.exception!!.message.toString(), true)
                    }
                }
        }
    }

    fun userLoggedInSuccess(user: User) {
        hideProgressDialog()

        Log.i("First Name: ", user.firstName)
        Log.i("Last Name: ", user.lastName)
        Log.i("Email: ", user.email)

        if (user.profileCpmpleted == 0) {
            val intent = Intent(this@LoginActivity, UserProfileActivity::class.java)
            startActivity(intent)
        } else {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        }
        finish()
    }
}