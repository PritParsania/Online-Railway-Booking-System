package com.example.kotlin_course

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONObject
import java.io.InputStream

private val InputStream.codestormx: Any
    get() {
        TODO("Not yet implemented")
    }

class PaymentActivity : AppCompatActivity(), PaymentResultListener {

    var amountTotal:Double=0.0
    val TAG:String = com.example.kotlin_course.PaymentActivity::class.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        Checkout.preload(applicationContext)

        val amount: String? = intent.getStringExtra("amount")
        val amountGiven=findViewById<EditText>(R.id.amount)
        val buttonPay=findViewById<Button>(R.id.payBtn)

        amountGiven.setText(amount.toString())

        buttonPay.setOnClickListener {
            var amount=amountGiven.text.toString()
            if(amount!=""){
                amountTotal=amount.toDouble()
                startPayment()
            }
        }

        val actionbar = supportActionBar
        actionbar!!.title = "Payment"
        val actionBar: ActionBar? = supportActionBar
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun startPayment() {
/*
* You need to pass current activity in order to let Razorpay create CheckoutActivity
* */
        val activity: Activity = this
        val co = Checkout()

        try {
            val options = JSONObject()
            options.put("name", "App name")
            options.put("description", "Demo payment")
//You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png")
            options.put("currency", "INR")
            options.put("amount", amountTotal * 100)

            val prefill = JSONObject()
            prefill.put("email", "test@razorpay.com")
            prefill.put("contact", "9876543210")

            options.put("prefill", prefill)
            co.open(activity, options)
        } catch (e: Exception) {
            Toast.makeText(activity, "Error in payment: " + e.message, Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    override fun onPaymentError(errorCode: Int, response: String?) {
        try{
            startActivity(Intent(this,FailureActivity::class.java))
            finish()
//            Toast.makeText(this,"Payment failed $errorCode \n $response",Toast.LENGTH_LONG).show()
        }catch (e: Exception){
            Log.e(TAG,"Exception in onPaymentSuccess", e)
        }
    }

    override fun onPaymentSuccess(razorpayPaymentId: String?) {
        try{
            startActivity(Intent(this,SuccessActivity::class.java))
            finish()
//            Toast.makeText(this,"Payment Successful $razorpayPaymentId",Toast.LENGTH_LONG).show()
        }catch (e: Exception){
            Log.e(TAG,"Exception in onPaymentSuccess", e)
        }
    }

//        var card_btn=findViewById<RadioButton>(R.id.rb_DebitCard)
//        var upi=findViewById<RadioButton>(R.id.rb_bhimUpi)
//
//
//        title = "Payment Section"
//
//
//        upi.setOnClickListener()
//        {
//
//            UPI.setVisibility(View.GONE);
//            UPI.setVisibility(View.VISIBLE);
//
//            ID.setVisibility(View.GONE);
//            ID.setVisibility(View.VISIBLE);
//
//        }
//        card_btn.setOnClickListener()
//        {
//            tv_cardNumber.setVisibility(View.GONE);
//            tv_cardNumber.setVisibility(View.VISIBLE);
//
//            textView2.setVisibility(View.GONE);
//            textView2.setVisibility(View.VISIBLE);
//
//            tv_Expiry.setVisibility(View.GONE);
//            tv_Expiry.setVisibility(View.VISIBLE);
//
//            sp_ExpiryMonth.setVisibility(View.GONE);
//            sp_ExpiryMonth.setVisibility(View.VISIBLE);
//
//            sp_ExpiryYear.setVisibility(View.GONE);
//            sp_ExpiryYear.setVisibility(View.VISIBLE);
//
//            et_CVV.setVisibility(View.GONE);
//            et_CVV.setVisibility(View.VISIBLE);
//
//            et_cardNumber.setVisibility(View.GONE);
//            et_cardNumber.setVisibility(View.VISIBLE);
//        }




    }