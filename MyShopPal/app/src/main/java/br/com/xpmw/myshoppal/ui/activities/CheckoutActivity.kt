package br.com.xpmw.myshoppal.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.xpmw.myshoppal.R
import br.com.xpmw.myshoppal.model.Address
import br.com.xpmw.myshoppal.utils.Constants.EXTRA_SELECT_ADDRESS
import kotlinx.android.synthetic.main.activity_checkout.*

class CheckoutActivity : AppCompatActivity() {

    private var mAddressDetails: Address? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
        setUpActionBar()

        if(intent.hasExtra(EXTRA_SELECT_ADDRESS)){
            mAddressDetails = intent.getParcelableExtra<Address>(EXTRA_SELECT_ADDRESS)
        }

        if (mAddressDetails != null){
            tv_checkout_address_type.text = mAddressDetails?.type
            tv_checkout_full_name.text = mAddressDetails?.name
            tv_checkout_address.text = "${mAddressDetails!!.address}, ${mAddressDetails!!.zipCode}"
            tv_checkout_additional_note.text = mAddressDetails?.additionalNote

            if (mAddressDetails?.otherDetails!!.isNotEmpty()){
                tv_checkout_other_details.text = mAddressDetails?.otherDetails
            }
            tv_checkout_mobile_number.text = mAddressDetails?.mobileNumber
        }
    }

    private fun setUpActionBar(){
        setSupportActionBar(toolbar_checkout_activity)

        val actionBar = supportActionBar
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_white_color_back_24dp)
        }
        toolbar_checkout_activity.setNavigationOnClickListener { onBackPressed() }
    }
}