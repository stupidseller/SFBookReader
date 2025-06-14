package com.example.sfbookreader

// 添加以下导入
import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BookRepository(context: Context) {
    private val bookDao: BookDao = AppDatabase.getInstance(context).bookDao()

    val allBooks: LiveData<List<Book>> = bookDao.getAllBooks()

    suspend fun insert(book: Book) = withContext(Dispatchers.IO) {
        bookDao.insert(book)
    }

    suspend fun delete(book: Book) = withContext(Dispatchers.IO) {
        bookDao.delete(book)
    }

    suspend fun pinBook(bookId: Int, pin: Boolean) = withContext(Dispatchers.IO) {
        val book = bookDao.getBookById(bookId)
        book?.let {
            val updatedBook = it.copy(isPinned = pin)
            bookDao.update(updatedBook)
        }
    }
}