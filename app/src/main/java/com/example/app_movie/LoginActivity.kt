package com.example.app_movie

import android.content.Intent
import androidx.databinding.DataBindingUtil
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.app_movie.Main.MainActivity
import com.example.app_movie.Sign.SignActivity
import com.example.app_movie.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import java.lang.Exception

class LoginActivity : AppCompatActivity(), LoginNavigator {
    override fun intentSign() {
        startActivity(Intent(this, SignActivity::class.java))
        finish()
    }

    override fun intentSignin() {
        startActivity(Intent(this, SignInActivity::class.java))
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityLoginBinding>(
            this,
            R.layout.activity_login
        )
//        binding.login = LoginViewModel(this)
        binding.login=LoginViewModel(this)
        val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
        if (firebaseAuth.currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        var num = 0;
        val viewPager = findViewById<ViewPager>(R.id.view_login)
        viewPager.adapter = LoginAdapter(supportFragmentManager)
        viewPager.currentItem = 0
        val t = object : Thread() {
            override fun run() {
                while (true) {
                    try {
                        Thread.sleep(3000)
                        runOnUiThread {
                            if (num == 2) {
                                num = 0
                                viewPager.currentItem = num
                            } else {
                                num = num + 1
                                viewPager.currentItem = num
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                }
            }
        }
        t.start()
    }
}
