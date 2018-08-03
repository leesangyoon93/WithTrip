package com.sangyoon.withtrip.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sangyoon.withtrip.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
    }

    fun signOut() {
        finish()
    }
}