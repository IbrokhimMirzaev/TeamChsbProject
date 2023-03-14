package com.example.teamchsbproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.teamchsbproject.databinding.ActivityLoginBinding
import com.example.teamchsbproject.databinding.ActivityRegisterBinding
import com.example.teamchsbproject.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    private var userList = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val shared = getSharedPreferences("login", MODE_PRIVATE)
        val gson = Gson()
        val convert = object : TypeToken<List<User>>() {}.type
        val users = shared.getString("users", "")

        binding.signInBtn.setOnClickListener {
            userList = gson.fromJson(users, convert)

            if (userList.isNotEmpty()) {
                for (user in userList) {
                    if (binding.nameInput.text.toString() == user.name && binding.passwordInput.text.toString() == user.password) {
                        Toast.makeText(this, "Successfully logged in", Toast.LENGTH_SHORT).show()
                        var intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        return@setOnClickListener
                    }
                }
            }


            Toast.makeText(this, "Name or Password is incorrect", Toast.LENGTH_SHORT).show()


        }
    }
}