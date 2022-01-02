package com.example.notesappfull

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.items_row.view.*

class RVAdapter(private val activity: MainActivity, var notes : ArrayList<Note>):RecyclerView.Adapter<RVAdapter.ItemsViewHolder>(){
    class ItemsViewHolder(itemsView : View): RecyclerView.ViewHolder(itemsView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        return ItemsViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.items_row,
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        val text = notes[position]

        holder.itemView.apply {
            tvNote.text = "${text.pk} - ${text.note}"

            btn_edit.setOnClickListener{
                activity.raiseDialog(text.pk)
            }

            btn_delete.setOnClickListener {
                activity.deleteNote(text.pk)
            }
        }
    }

    override fun getItemCount() = notes.size

    fun update(notes : ArrayList<Note>){
        this.notes = notes
        notifyDataSetChanged()
    }
}