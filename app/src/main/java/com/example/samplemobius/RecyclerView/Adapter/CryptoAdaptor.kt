package com.example.samplemobius.RecyclerView.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.samplemobius.R

class CryptoAdaptor:ListAdapter<ViewItem,CryptoAdaptor.ProgrammingViewHolder>(DiffUtil()) {
    class ProgrammingViewHolder(view: View): RecyclerView.ViewHolder(view){
        val currency_symbol=view.findViewById<TextView>(R.id.currency_symbol)
        val currency_name=view.findViewById<TextView>(R.id.currency_name)
        val currency_rate=view.findViewById<TextView>(R.id.currency_rate)

        fun bind(item:ViewItem){
            //TODO
            currency_symbol.text=item.symbol
            currency_name.text=item.name
            currency_rate.text=item.priceUsd

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