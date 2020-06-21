package com.kowshik.basic2

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val mcreatebtn = findViewById<TextView>(R.id.createText)
        val mEmail = findViewById<TextView>(R.id.emailid)
        val mloginbtn = findViewById<TextView>(R.id.loginbtn)
        val mPassword = findViewById<TextView>(R.id.password)
        val progessBar = findViewById<ProgressBar>(R.id.progressBar2)
        val fAuth = FirebaseAuth.getInstance()
        mloginbtn.setOnClickListener(View.OnClickListener {
            val email = mEmail.text.toString().trim { it <= ' ' }
            val password = mPassword.text.toString().trim { it <= ' ' }
            if (TextUtils.isEmpty(email)) {
                mEmail.error = "Email is required"
                return@OnClickListener
            }
            if (TextUtils.isEmpty(password)) {
                mPassword.error = "Password is required"
                return@OnClickListener
            }
            if (mPassword.length() < 6) {
                mPassword.error = "min 6 digits are required"
                return@OnClickListener
            }
            progessBar.visibility = View.VISIBLE
            fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(this@Login, "Logged in sucessfully", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@Login, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    progessBar.visibility = View.GONE
                }
            }
        })
        mcreatebtn.setOnClickListener { startActivity(Intent(applicationContext, Register::class.java)) }
    }
}