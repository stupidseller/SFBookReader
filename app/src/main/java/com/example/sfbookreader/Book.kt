// Book.kt
package com.example.sfbookreader

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val filePath: String,
    val coverPath: String? = null,
    val group: String = "默认分组",
    val isPinned: Boolean = false,
    val lastReadPosition: Int = 0
){

}