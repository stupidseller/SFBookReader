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

class BookShelfViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: BookRepository = BookRepository(application)
    val books: LiveData<List<Book>> = repository.getAllBooks()

    fun importBook(uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            val book = FileUtils.parseBookFromUri(getApplication(), uri)
            repository.insertBook(book)
        }
    }

    // ViewModel Factory
    class Factory(private val application: Application) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return BookShelfViewModel(application) as T
        }
    }
}