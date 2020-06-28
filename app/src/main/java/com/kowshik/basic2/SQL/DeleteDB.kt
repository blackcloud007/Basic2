package com.kowshik.basic2.SQL

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.kowshik.basic2.MainActivity
import com.kowshik.basic2.R

class DeleteDB : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_d_b)

        val db: DataBaseHelper = DataBaseHelper(this)

        val mid = findViewById<EditText>(R.id.dID)
        val mdeletebtn = findViewById<Button>(R.id.btdelete)
        val progessBar = findViewById<ProgressBar>(R.id.progressBar_d)

        mdeletebtn.setOnClickListener {
            val id= mid.text.toString().trim()

            if (TextUtils.isEmpty(id)) {
                mid.error = "ID is required"
                return@setOnClickListener
            }
            progessBar.visibility = View.VISIBLE
            val res:Int = db.deletedata(id)
            if(res>0){
                Toast.makeText(this,"$res User(s) details were successfully Deleted", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                progessBar.visibility = View.INVISIBLE
            }else{
                Toast.makeText(this,"ERROR..Can't Delete the record!", Toast.LENGTH_SHORT).show()
                progessBar.visibility = View.INVISIBLE
            }
        }

    }
}