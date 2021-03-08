package com.xpmw.applicationcontentprovider

import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapeter() : RecyclerView.Adapter<NotesViewHolder>() {

    private var mCursor: Cursor? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder =
        NotesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        )

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    fun setCursor(newCursor: Cursor?){
        mCursor = newCursor
        notifyDataSetChanged()
    }

}

class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val noteTitle = itemView.findViewById(R.id.note_title) as TextView
    val noteDescription = itemView.findViewById(R.id.note_descrition) as TextView
    val noteButtonRemove = itemView.findViewById(R.id.note_button_remove) as Button
}