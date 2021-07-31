package br.com.xpmw.myshoppal.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.com.xpmw.myshoppal.R
import br.com.xpmw.myshoppal.firestore.FirestoreClass
import br.com.xpmw.myshoppal.model.Address
import kotlinx.android.synthetic.main.activity_address_list.*
import java.util.ArrayList

class AddressListActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address_list)

        setupActionBas()

        tv_add_address.setOnClickListener {
            val intent = Intent(this, AddEditAddressActivity::class.java)
            startActivity(intent)
        }

        getAddressesList()
    }

    fun successAddressListFromFirestore(addressList: ArrayList<Address>) {
        hideProgressDialog()
        for (i in addressList) {
            Log.i("Name and Address", "${i.name} :: ${i.address}")
        }
    }

    private fun getAddressesList() {
        showProgressDialog(getString(R.string.please_wait))
        FirestoreClass().getAddressesList(this)
    }

    private fun setupActionBas() {
        setSupportActionBar(toolbar_address_list_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_white_color_back_24dp)
        }

        toolbar_address_list_activity.setNavigationOnClickListener { onBackPressed() }
    }


}