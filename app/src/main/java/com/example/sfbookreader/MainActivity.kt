import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sfbookreader.Book
import com.example.sfbookreader.BookAdapter
import com.example.sfbookreader.BookShelfViewModel
import com.example.sfbookreader.FileUtils
import com.example.yourapp.R
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: BookShelfViewModel
    private lateinit var binding: ActivityMainBinding
    private var backPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[BookShelfViewModel::class.java]

        setupRecyclerView()
        setupButtons()
        observeBooks()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = GridLayoutManager(this, 3)
    }

    private fun observeBooks() {
        viewModel.books.observe(this) { books ->
            binding.recyclerView.adapter = BookAdapter(books,
                { book -> openBook(book) },
                { book, view -> showBookMenu(book, view) }
            )
        }
    }

    private fun setupButtons() {
        binding.btnImport.setOnClickListener { importBook() }
        binding.btnMenu.setOnClickListener { showMainMenu(it) }
    }

    private fun importBook() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "*/*"
            putExtra(Intent.EXTRA_MIME_TYPES, arrayOf(
                "text/plain",
                "application/pdf",
                "application/epub+zip"
            ))
        }
        startActivityForResult(intent, IMPORT_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMPORT_REQUEST_CODE && resultCode == RESULT_OK) {
            data?.data?.let { uri ->
                val filePath = FileUtils.importBook(this, uri)
                filePath?.let {
                    val book = Book(
                        title = File(it).nameWithoutExtension,
                        filePath = it
                    )
                    viewModel.insert(book)
                }
            }
        }
    }

    private fun showBookMenu(book: Book, anchor: View) {
        val popup = PopupMenu(this, anchor)
        popup.menuInflater.inflate(R.menu.book_menu, popup.menu)

        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_pin -> viewModel.pinBook(book.id)
                R.id.menu_delete -> showDeleteConfirmation(book)
                R.id.menu_details -> showBookDetails(book)
            }
            true
        }
        popup.show()
    }

    private fun showDeleteConfirmation(book: Book) {
        AlertDialog.Builder(this)
            .setTitle("删除书籍")
            .setMessage("书籍文件和本地阅读进度都会被删除，你确认此操作吗？")
            .setPositiveButton("确定") { _, _ -> viewModel.deleteBook(book) }
            .setNegativeButton("取消", null)
            .show()
    }

    override fun onBackPressed() {
        if (backPressedOnce) {
            super.onBackPressed()
            return
        }

        this.backPressedOnce = true
        Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show()

        Handler(Looper.getMainLooper()).postDelayed({
            backPressedOnce = false
        }, 2000)
    }

    companion object {
        const val IMPORT_REQUEST_CODE = 1001
    }
}