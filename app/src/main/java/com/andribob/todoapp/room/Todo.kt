package com.andribob.todoapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "todo_id")
    var id: Int,
    @ColumnInfo(name = "todo_name")
    var name: String,
    @ColumnInfo(name = "todo_is_done")
    var isDone: Boolean,
)
