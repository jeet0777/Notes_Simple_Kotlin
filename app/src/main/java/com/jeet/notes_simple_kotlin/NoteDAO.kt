package com.jeet.notes_simple_kotlin

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDAO {

@Insert(onConflict = OnConflictStrategy.IGNORE)
 fun insert(note:Notes)


@Update
 fun update(note:Notes)

@Delete
 fun delete(note:Notes)

@Query("select * from NT order by id ASC")
fun getAllNotes():LiveData<List<Notes>>

}