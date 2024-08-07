package com.andribob.todoapp.viewmodel

import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andribob.todoapp.room.Todo
import com.andribob.todoapp.room.TodoRepository
import kotlinx.coroutines.launch

class TodoViewModel(private val todoRepository: TodoRepository) : ViewModel(), Observable {

    val todos: LiveData<List<Todo>> = todoRepository.getAllTodo()
    val inputNameTodo: MutableLiveData<String> = MutableLiveData()

    fun saveTodoHandle() {
        if (inputNameTodo.value != null && inputNameTodo.value!!.isNotBlank()) {
            insert(Todo(0, inputNameTodo.value!!, false))
            inputNameTodo.value = null
        }
    }

    fun isDoneHandle(todo: Todo) {
        var newTodo: Todo = todo
        newTodo.isDone = true
        update(newTodo)
    }

    fun deleteTodoHandle(todo: Todo) {
        delete(todo)
    }

    fun deleteAllTodoHandle() {
        deleteAll()
    }


    private fun insert(todo: Todo) = viewModelScope.launch {
        todoRepository.insert(todo)
    }

    private fun update(todo: Todo) = viewModelScope.launch {
        todoRepository.update(todo)
    }

    private fun delete(todo: Todo) = viewModelScope.launch {
        todoRepository.delete(todo)
    }

    private fun deleteAll() = viewModelScope.launch {
        todoRepository.deleteAll()
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}