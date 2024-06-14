package com.example.library

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class Welcome : AppCompatActivity() {

    private lateinit var loginEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signInButton: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        loginEditText = findViewById(R.id.login)
        passwordEditText = findViewById(R.id.password)
        signInButton = findViewById(R.id.signin)
        sharedPreferences = getSharedPreferences("LibraryApp", MODE_PRIVATE)

        signInButton.setOnClickListener {
            val login = loginEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (login.isEmpty()) {
                Toast.makeText(this, "Введите логин", Toast.LENGTH_SHORT).show()
            }
            else if (password.isEmpty()) {
                Toast.makeText(this, "Введите пароль", Toast.LENGTH_SHORT).show()
            }
            else
            {
                sharedPreferences.edit().putString("login", login).apply()
                sharedPreferences.edit().putString("password", password).apply()
                startActivity(Intent(this, book_form::class.java))
            }
        }
    }
}