package com.example.library

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class view_books : AppCompatActivity() {

    private lateinit var booksTextView: TextView
    private lateinit var sharedPreferences: SharedPreferences
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_books)

        booksTextView = findViewById(R.id.booksTextView)
        sharedPreferences = getSharedPreferences("LibraryApp", MODE_PRIVATE)

        val booksJson = sharedPreferences.getString("books", "[]")
        val booksType = object : TypeToken<List<Book>>() {}.type
        val books: List<Book> = gson.fromJson(booksJson, booksType) ?: emptyList()

        val booksText = books.joinToString(separator = "\n") { "Название: ${it.title}, Автор: ${it.author}" }
        booksTextView.text = booksText
    }
}