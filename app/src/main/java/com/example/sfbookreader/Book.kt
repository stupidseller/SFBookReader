package com.example.sfbookreader

data class Book(
    val id: String,
    val title: String,
    val coverPath: String?, // 封面路径
    val filePath: String,   // 文件路径
    val addedDate: Long = System.currentTimeMillis()
)