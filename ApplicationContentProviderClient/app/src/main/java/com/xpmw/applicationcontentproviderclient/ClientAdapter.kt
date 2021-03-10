package com.xpmw.applicationcontentproviderclient

import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ClientAdapter (private val mCursor: Cursor): RecyclerView.Adapter<ClientViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder =
        ClientViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.client_item, parent, false))

    override fun getItemCount(): Int = mCursor.count

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        mCursor.moveToPosition(position)
        holder.clientTitle.text = mCursor.getString(mCursor.getColumnIndex("title"))
        holder.clientDescription.text = mCursor.getString(mCursor.getColumnIndex("description"))
    }

}

class ClientViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val clientTitle = itemView.findViewById(R.id.cliente_item_title) as TextView
    val clientDescription = itemView.findViewById(R.id.cliente_item_description) as TextView
}