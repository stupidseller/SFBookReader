package com.example.sfbookreader

import android.content.Context
import android.net.Uri
import java.util.UUID

object FileUtils {
    fun parseBookFromUri(context: Context, uri: Uri): Book {
        // 实际项目中这里需要实现文件复制和元数据解析
        // 这里返回一个模拟的Book对象
        return Book(
            id = UUID.randomUUID().toString(),
            title = "导入的书籍",
            coverPath = null,
            filePath = uri.toString(),
            addedDate = System.currentTimeMillis()
        )
    }
}