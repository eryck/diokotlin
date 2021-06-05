package br.com.xpmw.myshoppal.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.xpmw.myshoppal.R
import br.com.xpmw.myshoppal.model.User
import br.com.xpmw.myshoppal.utils.Constants.EXTRA_USER_DETAILS
import kotlinx.android.synthetic.main.activity_user_profile.*

class UserProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        var userDatails: User = User()
        if (intent.hasExtra(EXTRA_USER_DETAILS)){
            userDatails = intent.getParcelableExtra(EXTRA_USER_DETAILS)!!
        }

        et_first_name.isEnabled = false
        et_first_name.setText(userDatails.firstName)
        et_last_name.isEnabled = false
        et_last_name.setText(userDatails.lastName)
        et_email.isEnabled = false
        et_email.setText(userDatails.email)
    }
}