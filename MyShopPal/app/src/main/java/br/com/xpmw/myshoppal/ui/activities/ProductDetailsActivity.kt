package br.com.xpmw.myshoppal.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.xpmw.myshoppal.R
import br.com.xpmw.myshoppal.utils.Constants.EXTRA_PRODUCT_ID
import kotlinx.android.synthetic.main.activity_add_product.*
import kotlinx.android.synthetic.main.activity_product_details.*

class ProductDetailsActivity : AppCompatActivity() {

    private var mProductId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        setupActionBar()

        if(intent.hasExtra(EXTRA_PRODUCT_ID)){
            mProductId = intent.getStringExtra(EXTRA_PRODUCT_ID)!!
        }
    }

    private fun setupActionBar(){
        setSupportActionBar(toolbar_product_details_activity)

        val actionBar = supportActionBar
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_white_color_back_24dp)
        }

        toolbar_product_details_activity.setNavigationOnClickListener { onBackPressed() }
    }
}