package com.andribob.todoapp.viewui


import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.recyclerview.widget.RecyclerView
import com.andribob.todoapp.R
import com.andribob.todoapp.databinding.CardItemBinding
import com.andribob.todoapp.room.Todo

class MyRecyclerView(
    private val todos: List<Todo>,
    private val deleteClickListener: (Todo) -> Unit,
    private var updateClickListener: (Todo) -> Unit
) :
    RecyclerView.Adapter<MyRecyclerView.MyViewHolder>() {

    inner class MyViewHolder(private val binding: CardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            todo: Todo,
            deleteClickListener: (Todo) -> Unit,
            updateClickListener: (Todo) -> Unit
        ) {
            binding.tvCardName.text = todo.name
            binding.btnCardDelete.setOnClickListener {
                deleteClickListener(todo)
            }

            if (todo.isDone) {
                binding.tvCardName.foreground =
                    ContextCompat.getDrawable(binding.root.context, R.drawable.strike_through);
                binding.btnCardDone.isEnabled = false
            } else {
                binding.tvCardName.foreground = null
                binding.btnCardDone.setOnClickListener {
                    updateClickListener(todo)
                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: CardItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.card_item, parent, false)
        return this.MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(todos[position], deleteClickListener, updateClickListener)
    }


}