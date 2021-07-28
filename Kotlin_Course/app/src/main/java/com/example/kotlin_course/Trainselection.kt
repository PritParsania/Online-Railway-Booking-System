package com.example.kotlin_course

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_login.*

import kotlinx.android.synthetic.main.activity_trainselection.*
import java.text.SimpleDateFormat
import java.util.*


class Trainselection : AppCompatActivity() {

//    lateinit var textView: TextView
//    lateinit var button: Button
//    var day = 0
//    var month: Int = 0
//    var year: Int = 0
//    var hour: Int = 0
//    var minute: Int = 0
//    var myDay = 0
//    var myMonth: Int = 0
//    var myYear: Int = 0
//    var myHour: Int = 0
//    var myMinute: Int = 0

    var button_date: Button? = null
    var textview_date: TextView? = null
    var cal = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trainselection)



        date.setOnClickListener {
            // get the references from layout file
            textview_date = this.text_view_date_1
            button_date = this.date
            textview_date!!.text = "--/--/----"

            // create an OnDateSetListener
            val dateSetListener = object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                       dayOfMonth: Int) {
                    cal.set(Calendar.YEAR, year)
                    cal.set(Calendar.MONTH, monthOfYear)
                    cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    updateDateInView()
                }
            }

            // when you click on the button, show DatePickerDialog that is set with OnDateSetListener
            button_date!!.setOnClickListener(object : View.OnClickListener {
                override fun onClick(view: View) {
                    DatePickerDialog(this@Trainselection,
                        dateSetListener,
                        // set DatePickerDialog to point to today's date when it loads up
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)).show()
                }

            })

        }



        back_trainselection.setOnClickListener {
            val intent=Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


        btn_submit.setOnClickListener {
            val intent=Intent(this, Ticket_CountActivity::class.java)
            startActivity(intent)
        }
        title = "Select Stations"
    }

    private fun updateDateInView() {
        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        textview_date!!.text = sdf.format(cal.getTime())
    }
}