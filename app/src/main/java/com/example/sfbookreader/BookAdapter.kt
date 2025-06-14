package com.example.sfbookreader

import android.graphics.Color
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

class BookAdapter(
    private val books: List<Book>,
    private val onItemClick: (Book) -> Unit,
    private val onMenuClick: (Book, View) -> Unit
) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cover: ImageView = view.findViewById(R.id.ivCover)
        val title: TextView = view.findViewById(R.id.tvTitle)
        val menuButton: ImageView = view.findViewById(R.id.btnMenu)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = books[position]

        // 设置封面
        if (book.coverPath != null) {
            Glide.with(holder.itemView.context)
                .load(File(book.coverPath))
                .into(holder.cover)
        } else {
            holder.cover.setImageResource(R.drawable.default_cover)
            holder.cover.setBackgroundColor(Color.LTGRAY)
        }

        holder.title.text = book.title
        holder.itemView.setOnClickListener { onItemClick(book) }
        holder.menuButton.setOnClickListener { onMenuClick(book, it) }
    }

    override fun getItemCount() = books.size
}