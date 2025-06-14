package com.example.sfbookreader

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class BookShelfViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = BookRepository(application)
    val books = repository.allBooks

    fun deleteBook(book: Book) {
        viewModelScope.launch {
            repository.delete(book)
            // 删除实际文件
            File(book.filePath).delete()
        }
    }

    fun pinBook(bookId: Int) {
        viewModelScope.launch {
            repository.pinBook(bookId, true)
        }
    }
}