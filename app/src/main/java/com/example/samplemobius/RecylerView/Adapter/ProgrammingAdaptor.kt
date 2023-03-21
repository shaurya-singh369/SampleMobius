package com.example.samplemobius.RecylerView.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.samplemobius.R

class ProgrammingAdaptor:ListAdapter<ViewItem,ProgrammingAdaptor.ProgrammingViewHolder>(DiffUtil()) {
    class ProgrammingViewHolder(view: View): RecyclerView.ViewHolder(view){
        val name=view.findViewById<TextView>(R.id.name)
        val count=view.findViewById<TextView>(R.id.count)
        fun bind(item:ViewItem){
            //TODO
            name.text=item.name
            count.text=item.count.toString()

            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgrammingViewHolder {

        val view= LayoutInflater.from(parent.context).inflate(R.layout.view_item,parent,false)
        return ProgrammingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProgrammingViewHolder, position: Int) {
       val item=getItem(position)
        holder.bind(item)
    }

    class DiffUtil:androidx.recyclerview.widget.DiffUtil.ItemCallback<ViewItem>(){
        override fun areItemsTheSame(oldItem: ViewItem, newItem: ViewItem): Boolean {
            return oldItem.name==newItem.name
        }

        override fun areContentsTheSame(oldItem: ViewItem, newItem: ViewItem): Boolean {
            return oldItem==newItem
        }
    }
}