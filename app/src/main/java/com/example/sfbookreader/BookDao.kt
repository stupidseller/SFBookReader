// BookDao.kt
package com.example.sfbookreader

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface BookDao {
    @Insert
    suspend fun insert(book: Book)

    @Delete
    suspend fun delete(book: Book)

    @Query("SELECT * FROM books ORDER BY isPinned DESC, title ASC")
    fun getAllBooks(): LiveData<List<Book>>

    // 修复：使用反引号转义 SQL 关键字 "group"
    @Query("SELECT * FROM books WHERE `group` = :groupName")
    fun getBooksByGroup(groupName: String): LiveData<List<Book>>

    @Update
    suspend fun update(book: Book)

    // 修复：添加正确的 getBookById 方法
    @Query("SELECT * FROM books WHERE id = :bookId")
    suspend fun getBookById(bookId: Int): Book?
}