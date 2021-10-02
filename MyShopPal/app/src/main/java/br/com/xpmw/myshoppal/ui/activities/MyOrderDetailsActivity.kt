package br.com.xpmw.myshoppal.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import br.com.xpmw.myshoppal.R
import br.com.xpmw.myshoppal.model.Order
import br.com.xpmw.myshoppal.utils.Constants
import br.com.xpmw.myshoppal.utils.Constants.EXTRA_MY_ORDER_DETAILS
import kotlinx.android.synthetic.main.activity_my_order_details.*

class MyOrderDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_order_details)
        setupActionBar()

        var myOrderDetails: Order
        if (intent.hasExtra(EXTRA_MY_ORDER_DETAILS)){
            intent?.let{
                it.getParcelableExtra<Order>(EXTRA_MY_ORDER_DETAILS)
            }
        }
    }

    private fun setupActionBar(){
        setSupportActionBar(toolbar_my_order_details_activity)

        val actionBar = supportActionBar
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_white_color_back_24dp)
        }

        toolbar_my_order_details_activity.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}