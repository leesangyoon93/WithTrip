package com.sangyoon.withtrip.ui.signin

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.sangyoon.withtrip.R
import com.sangyoon.withtrip.model.User
import com.sangyoon.withtrip.ui.main.MainActivity


class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        supportFragmentManager.beginTransaction()
                .replace(R.id.container, SignInFragment.newInstance())
                .commitNow()
    }

    fun moveNextFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                .replace(R.id.container, fragment)
                .commit()
    }

    fun movePrevFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit, R.anim.enter, R.anim.exit)
                .replace(R.id.container, fragment)
                .commit()
    }

    val db = FirebaseFirestore.getInstance()

    fun googleSignInComplete(user: FirebaseUser) {
        val docRef = db.collection("users").document(user.uid)
        docRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document.exists()) {
                    signInComplete(User(document.id, document["name"] as String, document["email"] as String,
                            document["tel"] as String, document["gender"] as String))
                } else {
                    val newUser = User(user.uid, user.displayName ?: "", user.email ?: "", "", "")
                    val extraInfoFragment = ExtraInfoFragment.newInstance()
                    extraInfoFragment.newUser = newUser
                    moveNextFragment(extraInfoFragment)
                }
            } else {
                // 쿼리 실패
            }
        }
    }

    fun signInComplete(user : User) {
        db.collection("users").document(user.id).set(user).addOnCompleteListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

}
