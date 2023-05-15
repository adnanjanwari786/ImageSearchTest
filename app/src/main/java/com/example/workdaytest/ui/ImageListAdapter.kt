package com.example.workdaytest.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.workdaytest.R
import com.example.workdaytest.data.Item
import com.example.workdaytest.data.ItemData

class ImageListAdapter (private val listener: ItemClickListener): RecyclerView.Adapter<ImageListAdapter.ImageViewHolder>() {

    private val items: MutableList<Item> = mutableListOf()

    fun addItems(newItems: List<Item>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }
    fun refreshItems(newItems: List<Item>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.list_item, parent, false)
        return ImageViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = items[position].data[0]
        holder.bind(item)

        holder.itemView.setOnClickListener {
            listener.onCLick(item)
        }
    }

    override fun getItemCount(): Int = items.size

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)

        fun bind(item: ItemData) {
            titleTextView.text = item.title
            descriptionTextView.text = item.description
            // Bind other data properties to respective views

            // Example: Load image using Glide library
            if (item.imageUrl.isNullOrEmpty())
                return
            Glide.with(itemView.context)
                .load(item.imageUrl)
                .into(imageView)
        }
    }

    interface ItemClickListener{
       fun onCLick(item: ItemData)
    }
}
