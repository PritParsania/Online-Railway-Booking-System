package com.example.kotlin_course

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.train_single.view.*

class TrainJSONAdapter(var context: Context, var  arrList: ArrayList<PostModel>): BaseAdapter() {
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var abc = LayoutInflater.from(context).inflate(R.layout.train_single, p2, false)
        abc.arrivalTime.text =  arrList[p0].arrival
        //Toast.makeText(context, "${arrList[p0].PhoneNo is String}", Toast.LENGTH_LONG).show()
        abc.days.text = arrList[p0].day
        abc.trainName.text = arrList[p0].train_name
        abc.stationName.text = arrList[p0].station_name
        abc.stationCode.text = arrList[p0].station_code
        abc.yourId.text = arrList[p0].id
        abc.trainNumber.text = arrList[p0].train_number
        abc.departureTime.text = arrList[p0].departure

        return abc
    }

    override fun getItem(p0: Int): Any {
        return arrList.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return arrList.size
    }
}
