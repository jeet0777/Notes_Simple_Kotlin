package com.jeet.notes_simple_kotlin

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), NoteClickDeleteInterface, NoteClickInterface {
    lateinit var viewModal: NotesViewModel
    lateinit var notesRV: RecyclerView
    lateinit var addFAB: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notesRV = findViewById(R.id.smp_notes)
        addFAB = findViewById(R.id.fab)

//        notesRV.layoutManager = LinearLayoutManager(this)
        notesRV.layoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)


        val noteRVAdapter = NotesRVadapter(this, this, this)

        // on below line we are setting
        // adapter to our recycler view.
        notesRV.adapter = noteRVAdapter

        // on below line we are
        // initializing our view modal.
        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NotesViewModel::class.java)

        viewModal.allNotes.observe(this, Observer { list ->
            list?.let {
                // on below line we are updating our list.
                noteRVAdapter.updateList(it)
            }
        })
        addFAB.setOnClickListener {
            // adding a click listener for fab button
            // and opening a new intent to add a new note.
            val intent = Intent(this
                , AddEditNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }



    }

    override fun onDeleteIconClick(note: Notes) {
//        TODO("Not yet implemented")
        viewModal.deleteNote(note)
        Toast.makeText(this, "${note.noteTitle} Deleted", Toast.LENGTH_LONG).show()


    }

    override fun onNoteClick(note: Notes) {
//        TODO("Not yet implemented")

        val intent = Intent(this, AddEditNoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.noteTitle)
        intent.putExtra("noteDescription", note.decs)
        intent.putExtra("noteId", note.id)
        startActivity(intent)
        this.finish()
    }


}