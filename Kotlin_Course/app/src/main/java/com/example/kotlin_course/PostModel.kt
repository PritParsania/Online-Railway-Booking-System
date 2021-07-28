package com.example.kotlin_course

import org.json.JSONObject

class PostModel(jsonObject : JSONObject) {
    lateinit var arrival:String
    lateinit var day:String
    lateinit var train_name:String
    lateinit var station_name:String
    lateinit var station_code:String
    lateinit var id:String
    lateinit var train_number:String
    lateinit var departure:String
    init {

        arrival = jsonObject.getString("arrival")
        day = jsonObject.getString("day")
        train_name = jsonObject.getString("train_name")
        station_name = jsonObject.getString("station_name")
        station_code = jsonObject.getString("station_code")
        id = jsonObject.getString("id")
        train_number = jsonObject.getString("train_number")
        departure = jsonObject.getString("departure")
    }
}
