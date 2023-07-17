package com.example.multiviewadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.recyler)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val itemList = listOf(
            ItemModel(1, "Imran khan"),
            ItemModel(2, "",  arrayListOf("https://source.unsplash.com/user/c_v_r/1900x800",
                "https://source.unsplash.com/user/c_v_r/1900x800",
                "https://source.unsplash.com/user/c_v_r/1900x800",
                "https://source.unsplash.com/user/c_v_r/1900x800",
            )),
            ItemModel(1, "Imran khan2"),
            ItemModel(2, "",  arrayListOf("https://source.unsplash.com/user/c_v_r/1900x800",
                "https://source.unsplash.com/user/c_v_r/1900x800",
                "https://source.unsplash.com/user/c_v_r/1900x800",
                "https://source.unsplash.com/user/c_v_r/1900x800",
            )),
            ItemModel(1, "Imran khan3"),
            // Add more items as needed
            ItemModel(2, "",  arrayListOf("https://source.unsplash.com/user/c_v_r/1900x800",
            )),
            // Add more items as needed
        )

        val adapter = MultiViewTypeAdapter(){mutltiviewPosition,ClickedPosition->
            Toast.makeText(this, "multiView ${itemList[mutltiviewPosition].viewType} image Position${itemList[mutltiviewPosition].Url[ClickedPosition]} ", Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = adapter

        adapter.submitList(itemList)
    }
}