package com.richa.pulltorefresh

import android.content.Intent
import com.richa.pulltorefresh.RVAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView : RecyclerView = findViewById(R.id.recyclerView);
        recyclerView.layoutManager = LinearLayoutManager(this);

        //adapter and viewholder for recyclerview

        val items = fetchData()
        //set adapter
        val adapter = RVAdapter(items)
        recyclerView.adapter = adapter

        //onclick event on items
        adapter.setOnItemClickListner(object : RVAdapter.onItemClickListner {
            override fun onItemClick(position: Int) {

                Toast.makeText(applicationContext,"You clicked on item no. $position",LENGTH_LONG).show()

            }
        })

        Toast.makeText(applicationContext,"Click on any item and Please pull to refresh Data",LENGTH_LONG).show()

        // after pull refresh layout
        val swipe : SwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        swipe.setOnRefreshListener {
            //update data
            items.addAll(updateData())
            //notify when data is updatd
            adapter.notifyDataSetChanged()
            Toast.makeText(applicationContext,"Please scroll to see updated data",LENGTH_LONG).show()
            //stop refreshing when task is completed
            swipe.isRefreshing = false
        }

    }
    //fetching data

    fun fetchData() : ArrayList<String> {
        val list = ArrayList<String>()

        for (i in 0 until 16){
            list.add("Item id $i")
        }
        return list
    }

    //update data to 30 when pull to refresh action happen

    fun updateData() : ArrayList<String>{
        val item = ArrayList<String>()
        for (i in 16 until 51){
            item.add("Item id $i")
        }
        return item
    }
}