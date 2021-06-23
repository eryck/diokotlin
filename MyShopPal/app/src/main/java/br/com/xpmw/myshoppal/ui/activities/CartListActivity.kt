package br.com.xpmw.myshoppal.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.com.xpmw.myshoppal.R
import br.com.xpmw.myshoppal.firestore.FirestoreClass
import br.com.xpmw.myshoppal.model.CartItem
import kotlinx.android.synthetic.main.activity_cart_list.*

class CartListActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_list)

        setupActionBar()
    }

    fun successCartItemsList(cartList: ArrayList<CartItem>){
        hideProgressDialog()

        for (i in cartList){
            Log.i("Cart Item Title", i.title)
        }
    }

    private fun getCartItemsList(){
        showProgressDialog(getString(R.string.please_wait))
        FirestoreClass().getCatList(this)
    }

    override fun onResume() {
        super.onResume()
        getCartItemsList()
    }

    private fun setupActionBar(){

        setSupportActionBar(toolbar_cart_list_activity)

        val actionBar = supportActionBar
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_white_color_back_24dp)
        }

        toolbar_cart_list_activity.setNavigationOnClickListener { onBackPressed() }
    }
}