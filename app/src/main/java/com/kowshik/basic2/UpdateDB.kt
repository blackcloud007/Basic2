package com.kowshik.basic2

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.text.TextUtils.isEmpty
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.backendless.Backendless
import com.backendless.BackendlessUser
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault


class UpdateDB : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_updatedb)
        val db:DataBaseHelper= DataBaseHelper(this)

        val mid = findViewById<EditText>(R.id.uID)
        val mEmail = findViewById<EditText>(R.id.uemailID)
        val mFullName = findViewById<EditText>(R.id.uName)
        val mupdatebtn = findViewById<Button>(R.id.btupdate)
        val mPhone = findViewById<EditText>(R.id.uphone)
        val progessBar = findViewById<ProgressBar>(R.id.progressBar_u)

        mupdatebtn.setOnClickListener(View.OnClickListener {
            val email = mEmail.text.toString().trim { it <= ' ' }
            val fullname = mFullName.text.toString().trim()
            val phoneno = mPhone.text.toString().trim()
            val id= mid.text.toString().trim()

            if (TextUtils.isEmpty(id)) {
                mid.error = "ID is required"
                return@OnClickListener
            }
            progessBar.visibility = View.VISIBLE
            val res:Boolean= db.updatedb(id,email,fullname,phoneno)

            if (res){
                Toast.makeText(this,"User details were successfully Updated to the Database",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,MainActivity::class.java))
                progessBar.visibility = View.INVISIBLE
            }else{
                Toast.makeText(this,"ERROR..Can't Update this to Database",Toast.LENGTH_SHORT).show()
                progessBar.visibility = View.INVISIBLE
            }
        })
    }

}