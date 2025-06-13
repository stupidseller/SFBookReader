package com.example.sfbookreader

// 添加以下导入
import android.content.Context
import androidx.lifecycle.LiveData

class BookRepository(context: Context) {
    private val bookDao: BookDao = AppDatabase.getInstance(context).bookDao()

    fun getAllBooks(): LiveData<List<Book>> {
        return bookDao.getAllBooks()
    }

    suspend fun insertBook(book: Book) {
        bookDao.insert(book)
    }
}