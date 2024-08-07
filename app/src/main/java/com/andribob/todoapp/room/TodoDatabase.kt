package com.andribob.todoapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Todo::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract val todoDAO: TodoDAO

    //single tone object
    companion object {
        @Volatile
        private var INSTANCE: TodoDatabase? = null
        private val lock: Any = Any()

        fun getInstance(context: Context): TodoDatabase {
            var instance: TodoDatabase? = this.INSTANCE
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    "todo_db"
                ).build()
            }

            return instance
        }

    }
}