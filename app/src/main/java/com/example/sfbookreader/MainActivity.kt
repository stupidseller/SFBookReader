package com.example.sfbookreader

// 添加这些导入
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sfbookreader.databinding.ActivityMainBinding // 确保视图绑定已启用

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: BookShelfViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 初始化视图绑定
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 初始化ViewModel
        viewModel = ViewModelProvider(
            this,
            BookShelfViewModel.Factory(application)
        ).get(BookShelfViewModel::class.java)

        // 初始化RecyclerView
        setupRecyclerView()

        // 监听ViewModel数据变化
        viewModel.books.observe(this) { books ->
            (binding.rvBooks.adapter as? BookAdapter)?.submitList(books)
        }

        // 右上角按钮事件
        binding.btnSearch.setOnClickListener { openSearch() }
        binding.btnImport.setOnClickListener { importBook() }
        binding.btnMenu.setOnClickListener { showGroupMenu() }
    }

    private fun setupRecyclerView() {
        binding.rvBooks.layoutManager = GridLayoutManager(this, 3)
        binding.rvBooks.adapter = BookAdapter { book -> onBookClicked(book) }
    }

    // 实现缺失的方法
    private fun openSearch() {
        Toast.makeText(this, "搜索功能待实现", Toast.LENGTH_SHORT).show()
    }

    private fun showGroupMenu() {
        Toast.makeText(this, "分组菜单待实现", Toast.LENGTH_SHORT).show()
    }

    private fun onBookClicked(book: Book) {
        Toast.makeText(this, "打开书籍: ${book.title}", Toast.LENGTH_SHORT).show()
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
        startActivityForResult(intent, REQUEST_IMPORT_BOOK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMPORT_BOOK && resultCode == RESULT_OK) {
            data?.data?.let { uri ->
                viewModel.importBook(uri)
            }
        }
    }

    // 双击退出实现
    private var backPressedTime = 0L
    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
        } else {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show()
            backPressedTime = System.currentTimeMillis()
        }
    }

    companion object {
        const val REQUEST_IMPORT_BOOK = 1001
    }
}