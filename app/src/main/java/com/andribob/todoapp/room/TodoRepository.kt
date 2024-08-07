package com.andribob.todoapp.room

import androidx.lifecycle.LiveData

class TodoRepository(private val todoDAO: TodoDAO) {
    fun getAllTodo(): LiveData<List<Todo>> {
        return todoDAO.getAllTodo()
    }

    suspend fun insert(todo: Todo) {
        return todoDAO.insert(todo)
    }

    suspend fun update(todo: Todo) {
        return todoDAO.update(todo)
    }

    suspend fun delete(todo: Todo) {
        return todoDAO.delete(todo)
    }

    suspend fun deleteAll() {
        return todoDAO.deleteAll()
    }
}