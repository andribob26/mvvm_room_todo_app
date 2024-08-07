package com.andribob.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.andribob.todoapp.databinding.ActivityMainBinding
import com.andribob.todoapp.room.Todo
import com.andribob.todoapp.room.TodoDAO
import com.andribob.todoapp.room.TodoDatabase
import com.andribob.todoapp.room.TodoRepository
import com.andribob.todoapp.viewmodel.TodoViewModel
import com.andribob.todoapp.viewmodel.TodoViewModelFactory
import com.andribob.todoapp.viewui.MyRecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var todoViewModelFactory: TodoViewModelFactory
    lateinit var todoViewModel: TodoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val todoDAO: TodoDAO = TodoDatabase.getInstance(this).todoDAO
        val todoRepository: TodoRepository = TodoRepository(todoDAO)
        todoViewModelFactory = TodoViewModelFactory(todoRepository)

        todoViewModel = ViewModelProvider(this, todoViewModelFactory)[TodoViewModel::class.java]

        binding.todoViewModel = todoViewModel
        binding.lifecycleOwner = this

        initMyRecyclerView()

    }

    private fun initMyRecyclerView() {
        binding.rvTodoList.layoutManager = LinearLayoutManager(this)
        todoViewModel.todos.observe(this, Observer {
            binding.rvTodoList.adapter = MyRecyclerView(it.reversed(), deleteCardItem, isDoneCardItem)
        })
    }

    private val deleteCardItem: (Todo) -> Unit = {
        todoViewModel.deleteTodoHandle(it)
    }

    private val isDoneCardItem: (Todo) -> Unit = {
        todoViewModel.isDoneHandle(it)
    }
}