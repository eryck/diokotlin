package com.xpmw.agendatk

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ContacAdapterViewHolder>(){

    private val list : MutableList<Contact> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContacAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return ContacAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContacAdapterViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ContacAdapterViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){



        fun bind(contact: Contact){

        }
    }
}