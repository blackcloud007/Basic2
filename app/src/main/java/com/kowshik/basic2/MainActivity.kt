package com.kowshik.basic2

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.backendless.Backendless
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault
import com.google.android.material.navigation.NavigationView
import com.kowshik.basic2.RecyclerView.Recycler_View
import com.kowshik.basic2.SQL.DataBaseHelper
import com.kowshik.basic2.SQL.DeleteDB
import com.kowshik.basic2.SQL.UpdateDB
import com.kowshik.basic2.Starters.Login


class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    private val TAG = this@MainActivity::class.qualifiedName

    companion object {
        val APP_ID = "A0A3DC8F-1A68-8FE0-FF7C-F4D8C5B5F100"
        val API_KEY = "F21045F8-BE5A-4F77-AC44-03347EDDE905"
    }

    private fun setNavigationViewListener(){
        val navigationView=findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Backendless.initApp(this@MainActivity, APP_ID, API_KEY)
        val drawerLayout=findViewById<DrawerLayout>(R.id.drawerlayout)
        setNavigationViewListener()
        val brecyclerview :Button=findViewById(R.id.bt_recyclerview)
        val bfrag1 = findViewById<Button>(R.id.b_frag1)
        val bfrag2 = findViewById<Button>(R.id.b_frag2)

        val fragment1: Fragment = Fragment_1()
        val fragment2: Fragment = Fragment_2()
        supportFragmentManager.beginTransaction().replace(R.id.flfragment, fragment1).commit()
        bfrag1.setOnClickListener { supportFragmentManager.beginTransaction().replace(R.id.flfragment, fragment1).commit() }
        bfrag2.setOnClickListener { supportFragmentManager.beginTransaction().replace(R.id.flfragment, fragment2).commit() }


        brecyclerview.setOnClickListener { startActivity(Intent(this, Recycler_View::class.java)) }

        ViewDatabase()
        UpdateDatabase()
        DeleteDatabase()

        val Randomnumber: TextView = findViewById(R.id.randomNo);
        val mydata = ViewModelProvider(this@MainActivity)[randomgenrator::class.java]
        Randomnumber.text = mydata.number;
        Log.i(TAG, "Random number set")
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val drawerLayout=findViewById<DrawerLayout>(R.id.drawerlayout)
        when (item.itemId) {
            R.id.logout -> {
                val isValidLogin = Backendless.UserService.isValidLogin
                Log.i(TAG, "Is user logged in? - $isValidLogin")
                if (isValidLogin) {
                    Backendless.UserService.logout(object : AsyncCallback<Void?> {
                        override fun handleResponse(response: Void?) {
                            Log.i(TAG, "Logging user out")
                            Toast.makeText(applicationContext, "Logging user out", Toast.LENGTH_LONG).show()
                            startActivity(Intent(this@MainActivity, Login::class.java))
                        }

                        override fun handleFault(fault: BackendlessFault) {
                            Log.i(TAG, "Error in logging out")
                        }
                    })


                } else {
                    Log.i(TAG, "User already logged out ")
                    Toast.makeText(applicationContext, "User already logged out", Toast.LENGTH_LONG).show()

                }
            }
            else -> {
                Toast.makeText(applicationContext, "coming soon", Toast.LENGTH_SHORT).show()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun ViewDatabase(){
        val bDatabaseView:Button= findViewById<Button>(R.id.viewdatabase)
        val db: DataBaseHelper = DataBaseHelper(this)
        bDatabaseView.setOnClickListener {
           val res:Cursor = db.View_all_data()
            if (res.count==0){
                showMessage("ERROR","No data is found")
            }
            else{
                val details:StringBuffer= StringBuffer()
                while(res.moveToNext())
                {
                    details.append("ID :"+res.getString(0)+"\n")
                    details.append("EMAIL :"+res.getString(1)+"\n")
                    details.append("NAME :"+res.getString(2)+"\n")
                    details.append("PHONE :"+res.getString(3)+"\n\n")
                }
                showMessage("FOUND!!",details.toString())
            }

        }
    }
    fun showMessage(Title: String,Message: String){
        val builder:AlertDialog.Builder=AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle(Title)
        builder.setMessage(Message)
        builder.show()
    }
    fun UpdateDatabase(){
        val btupdate:Button= findViewById<Button>(R.id.btupdatedb)
        btupdate.setOnClickListener {
            startActivity(Intent(this, UpdateDB::class.java))
        }
    }
    fun DeleteDatabase(){
        val btdelete:Button= findViewById<Button>(R.id.btdelete)
        btdelete.setOnClickListener {
            startActivity(Intent(this, DeleteDB::class.java))
        }
    }
}
