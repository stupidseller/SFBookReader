import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: BookShelfViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 初始化RecyclerView
        setupRecyclerView()

        // 监听ViewModel数据变化
        viewModel.books.observe(this) { books ->
            (binding.rvBooks.adapter as BookAdapter).submitList(books)
        }

        // 右上角按钮事件
        binding.btnSearch.setOnClickListener { openSearch() }
        binding.btnImport.setOnClickListener { importBook() }
        binding.btnMenu.setOnClickListener { showGroupMenu() }
    }

    private fun setupRecyclerView() {
        binding.rvBooks.layoutManager = GridLayoutManager(this, 3) // 默认网格布局
        binding.rvBooks.adapter = BookAdapter { book -> onBookClicked(book) }
    }

    private fun importBook() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            type = "*/*"
            putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("text/plain", "application/pdf", "application/epub"))
        }
        startActivityForResult(intent, REQUEST_IMPORT_BOOK)
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
}