package com.example.kotlin_course

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_ticket__count.*
import org.w3c.dom.Text

class Ticket_CountActivity : AppCompatActivity() {

    lateinit var tv_amounttobePaid: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket__count)

        var sp_T: Spinner = findViewById(R.id.sp_Ticket)
        var tv_Amt = findViewById<TextView>(R.id.Amt)
        tv_amounttobePaid = findViewById(R.id.tv_totalAmount)

        sp_T.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                val a: Int = selectedItem.toInt()
                val t = tv_Amt.text.toString().toInt()
                val res = a * t;
                tv_amounttobePaid.text = res.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        btn_count_DONE.setOnClickListener {
            val intent:Intent=Intent(applicationContext,PaymentActivity::class.java)
            intent.putExtra("amount",tv_amounttobePaid.text.toString())
            startActivity(intent)
            val actionbar = supportActionBar
            actionbar!!.title = "Ticket-Count"
        }
    }
}
