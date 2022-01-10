package com.jeet.notes_simple_kotlin

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "NT")
class Notes (@ColumnInfo(name = "title")val noteTitle:String,
             @ColumnInfo(name = "description")val decs:String,
            @ColumnInfo(name = "timestamp")val timestamp: String)
{

    @PrimaryKey(autoGenerate = true)
    var id=0


}