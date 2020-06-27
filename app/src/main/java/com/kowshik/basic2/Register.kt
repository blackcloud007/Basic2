package com.kowshik.basic2

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.backendless.Backendless
import com.backendless.BackendlessUser
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault


class Register : AppCompatActivity() {
    private val TAG = this::class.qualifiedName
    companion object {
        val APP_ID = "A0A3DC8F-1A68-8FE0-FF7C-F4D8C5B5F100"
        val API_KEY = "F21045F8-BE5A-4F77-AC44-03347EDDE905"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        Backendless.initApp(this,APP_ID, API_KEY)

        val mEmail = findViewById<EditText>(R.id.emailid)
        val mFullName = findViewById<EditText>(R.id.userdisplayname)
        val mLogInbtn = findViewById<TextView>(R.id.loginText)
        val mregisterbtn = findViewById<Button>(R.id.registerbtn)
        val mPhone = findViewById<EditText>(R.id.ph)
        val mPassword = findViewById<EditText>(R.id.password)
        val progessBar = findViewById<ProgressBar>(R.id.progressBar)
        mLogInbtn.setOnClickListener { startActivity(Intent(applicationContext, Login::class.java)) }
        mregisterbtn.setOnClickListener(View.OnClickListener {
            val email = mEmail.text.toString().trim { it <= ' ' }
            val pwd = mPassword.text.toString().trim { it <= ' ' }
            val fullname = mFullName.text.toString()
            val phoneno = mPhone.text.toString()
            if (isEmpty(email)) {
                mEmail.error = "Email is required "
                return@OnClickListener
            }
            if (isEmpty(pwd)) {
                mPassword.error = "Password required"
                return@OnClickListener
            }
            if (isEmpty(phoneno)) {
                mPhone.error = "Phone NO required"
                return@OnClickListener
            }
            if (isEmpty(fullname)) {
                mFullName.error = "Username is required"
                return@OnClickListener
            }
            if (pwd.length < 6) {
                mPassword.error = "Password length should be at least 6 "
                return@OnClickListener
            }
/**************Database Local**********************************************/
            addtodb(email,fullname,phoneno)
/*****************************************************************************/
            val user = BackendlessUser()
            user.email=email
            user.password = pwd

            user.setProperty( "name", fullname );
            user.setProperty( "phoneNumber", phoneno );

            progessBar.visibility = View.VISIBLE

                Backendless.UserService.register(user, object : AsyncCallback<BackendlessUser?> {
                    override fun handleResponse(response: BackendlessUser?) {
                        progessBar.visibility = View.INVISIBLE
                        Toast.makeText(this@Register, "User has been registered", Toast.LENGTH_LONG).show()
                        startActivity(Intent(applicationContext, Login::class.java))
                    }

                    override fun handleFault(fault: BackendlessFault) {
                        progessBar.visibility = View.INVISIBLE
                        Toast.makeText(this@Register, fault.message, Toast.LENGTH_LONG).show()
                    }
                })


        })
    }
    fun addtodb(email : String, Name :String, ph:String){
        val db : DataBaseHelper = DataBaseHelper(this@Register)
        val result : Boolean = db.insert_data(email,Name,ph)
        if (result){
            Toast.makeText(this,"User details were successfully added to the Database",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"ERROR..Can't add this to Database",Toast.LENGTH_SHORT).show()
        }
    }
}