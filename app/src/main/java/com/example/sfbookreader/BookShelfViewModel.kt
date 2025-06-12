class BookShelfViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: BookRepository = BookRepository(application)
    val books: LiveData<List<Book>> = repository.getAllBooks()

    fun importBook(uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            val book = FileUtils.parseBookFromUri(getApplication(), uri)
            repository.insertBook(book)
        }
    }
}