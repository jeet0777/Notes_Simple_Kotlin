package com.jeet.notes_simple_kotlin

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Notes::class), version = 1, exportSchema = false)
abstract class Notedatabase:RoomDatabase() {

    abstract fun getNotesDao():NoteDAO

    companion object{
        @Volatile
        private var INSTANCE:Notedatabase?=null

        fun getDatabase(context: Context):Notedatabase{
            return INSTANCE?: synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    Notedatabase::class.java,
                "note_database"
                ).build()
                INSTANCE=instance
                instance
            }
        }
    }
}