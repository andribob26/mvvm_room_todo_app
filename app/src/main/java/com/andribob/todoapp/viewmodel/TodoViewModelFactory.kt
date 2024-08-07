package com.andribob.todoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andribob.todoapp.room.TodoRepository

class TodoViewModelFactory(private val todoRepository: TodoRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            return TodoViewModel(todoRepository) as T
        }

        throw IllegalArgumentException("Tidak ada class ViewModel")
    }
}