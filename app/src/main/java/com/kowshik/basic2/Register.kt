package com.kowshik.basic2

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.kowshik.basic2.Register
import java.util.*

class Register : AppCompatActivity() {
    var UserId = String()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val mEmail = findViewById<TextView>(R.id.emailid)
        val mFullName = findViewById<TextView>(R.id.userdisplayname)
        val mLogInbtn = findViewById<TextView>(R.id.loginText)
        val mregisterbtn = findViewById<TextView>(R.id.registerbtn)
        val mPhone = findViewById<TextView>(R.id.ph)
        val mPassword = findViewById<TextView>(R.id.password)
        val progessBar = findViewById<ProgressBar>(R.id.progressBar)
        val fAuth = FirebaseAuth.getInstance()
        val fstore = FirebaseFirestore.getInstance()

        if (fAuth.currentUser != null) {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }
        mLogInbtn.setOnClickListener(View.OnClickListener { startActivity(Intent(applicationContext, Login::class.java)) })
        mregisterbtn.setOnClickListener(View.OnClickListener {
            val email = mEmail.text.toString().trim { it <= ' ' }
            val pwd = mPassword.text.toString().trim { it <= ' ' }
            val fullname = mFullName.text.toString()
            val phoneno = mPhone.text.toString()
            if (TextUtils.isEmpty(email)) {
                mEmail.error = "Email is required "
                return@OnClickListener
            }
            if (TextUtils.isEmpty(pwd)) {
                mPassword.error = "Password required"
                return@OnClickListener
            }
            if (TextUtils.isEmpty(phoneno)) {
                mPhone.error = "Phone NO required"
                return@OnClickListener
            }
            if (TextUtils.isEmpty(fullname)) {
                mFullName.error = "Username is required"
                return@OnClickListener
            }
            if (pwd.length < 6) {
                mPassword.error = "Password length should be at least 6 "
                return@OnClickListener
            }
            progessBar.visibility = View.VISIBLE
            fAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this@Register, "User created", Toast.LENGTH_SHORT).show()
                    UserId = fAuth.currentUser!!.uid
                    val documentReference = fstore.collection("Users").document(UserId)
                    val user: MutableMap<String, Any> = HashMap()
                    user["Email"] = email
                    user["password"] = pwd
                    user["full name"] = fullname
                    user["Phone NO "] = phoneno
                    documentReference.set(user).addOnSuccessListener { Log.d(TAG, "onSuccess: User profile is created for$UserId") }
                    startActivity(Intent(applicationContext, Login::class.java))
                } else {
                    Toast.makeText(this@Register, "Error :( " + task.exception!!.message, Toast.LENGTH_SHORT).show()
                    progessBar.visibility = View.GONE
                }
            }
        }
        )
    }

    companion object {
        const val TAG = "TAG"
    }
}