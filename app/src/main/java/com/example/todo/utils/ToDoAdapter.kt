package com.example.todo.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.EachTodoItemBinding
import com.example.todo.databinding.FragmentAddTodoPopupBinding

class ToDoAdapter(private val list: MutableList<ToDoData>) :
    RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {
    private var listener:ToDoAdapterInterface?=null
    fun setListener(listener:ToDoAdapterInterface){
        this.listener=listener
    }
    inner class ToDoViewHolder(val binding: EachTodoItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
       val binding=EachTodoItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ToDoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
       with(holder){
           with(list[position]){
               binding.todoTask.text=this.task
               binding.deleteTask.setOnClickListener{
                listener?.onDeleteTaskBtnClicked(this)
               }
               binding.editTask.setOnClickListener{
                   listener?.onEditTaskBtnClicked(this)

               }
           }
       }
    }

    override fun getItemCount(): Int {
       return list.size
    }
    interface ToDoAdapterInterface{
        fun onDeleteTaskBtnClicked(toDoData: ToDoData)
        fun onEditTaskBtnClicked(toDoData: ToDoData)
    }
}