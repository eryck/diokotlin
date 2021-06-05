package br.com.xpmw.myshoppal.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.xpmw.myshoppal.R
import br.com.xpmw.myshoppal.utils.Constants.LOGGED_IN_USERNAME
import br.com.xpmw.myshoppal.utils.Constants.MYSHOPPAL_PREFERENCES
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences(MYSHOPPAL_PREFERENCES, Context.MODE_PRIVATE)
        val userName = sharedPreferences.getString(LOGGED_IN_USERNAME, "")!!
        tv_name.text = "Hello $userName"
    }
}