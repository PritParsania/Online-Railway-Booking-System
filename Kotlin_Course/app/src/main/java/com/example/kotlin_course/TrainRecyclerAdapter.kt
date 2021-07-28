package com.example.kotlin_course

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.train_single.view.*

class TrainRecyclerAdapter(var context: Context, var  arrList: ArrayList<PostModel>): RecyclerView.Adapter <TrainRecyclerAdapter.TrainRecyclerViewHolder>(){
    class TrainRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainRecyclerViewHolder {
        var abc = LayoutInflater.from(context).inflate(R.layout.train_single, parent, false)
        return TrainRecyclerViewHolder(abc)
    }

    override fun onBindViewHolder(holder: TrainRecyclerViewHolder, position: Int) {
        holder.itemView.arrivalTime.text =  "Arrival Time:" + arrList[position].arrival
        //Toast.makeText(context, "${arrList[p0].PhoneNo is String}", Toast.LENGTH_LONG).show()
        holder.itemView.days.text = "Day:" + arrList[position].day
        holder.itemView.trainName.text = "Train Name:" + arrList[position].train_name
        holder.itemView.stationName.text = "Station Name:" + arrList[position].station_name
        holder.itemView.stationCode.text = "Station Code:" + arrList[position].station_code
        holder.itemView.yourId.text = "Id:" + arrList[position].id
        holder.itemView.trainNumber.text = "Train Number:" + arrList[position].train_number
        holder.itemView.departureTime.text = "Departure Time" + arrList[position].departure
    }

    override fun getItemCount(): Int {
        return arrList.size
    }
}