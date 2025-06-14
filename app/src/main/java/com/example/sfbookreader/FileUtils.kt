package com.example.sfbookreader

import android.content.Context
import android.net.Uri
import java.util.UUID

object FileUtils {
    fun parseBookFromUri(context: Context, uri: Uri): Book {
        // 这里简化处理，实际需要解析文件元数据
        return Book(
            id = UUID.randomUUID().toString(),
            title = "导入的书籍",
            coverPath = null,
            filePath = uri.toString(),
            addedDate = System.currentTimeMillis()
        )
    }
}