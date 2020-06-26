package com.kowshik.basic2

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.backendless.Backendless
import com.backendless.BackendlessUser
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault


class Login : AppCompatActivity() {
    private val TAG = this::class.qualifiedName
    companion object {
        val APP_ID = "A0A3DC8F-1A68-8FE0-FF7C-F4D8C5B5F100"
        val API_KEY = "F21045F8-BE5A-4F77-AC44-03347EDDE905"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Backendless.initApp(this@Login, APP_ID, API_KEY)

        val mcreatebtn = findViewById<TextView>(R.id.createText)
        val mEmail = findViewById<TextView>(R.id.emailid)
        val mloginbtn = findViewById<TextView>(R.id.loginbtn)
        val mPassword = findViewById<TextView>(R.id.password)
        val progessBar = findViewById<ProgressBar>(R.id.progressBar2)
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
            Backendless.UserService.login(email,password, object : AsyncCallback<BackendlessUser>{
                override fun handleFault(fault: BackendlessFault?) {

                    if (fault != null) {
                        Log.e(TAG, fault.message)
                        Toast.makeText(this@Login, "Error: "+fault.message, Toast.LENGTH_SHORT).show()
                        progessBar.visibility = View.INVISIBLE
                    }
                }

                override fun handleResponse(response: BackendlessUser?) {
                    Log.i(TAG, "User has been logged in")
                    Toast.makeText(this@Login, "Logged in sucessfully", Toast.LENGTH_SHORT).show()
                    progessBar.visibility = View.INVISIBLE
                    startActivity(Intent(applicationContext, MainActivity::class.java))

                }

            })

        })
        mcreatebtn.setOnClickListener { startActivity(Intent(applicationContext, Register::class.java)) }
    }
}