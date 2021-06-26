package br.com.xpmw.myshoppal.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import br.com.xpmw.myshoppal.R
import br.com.xpmw.myshoppal.firestore.FirestoreClass
import br.com.xpmw.myshoppal.model.CartItem
import br.com.xpmw.myshoppal.model.Product
import br.com.xpmw.myshoppal.utils.Constants.DEFAULT_CART_QUANTITY
import br.com.xpmw.myshoppal.utils.Constants.EXTRA_PRODUCT_ID
import br.com.xpmw.myshoppal.utils.Constants.EXTRA_PRODUCT_OWNER_ID
import br.com.xpmw.myshoppal.utils.GliderLoader
import kotlinx.android.synthetic.main.activity_product_details.*

class ProductDetailsActivity : BaseActivity(), View.OnClickListener {

    private var mProductId: String = ""
    private lateinit var mProductDetails: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        setupActionBar()

        if (intent.hasExtra(EXTRA_PRODUCT_ID)) {
            mProductId = intent.getStringExtra(EXTRA_PRODUCT_ID)!!
        }

        var productOwnerID: String = ""

        if (intent.hasExtra(EXTRA_PRODUCT_OWNER_ID)) {
            productOwnerID = intent.getStringExtra(EXTRA_PRODUCT_OWNER_ID)!!
        }

        if (FirestoreClass().getCurrentUserID() == productOwnerID) {
            btn_add_to_cart.visibility = View.GONE
            btn_go_to_cart.visibility = View.GONE
        } else {
            btn_add_to_cart.visibility = View.VISIBLE
        }

        getProductDetails()

        btn_add_to_cart.setOnClickListener(this)
        btn_go_to_cart.setOnClickListener(this)
    }

    private fun getProductDetails() {
        showProgressDialog(getString(R.string.please_wait))
        FirestoreClass().getProductDetails(this, mProductId)
    }

    fun productExistsInCart(){
        hideProgressDialog()
        btn_add_to_cart.visibility = View.GONE
        btn_go_to_cart.visibility = View.VISIBLE
    }

    fun productDetailsSuccess(product: Product) {
        mProductDetails = product
        GliderLoader(this).loadProductPicture(
            product.image,
            iv_product_detail_image
        )
        tv_product_details_title.text = product.title
        tv_product_details_price.text = "$${product.price}"
        tv_product_details_description.text = product.description
        tv_product_details_available_quantity.text = product.stock_quantity

        if (product.stock_quantity.toInt() == 0){
            hideProgressDialog()
            btn_add_to_cart.visibility = View.GONE
            tv_product_details_available_quantity.text = getString(R.string.lbl_out_of_stock)
            tv_product_details_available_quantity.setTextColor(
                ContextCompat.getColor(
                    this, R.color.colorSnackBarError
                )
            )
        }else{
            if (FirestoreClass().getCurrentUserID() == product.user_id){
                hideProgressDialog()
            }else{
                FirestoreClass().checkIfItemExistInCart(this, mProductId)
            }
        }
    }

    private fun setupActionBar() {
        setSupportActionBar(toolbar_product_details_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_white_color_back_24dp)
        }

        toolbar_product_details_activity.setNavigationOnClickListener { onBackPressed() }
    }

    private fun addToCart() {
        val cartItem = CartItem(
            FirestoreClass().getCurrentUserID(),
            mProductId,
            mProductDetails.title,
            mProductDetails.price,
            mProductDetails.image,
            DEFAULT_CART_QUANTITY
        )

        showProgressDialog(getString(R.string.please_wait))
        FirestoreClass().addCartItems(this, cartItem)
    }

    fun addToCartSuccess(){
        hideProgressDialog()
        Toast.makeText(this, getString(R.string.success_message_item_add_to_cart), Toast.LENGTH_SHORT).show()

        btn_add_to_cart.visibility = View.GONE
        btn_go_to_cart.visibility = View.VISIBLE
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.btn_add_to_cart -> {
                    addToCart()
                }
            }
            when(v.id){
                R.id.btn_go_to_cart ->{
                    startActivity(Intent(this, CartListActivity::class.java))

                }
            }
        }
    }
}