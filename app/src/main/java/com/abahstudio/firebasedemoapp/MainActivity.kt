package com.abahstudio.firebasedemoapp

import android.R.attr
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    private var mFirebaseAnalytics: FirebaseAnalytics? = null
    private var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }

    fun buSignUpEvent(view: View) {
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()
        signUpFirebase(email,password)

    }

    fun buSignInEvent(view: View) {
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()
        signInFirebase(email,password)
    }


    fun signUpFirebase(email:String, password:String){
        mAuth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) { // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(applicationContext,"Successful Sign Up", Toast.LENGTH_LONG).show()
                    val currentUser =mAuth!!.currentUser
                    Log.d("Login:",currentUser!!.uid)
                } else { // If sign in fails, display a message to the user.
                    Toast.makeText(applicationContext,"Fail Sign Up",Toast.LENGTH_LONG).show()
                }
                // ...
            }
    }

    fun signInFirebase(email: String, password: String){
        mAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) { // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(applicationContext,"Successful login", Toast.LENGTH_LONG).show()
                    val currentUser =mAuth!!.currentUser
                    Log.d("Login:",currentUser!!.uid)
                } else { // If sign in fails, display a message to the user.
                    Toast.makeText(applicationContext,"Fail login",Toast.LENGTH_LONG).show()
                }
                // ...
            }
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth!!.currentUser
        if (currentUser!=null){
            var intent= Intent(this,Control::class.java)
//            startActivity(intent)
        }

    }



}
