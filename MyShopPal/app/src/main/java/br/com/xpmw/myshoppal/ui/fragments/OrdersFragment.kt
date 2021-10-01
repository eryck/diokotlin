package br.com.xpmw.myshoppal.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.xpmw.myshoppal.R
import br.com.xpmw.myshoppal.firestore.FirestoreClass
import br.com.xpmw.myshoppal.model.Order
import br.com.xpmw.myshoppal.ui.adapters.MyOrdersListAdapter
import kotlinx.android.synthetic.main.fragment_orders.*

class OrdersFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_orders, container, false)
        return root
    }

    override fun onResume() {
        super.onResume()
        getMyOrderList()
    }

    fun popularOrderListInUI(orderList: ArrayList<Order>){
        hideProgressDialog()

        if (orderList.size > 0){
            rv_my_order_items.visibility = View.VISIBLE
            tv_no_orders_found.visibility = View.GONE

            rv_my_order_items.layoutManager = LinearLayoutManager(requireActivity())
            rv_my_order_items.setHasFixedSize(true)

            val myOrdersAdapter = MyOrdersListAdapter(requireContext(), orderList)
            rv_my_order_items.adapter = myOrdersAdapter
        }else{
            rv_my_order_items.visibility = View.GONE
            tv_no_orders_found.visibility = View.VISIBLE
        }
    }

    private fun getMyOrderList(){
        showProgressDialog(getString(R.string.please_wait))

        FirestoreClass().getMyOrderList(this)
    }
}