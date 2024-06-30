package com.lemien.applicationbaseadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class BaseAdapterClass(var list: ArrayList<Student>) : BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(p0: Int): Any {
        return ""
    }

    override fun getItemId(p0: Int): Long {
        return 1L
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view = LayoutInflater.from(p2?.context).inflate(R.layout.item_base_adapter,p2, false)
        val tvName = view.findViewById<TextView>(R.id.tvName)
        tvName.setText(list[p0].name)
        val tv = view.findViewById<TextView>(R.id.tv)
        tv.setText(list[p0].subject)
        val tvRollNo = view.findViewById<TextView>(R.id.tvrollno)
        tvRollNo.setText(list[p0].rollNo.toString())
       return view

}
}
