package com.jeet.notes_simple_kotlin

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList


class NotesRVadapter(
    val context: Context,
    val noteClickDeleteInterface: NoteClickDeleteInterface,
    val noteClickInterface: NoteClickInterface
) :
    RecyclerView.Adapter<NotesRVadapter.ViewHolder>() {
    lateinit var random:Random
    private val allNotes = ArrayList<Notes>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteTV = itemView.findViewById<TextView>(R.id.idTVNote)
        val dateTV = itemView.findViewById<TextView>(R.id.idTVDate)
        val deleteIV = itemView.findViewById<ImageView>(R.id.idIVDelete)
        val cardview=itemView.findViewById<CardView>(R.id.rek)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
   //layout inflation
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.note_rv_item,
            parent, false
        )

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.noteTV.setText(allNotes.get(position).noteTitle)
//        holder.dateTV.setText("Last Updated : " + allNotes.get(position).timestamp)
        holder.dateTV.setText("" + allNotes.get(position).timestamp)
        holder.deleteIV.setOnClickListener {
            noteClickDeleteInterface.onDeleteIconClick(allNotes.get(position))
        }

        holder.itemView.setOnClickListener {
            noteClickInterface.onNoteClick(allNotes.get(position))
        }

        val colors=ArrayList<Int>()
        colors.add(getNext())
        colors.add(getNext())

        val gd: GradientDrawable = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,colors.toIntArray() )
        gd.gradientType= GradientDrawable.LINEAR_GRADIENT
        gd.gradientRadius=9000.0f
        gd.cornerRadius=0f

        holder.cardview.background=gd
        holder.cardview.radius=9000f

    }//closed of onbindviewHolder

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList: List<Notes>) {
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

    fun getNext():Int{
        random= Random()
        return Color.argb(255,random.nextInt(256),random.nextInt(56),random.nextInt(256))
    }

}

interface NoteClickDeleteInterface {
    fun onDeleteIconClick(note: Notes)
}

interface NoteClickInterface {
    fun onNoteClick(note: Notes)
}
