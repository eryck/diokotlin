package br.com.xpmw.myshoppal.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.com.xpmw.myshoppal.R
import br.com.xpmw.myshoppal.model.SoldProduct
import br.com.xpmw.myshoppal.utils.Constants.EXTRA_SOLD_PRODUCT_DETAILS
import br.com.xpmw.myshoppal.utils.GlideLoader
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.toolbar_register_activity
import kotlinx.android.synthetic.main.activity_sold_products_details.*
import java.text.SimpleDateFormat
import java.util.*

class SoldProductsDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sold_products_details)
        setupActionBar()

        var productDetails = SoldProduct()
        if (intent.hasExtra(EXTRA_SOLD_PRODUCT_DETAILS)){
            productDetails = intent.getParcelableExtra<SoldProduct>(EXTRA_SOLD_PRODUCT_DETAILS)!!
        }

        setupUI(productDetails)
    }

    private fun setupActionBar() {

        setSupportActionBar(toolbar_sold_product_details_activity)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_baseline_color_black_24)
        }

        toolbar_sold_product_details_activity.setNavigationOnClickListener { onBackPressed() }
    }

    private fun setupUI(productDetails: SoldProduct) {

        tv_sold_product_details_id.text = productDetails.order_id

        // Date Format in which the date will be displayed in the UI.
        val dateFormat = "dd MMM yyyy HH:mm"
        // Create a DateFormatter object for displaying date in specified format.
        val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = productDetails.order_date
        tv_sold_product_details_date.text = formatter.format(calendar.time)

        GlideLoader(this).loadProductPicture(
            productDetails.image,
            iv_product_item_image
        )
        tv_product_item_name.text = productDetails.title
        tv_product_item_price.text ="$${productDetails.price}"
        tv_sold_product_quantity.text = productDetails.sold_quantity

        tv_sold_details_address_type.text = productDetails.address.type
        tv_sold_details_full_name.text = productDetails.address.name
        tv_sold_details_address.text =
            "${productDetails.address.address}, ${productDetails.address.zipCode}"
        tv_sold_details_additional_note.text = productDetails.address.additionalNote

        if (productDetails.address.otherDetails.isNotEmpty()) {
            tv_sold_details_other_details.visibility = View.VISIBLE
            tv_sold_details_other_details.text = productDetails.address.otherDetails
        } else {
            tv_sold_details_other_details.visibility = View.GONE
        }
        tv_sold_details_mobile_number.text = productDetails.address.mobileNumber

        tv_sold_product_sub_total.text = productDetails.sub_total_amount
        tv_sold_product_shipping_charge.text = productDetails.shipping_charge
        tv_sold_product_total_amount.text = productDetails.total_amount
    }
}
