package com.example.sfbookreader

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yourapp.R
import java.io.File

class BookAdapter(private val onItemClick: (Book) -> Unit,private val onItemLongClick: (Book, View) -> Unit? = null) :
    ListAdapter<Book, BookAdapter.BookViewHolder>(BookDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = getItem(position)
        holder.bind(book)
        holder.itemView.setOnClickListener { onItemClick(book) }
    }

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleView: TextView = itemView.findViewById(R.id.tvTitle)
        private val coverView: ImageView = itemView.findViewById(R.id.ivCover)

        fun bind(book: Book) {
            titleView.text = book.title
            val coverFile = if (book.coverPath != null) File(book.coverPath) else null
            if (coverFile != null && coverFile.exists()) {
                Glide.with(itemView.context).load(coverFile).into(coverView)
            } else {
                Glide.with(itemView.context).load(R.drawable.default_cover).into(coverView)
            }
        }
    }

    class BookDiffCallback : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem == newItem
        }
    }
}