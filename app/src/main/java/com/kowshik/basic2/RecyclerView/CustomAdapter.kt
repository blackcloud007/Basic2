package com.kowshik.basic2.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kowshik.basic2.R

class CustomAdapter(val Userlist : ArrayList<User>):RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textviewname = itemView.findViewById<TextView>(R.id.textviewname)
        val textviewaddress=itemView.findViewById<TextView>(R.id.address)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =LayoutInflater.from(parent?.context).inflate(R.layout.list_layout,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
       return Userlist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user =Userlist[position]
        holder?.textviewname?.text =   user.name
        holder?.textviewaddress?.text = user.address
    }
}