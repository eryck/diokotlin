package br.com.xpmw.myshoppal.ui.activities

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.xpmw.myshoppal.R
import br.com.xpmw.myshoppal.firestore.FirestoreClass
import br.com.xpmw.myshoppal.model.Address
import br.com.xpmw.myshoppal.model.CartItem
import br.com.xpmw.myshoppal.model.Product
import br.com.xpmw.myshoppal.ui.adapters.CartItemListAdapter
import br.com.xpmw.myshoppal.utils.Constants.EXTRA_SELECT_ADDRESS
import kotlinx.android.synthetic.main.activity_checkout.*

class CheckoutActivity : BaseActivity() {

    private var mAddressDetails: Address? = null
    private lateinit var mProductList: ArrayList<Product>
    private lateinit var mCartItemList: ArrayList<CartItem>

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

        getProductList()
    }

    fun successProductListFromFireStore(productsList: ArrayList<Product>){
        mProductList = productsList
        getCartItemsList()
    }

    private fun getCartItemsList(){
        FirestoreClass().getCatList(this@CheckoutActivity)
    }

    fun successCartItemsList(cartList: ArrayList<CartItem>){
        hideProgressDialog()
        for (product in mProductList){
            for (cartItem in cartList){
                if (product.product_id == cartItem.product_id){
                    cartItem.stock_quantity = product.stock_quantity
                }
            }
        }
        mCartItemList = cartList

        rv_cart_list_items.layoutManager = LinearLayoutManager(this)
        rv_cart_list_items.setHasFixedSize(true)

        val cartListAdapter = CartItemListAdapter(this, mCartItemList, false)
        rv_cart_list_items.adapter = cartListAdapter
    }

    private fun getProductList(){
        //Show the progress dialog
        showProgressDialog(resources.getString(R.string.please_wait))

        FirestoreClass().getAllProductsList(this@CheckoutActivity)
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