package com.example.kotlin_course

import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.MenuItem
import android.widget.*
import kotlinx.android.synthetic.main.activity_login.*
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signup.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase



class LoginActivity : AppCompatActivity() {
    lateinit var et1: EditText
    lateinit var et2: EditText
    lateinit var btn1: Button
    lateinit var tv1: TextView
    lateinit var tv2: TextView

    private lateinit var auth: FirebaseAuth

    private var customToken: String? = null
// ...
// Initialize Firebase Auth

    lateinit var toggle: ActionBarDrawerToggle

    var img7:ImageView?=null
//    val validMobileNumber = arrayOf("8460642744", "9824399737")
//    val validPassword = arrayOf("Nilay", "Prit")

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("MainActivity","onCreate Called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        img7=findViewById(R.id.images)

       // supportActionBar?.hide()
        auth = Firebase.auth
//        if(auth.currentUser !=null){
//            Intent(this, DashboardActivity::class.java).also {
//                startActivity(it)
//            }
//        }

        val FirebaseAuth = FirebaseAuth.getInstance()
        btn_train_info.setOnClickListener {
            Intent(this, DashboardActivity::class.java).also {
                startActivity(it)
            }
        }
        btn_login.setOnClickListener {
            FirebaseAuth.signInWithEmailAndPassword(et1.text.toString(), et2.text.toString())
                .addOnSuccessListener {
                    Intent(this, Trainselection::class.java).also {
                        startActivity(it)
                        Toast.makeText(this@LoginActivity,"User is Logged in",Toast.LENGTH_SHORT).show()
                        }
                }.addOnFailureListener {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
        }

        toggle = ActionBarDrawerToggle(this,drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.admin -> {
                    Toast.makeText(applicationContext,"Admin Panel",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@LoginActivity, AdminActivity::class.java)
                    startActivity(intent)
                }

                R.id.myProfile -> {
                    Toast.makeText(applicationContext,"Profile",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@LoginActivity, ProfileActivity::class.java)
                    startActivity(intent)
                }

                R.id.aboutUs -> {
                    Toast.makeText(applicationContext,"About Us",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@LoginActivity, AboutUsActivity::class.java)
                    startActivity(intent)
                }

//                R.id.logout -> {
//                   FirebaseAuth.getInstance().signOut();
//                    finishAffinity();
//                }
            }
            true
        }
        val ad1 = resources.getDrawable(R.drawable.animation_list) as AnimationDrawable
        img7!!.setBackground(ad1)
        ad1.start()

        var previousMenuItem:MenuItem? = null

        title = "Log In"

        et1 = findViewById(R.id.et_login_email)
        et2 = findViewById(R.id.et_pass_login)
        btn1 = findViewById(R.id.btn_login)
        tv1 = findViewById(R.id.tv_forgot)
        tv2 = findViewById(R.id.tv_destination)

        tv1.setOnClickListener{

        }

        tv2.setOnClickListener{
            val intent = Intent(this@LoginActivity, SignupActivity::class.java)
            startActivity(intent)
        }
        tv_forgot.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Forgot Password")
            val view = layoutInflater.inflate(R.layout.dialog_forgot_password,null)
            val username = view.findViewById<EditText>(R.id.et_userName)
            builder.setView(view)
            builder.setPositiveButton("Reset",DialogInterface.OnClickListener{_, _ ->
                forgotPassword(username)
            })
            builder.setNegativeButton("Cancel",DialogInterface.OnClickListener{_, _ -> })
            builder.show()
        }

        Log.d("MainActivity","onCreate Ends")
    }
        // [END sign_in_custom]
    private fun forgotPassword(username : EditText){
        if(username.text.toString().isEmpty()){
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(username.text.toString()).matches()){
            return
        }
            Firebase.auth.sendPasswordResetEmail(username.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this@LoginActivity,"Email sent.",Toast.LENGTH_SHORT).show()
                    }
                }
    }
    companion object {
        private const val TAG = "LoginActivity"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}
