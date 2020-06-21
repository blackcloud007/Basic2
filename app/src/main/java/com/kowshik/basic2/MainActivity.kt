package com.kowshik.basic2

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bfrag1 = findViewById<Button>(R.id.b_frag1)
        val bfrag2 = findViewById<Button>(R.id.b_frag2)
        val fragment1: Fragment = Fragment_1()
        val fragment2: Fragment = Fragment_2()
        bfrag1.setOnClickListener { supportFragmentManager.beginTransaction().replace(R.id.flfragment, fragment1).commit() }
        bfrag2.setOnClickListener { supportFragmentManager.beginTransaction().replace(R.id.flfragment, fragment2).commit() }
    }
}