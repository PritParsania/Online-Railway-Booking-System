package com.example.kotlin_course

//import com.google.firebase.auth.ktx.auth
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_signup.*

//import com.google.firebase.ktx.Firebase

class SignupActivity : AppCompatActivity(),View.OnClickListener {
    lateinit var name: EditText
    lateinit var number: EditText
    lateinit var email: EditText
    lateinit var password: EditText
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth
    val db=FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        name = findViewById(R.id.signup_name)
        email = findViewById(R.id.signup_email)
        number = findViewById(R.id.signup_number)
        password = findViewById(R.id.signup_password)





        btn_cntWithGoog.setOnClickListener{
            signIn()
        }
        signup_button.setOnClickListener {
         //   val returned = validate()
            val firebaseAuth = FirebaseAuth.getInstance()
        //    if (returned) {
                auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                    .addOnSuccessListener {
                        Toast.makeText(this, "User Created", Toast.LENGTH_SHORT).show()

                        val user: MutableMap<String, Any> = HashMap()
                        user.put("name", name.text.toString())
                        user.put("email", email.text.toString())
                        user.put("number", number.text.toString())
                        user.put("password", password.text.toString())
                        db.collection("Users").document("email").set(user)
                            .addOnSuccessListener { documentReference ->
                                Firebase.auth.signOut()
//                                Intent(this, LoginActivity::class.java).also {
//                                    startActivity(it)
//                            }
                                val intent = Intent(this, LoginActivity::class.java)
                                startActivity(intent)
                                finish()
                        }
                  //  }
                    .addOnFailureListener {
                        Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    }
            }
        }
        auth = Firebase.auth
//        signup_button.setOnClickListener(this@SignupActivity)
    }





    fun signIn(){
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(user: FirebaseUser?) {

    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    updateUI(null)
                }
            }
    }

    private fun validate(): Boolean {
        if (signup_name.text.toString().isEmpty()) {
            signup_name.error = "Name Should not be Empty"
            return false
        }
        if (signup_number.text.toString().isEmpty()) {
            signup_number.error = "Number Should not be Empty"
            return false
        }
        if (signup_email.text.toString().isEmpty()) {
            signup_email.error = "Email Should not be Empty"
            return false
        }
        if (signup_password.text.toString().isEmpty()) {
            signup_password.error = "Password Should not be Empty"
            return false
        }
        return true
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
    companion object {
        private const val TAG = "SignupActivity"
        private const val RC_SIGN_IN = 9001
    }
}

