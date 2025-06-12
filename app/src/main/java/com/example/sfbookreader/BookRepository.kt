package com.example.sfbookreader

class BookRepository(context: Context) {
    private val bookDao: BookDao = AppDatabase.getInstance(context).bookDao()

    fun getAllBooks(): LiveData<List<Book>> {
        return bookDao.getAllBooks()
    }

    suspend fun insertBook(book: Book) {
        bookDao.insert(book)
    }
}