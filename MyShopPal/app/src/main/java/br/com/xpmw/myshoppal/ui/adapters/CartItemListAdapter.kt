package br.com.xpmw.myshoppal.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.xpmw.myshoppal.R
import br.com.xpmw.myshoppal.model.CartItem
import br.com.xpmw.myshoppal.utils.GliderLoader
import kotlinx.android.synthetic.main.item_cart_layout.view.*

class CartItemListAdapter(
    private val context: Context,
    private val list: ArrayList<CartItem>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_cart_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = list [position]
        if(holder is MyViewHolder){
            GliderLoader(context).loadProductPicture(model.image, holder.itemView.iv_cart_item_image)
            holder.itemView.tv_cart_item_title.text = model.title
            holder.itemView.tv_cart_item_price.text = "$${model.price}"
            holder.itemView.tv_cart_quantity.text = model.cart_quantity
        }
    }

    override fun getItemCount(): Int {
       return list.size
    }

    private class MyViewHolder(view: View): RecyclerView.ViewHolder(view)
}