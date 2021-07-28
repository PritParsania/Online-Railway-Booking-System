package com.example.kotlin_course

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_dashboard.*
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class DashboardActivity : AppCompatActivity() {
    var titleName: String? = "Trains"
    lateinit var sharedPreferences: SharedPreferences
    val client = OkHttpClient()
    var arrList: ArrayList<PostModel> = ArrayList()
    var persons: List<PostModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        sharedPreferences = getSharedPreferences(
            getString(R.string.preference_file_name),
            Context.MODE_PRIVATE
        )

        val jsonFileString = getJsonDataFromAsset(applicationContext, "somedata.json")

        val ss = jsonFileString!![0].toString()

        val ss2 = JSONArray(jsonFileString)

        var somedata = mutableListOf<PostModel>()

        val lengthData = ss2.length()

        for (a in 0..(lengthData-1)){
            arrList.add(PostModel(JSONObject(ss2[a].toString())))
        }
        val gson = Gson()
        val listPersonType = object : TypeToken<List<PostModel>>() {}.type

        persons = gson.fromJson(jsonFileString, listPersonType)

            titleName = sharedPreferences.getString("Title", "Online Ticket Booking For Railways")
            title = titleName

        val obj_adapter = TrainRecyclerAdapter(applicationContext, arrList)
        list_item.adapter = obj_adapter
        list_item.layoutManager = LinearLayoutManager(this)
    }

    }

        fun getJsonDataFromAsset(applicationContext: Context?, s: String): String? {
            val jsonString: String
            try {
                jsonString =
                    applicationContext!!.assets.open(s).bufferedReader().use { it.readText() }
            } catch (ioException: IOException) {
                ioException.printStackTrace()
                return null
            }
            return jsonString
        }