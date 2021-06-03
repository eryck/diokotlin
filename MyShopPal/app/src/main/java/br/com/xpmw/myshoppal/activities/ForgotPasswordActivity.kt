package br.com.xpmw.myshoppal.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.xpmw.myshoppal.R
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.activity_register.*

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        setupActionBar()
    }

    private fun setupActionBar() {

        setSupportActionBar(toolbar_forgot_password_activity)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setTitle(null)
            setHomeAsUpIndicator(R.drawable.ic_white_color_back_24dp)
        }

        toolbar_forgot_password_activity.setNavigationOnClickListener { onBackPressed() }
    }
}