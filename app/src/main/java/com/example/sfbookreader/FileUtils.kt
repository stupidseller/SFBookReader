package com.example.sfbookreader

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import java.io.File
import java.io.FileOutputStream
import java.util.UUID

object FileUtils {
    fun importBook(context: Context, uri: Uri): String? {
        return try {
            val inputStream = context.contentResolver.openInputStream(uri)
            val fileName = getFileName(context, uri) ?: "book_${System.currentTimeMillis()}"
            val outputFile = File(context.filesDir, fileName)
            FileOutputStream(outputFile).use { output ->
                inputStream?.copyTo(output)
            }
            outputFile.absolutePath
        } catch (e: Exception) {
            null
        }
    }

    @SuppressLint("Range")
    private fun getFileName(context: Context, uri: Uri): String? {
        val cursor = context.contentResolver.query(uri, null, null, null, null)
        return cursor?.use {
            it.moveToFirst()
            it.getString(it.getColumnIndex(OpenableColumns.DISPLAY_NAME))
        }
    }
}