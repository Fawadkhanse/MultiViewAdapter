package com.example.multiviewadapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ItemDiffCallback : DiffUtil.ItemCallback<ItemModel>() {
    override fun areItemsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
        return oldItem.viewType == newItem.viewType
    }

    override fun areContentsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
        // Implement your content comparison logic here
        return oldItem == newItem
    }
}
class MultiViewTypeAdapter(private val onItemClick: (Int, Int) -> Unit) :
    ListAdapter<ItemModel, RecyclerView.ViewHolder>(ItemDiffCallback()) {

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_1 -> ViewHolderType1(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_view_type1, parent, false)
            )
            VIEW_TYPE_2 -> ViewHolderType2(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_view_type2, parent, false)
            )
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)

        when (holder) {
            is ViewHolderType1 -> holder.bind(item)
            is ViewHolderType2 -> holder.bind(item)
        }
    }

    inner class ViewHolderType1(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewType1: TextView = itemView.findViewById(R.id.textViewType1)

        fun bind(item: ItemModel) {
            textViewType1.text = item.title
        }
    }

    inner class ViewHolderType2(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val recyclerView: RecyclerView = itemView.findViewById(R.id.nestedRecycler)

        fun bind(item: ItemModel) {
            val layoutManager = GridLayoutManager(itemView.context, 4)
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = ImageListAdapter(item.Url) { position ->
                onItemClick(adapterPosition, position)
            }
        }
    }

    companion object {
        private const val VIEW_TYPE_1 = 1
        private const val VIEW_TYPE_2 = 2
    }
}

