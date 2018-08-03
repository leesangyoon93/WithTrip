package com.sangyoon.withtrip

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.sangyoon.withtrip.ui.main.MainActivity
import com.sangyoon.withtrip.ui.signin.SignInActivity

class SplashActivity : AppCompatActivity() {

    override fun onStart() {
        super.onStart()

        intent = when(FirebaseAuth.getInstance().currentUser) {
            null -> {
                Intent(this, SignInActivity::class.java)
            }
            else -> {
                Intent(this, MainActivity::class.java)
            }
        }
        startActivity(intent)

    }
}