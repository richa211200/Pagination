package com.richa.pulltorefresh

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class RVAdapter(private val items:ArrayList<String>): RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    lateinit var mListener: onItemClickListner

    interface onItemClickListner {

        fun onItemClick(position: Int)
    }

    fun setOnItemClickListner(listner: onItemClickListner){
        //assigning listner argument to our local variable
        mListener = listner
    }

    class ViewHolder(itemView: View,listner: onItemClickListner) : RecyclerView.ViewHolder(itemView){

            val textView:TextView = itemView.findViewById(R.id.text)

        init {
            itemView.setOnClickListener{
                listner.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //cretae viewholder everytime it needed
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemlayout_rv,parent,false)
        return ViewHolder(view,mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //binding daa with viewholder
        //arrraylist for stirng
        holder.textView.text = items[position]
    }

    override fun getItemCount(): Int {
       return items.size
    }


}