package com.kowshik.basic2.RecyclerView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kowshik.basic2.R
import java.util.ArrayList

class Recycler_View : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler__view)

        val recyclerView=findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager =LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        val users=ArrayList<User>()

        users.add(User("Kakashi Hatake", "Fire Nation,Leaf country"))
        users.add(User("Naruto Uzumaki", "Fire Nation,Leaf country"))
        users.add(User("Sasuke", "Fire Nation,Leaf country"))
        users.add(User("Rock Lee", "Fire Nation,Leaf country"))
        users.add(User("Gaara", "Sand Nation,Earth country"))
        users.add(User("Itachi", "Fire Nation,Leaf country"))
        users.add(User("Pain", "Water Nation,Rain country"))

        val adapter = CustomAdapter(users)
        recyclerView.adapter=adapter



    }
}