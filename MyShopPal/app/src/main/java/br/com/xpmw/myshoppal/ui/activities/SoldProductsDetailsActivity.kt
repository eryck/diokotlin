package br.com.xpmw.myshoppal.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.xpmw.myshoppal.R
import br.com.xpmw.myshoppal.model.SoldProduct
import br.com.xpmw.myshoppal.utils.Constants.EXTRA_SOLD_PRODUCT_DETAILS
import kotlinx.android.synthetic.main.activity_register.*

class SoldProductsDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sold_products_details)
        setupActionBar()

        var productDetails = SoldProduct()
        if (intent.hasExtra(EXTRA_SOLD_PRODUCT_DETAILS)){
            productDetails = intent.getParcelableExtra<SoldProduct>(EXTRA_SOLD_PRODUCT_DETAILS)!!

        }
    }

    private fun setupActionBar() {

        setSupportActionBar(toolbar_register_activity)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_baseline_color_black_24)
        }

        toolbar_register_activity.setNavigationOnClickListener { onBackPressed() }
    }
}
