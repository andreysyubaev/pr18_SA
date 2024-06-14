package com.example.library

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class Book(val title: String, val author: String)

class book_form : AppCompatActivity() {

    private lateinit var titleEditText: EditText
    private lateinit var authorEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var viewBooksButton: Button
    private lateinit var sharedPreferences: SharedPreferences
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_form)

        titleEditText = findViewById(R.id.titleEditText)
        authorEditText = findViewById(R.id.authorEditText)
        saveButton = findViewById(R.id.saveButton)
        viewBooksButton = findViewById(R.id.viewBooksButton)
        sharedPreferences = getSharedPreferences("LibraryApp", MODE_PRIVATE)

        saveButton.setOnClickListener {
            val title = titleEditText.text.toString().trim()
            val author = authorEditText.text.toString().trim()

            if (title.isEmpty() || author.isEmpty()) {
                // Показать ошибку
            } else {
                val booksJson = sharedPreferences.getString("books", "[]")
                val booksType = object : TypeToken<MutableList<Book>>() {}.type
                val books: MutableList<Book> = gson.fromJson(booksJson, booksType) ?: mutableListOf()

                books.add(Book(title, author))
                val newBooksJson = gson.toJson(books)
                sharedPreferences.edit().putString("books", newBooksJson).apply()
            }
        }

        viewBooksButton.setOnClickListener {
            startActivity(Intent(this, view_books::class.java))
        }
    }
}