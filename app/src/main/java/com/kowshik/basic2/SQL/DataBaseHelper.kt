package com.kowshik.basic2.SQL

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.text.TextUtils.isEmpty

class DataBaseHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    companion object{
        val DATABASE_NAME : String ="UserDetails.db"
        val TABLE_NAME : String ="TABLE_NAME"
        val COL_1 : String="ID"
        val COL_2 : String="EmailID"
        val COL_3 : String="Name"
        val COL_4 : String="Phone_Number"
    }
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table TABLE_NAME (ID INTEGER PRIMARY KEY AUTOINCREMENT ,EmailID TEXT,Name TEXT ,Phone_Number TEXT)")
    }
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS TABLE_NAME")
    }
    fun insert_data(email : String,name : String , Ph : String) : Boolean{
        val db : SQLiteDatabase?=this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_2,email)
        contentValues.put(COL_3,name)
        contentValues.put(COL_4,Ph)
        val result : Long? = db?.insert(TABLE_NAME,null,contentValues)
        return result != (-1).toLong()
        }
    fun View_all_data() : Cursor{
        val db:SQLiteDatabase?=this.writableDatabase
        val res : Cursor = db!!.rawQuery("select * from TABLE_NAME",null)
        val args =
        return res
    }
    fun updatedb(id :String,email : String,name : String , Ph : String):Boolean{
        val db : SQLiteDatabase?=this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_1,id)
        if(!isEmpty(email))
            contentValues.put(COL_2,email)
        if(!isEmpty(name))
            contentValues.put(COL_3,name)
        if(!isEmpty(Ph))
            contentValues.put(COL_4,Ph)

        db?.update(TABLE_NAME,contentValues,"ID = ? ",arrayOf(id))
        return true
    }
    fun deletedata(id :String):Int{
        val db:SQLiteDatabase?=this.writableDatabase
        return db!!.delete(TABLE_NAME,"ID=?", arrayOf(id))
    }
    }
