// Book.kt
package com.example.sfbookreader

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(
    @PrimaryKey val id: String,
    val title: String,
    val coverPath: String?, // 封面路径
    val filePath: String,   // 文件路径
    val addedDate: Long = System.currentTimeMillis() // 添加时间戳
)