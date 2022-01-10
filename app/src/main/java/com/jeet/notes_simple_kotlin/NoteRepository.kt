package com.jeet.notes_simple_kotlin

import androidx.lifecycle.LiveData

class NoteRepository(private val notesDao: NoteDAO) {

    //select....

    val allNotes: LiveData<List<Notes>> = notesDao.getAllNotes()

    suspend fun insert(notes: Notes){
        notesDao.insert(notes)
    }

    suspend fun delete(notes:Notes){
        notesDao.delete(notes)
    }
    suspend fun update(notes: Notes){
        notesDao.update(notes)
    }


}