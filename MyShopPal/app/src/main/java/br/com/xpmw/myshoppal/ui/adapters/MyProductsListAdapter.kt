package br.com.xpmw.myshoppal.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.xpmw.myshoppal.R
import br.com.xpmw.myshoppal.model.Product
import br.com.xpmw.myshoppal.utils.GliderLoader
import kotlinx.android.synthetic.main.item_list_layout.view.*

open class MyProductsListAdapter(
    private val context: Context,
    private val list: ArrayList<Product>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_list_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = list[position]

        if (holder is MyViewHolder){
            GliderLoader(context).loadProductPicture(model.image, holder.itemView.iv_item_image)
            holder.itemView.tv_item_name.text = model.title
            holder.itemView.tv_item_price.text = "$${model.price}"
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view)
}