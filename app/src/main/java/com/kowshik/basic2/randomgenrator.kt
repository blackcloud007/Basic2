package com.kowshik.basic2

import android.util.Log
import androidx.lifecycle.ViewModel
import java.util.*

class randomgenrator : ViewModel() {
    val TAG = this::class.qualifiedName
    private var randno: String? = null
    val number: String?
        get() {
            Log.i(TAG, "Rand number is set")
            if (randno == null) {
                createNumber()
            }
            return randno
        }

    fun createNumber() {
        Log.i(TAG, "rand num is created")
        val random = Random()
        randno = "Random Number is :" + (random.nextInt(10 - 1) + 1)
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "ended the app")
    }
}