package com.example.appfirebaseauth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    //variable
    lateinit var activityRegister: Button
    lateinit var loginUser:Button

     private lateinit var inputEmail:EditText;
     private lateinit var inputPassword:EditText;
    private lateinit var database: DatabaseReference

    private lateinit var analytics: FirebaseAnalytics


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //google Analytics
        analytics = Firebase.analytics
        val bundle=Bundle()
        bundle.putString("message", "Integration firebase complete")
        analytics.logEvent("InitScreen",bundle)

       //search obj
        activityRegister=findViewById(R.id.ButtonSignup)
        loginUser=findViewById(R.id.ButtonLogin)
        inputEmail=findViewById(R.id.inputUserName)
        inputPassword=findViewById(R.id.inputPass)

        //show activity register
        activityRegister.setOnClickListener(){
            val intent=Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

        //show activity mainUser (login)
        loginAccount()

    }
   /* private fun getData(){
        database= FirebaseDatabase.getInstance().getReference("Users")
        database.child(id).get().addOnSuccessListener {

            if (it.exists()){
                val name=it.child("name").value
                Toast.makeText(this,"success", Toast.LENGTH_SHORT).show()
                binding.showName.text=name.toString()
                //${} ${it.child("lastName")}"
                Log.i("firebase", "Got value ${it.value}")

            }


        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }

    }*/


    private fun loginAccount () {
        loginUser.setOnClickListener() {
            if (validData()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    inputEmail.text.toString(),
                    inputPassword.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {

                        showProfile(it.result?.user?.email ?: "",)
                    } else {
                        showAlertAuth()
                    }

                }
            }

        }
    }

    private fun showProfile(email:String) {
        val profileIntent :Intent=Intent(this, MainUsernameActivity::class.java).apply {
            putExtra("email",email)
            //putExtra("id",id)
            // putExtra("name",name)
        }
        startActivity(profileIntent)

    }

    private fun showAlertAuth() {
        /*val builderMessage= AlertDialog.Builder(this)
        builderMessage.setTitle("Alert")
        builderMessage.setMessage("failed, wrong password or email")
        builderMessage.setPositiveButton("Accept", null)
        val message: AlertDialog =builderMessage.create()
        message.show()*/


        MaterialAlertDialogBuilder(this)
            .setTitle(resources.getString(R.string.titleAlert))
            .setMessage(resources.getString(R.string.messageErrorLogin))

            .setPositiveButton(resources.getString(R.string.AcceptError)) { dialog, which ->
                inputEmail.text.clear()
                inputPassword.text.clear()

            }
            .show()
    }

    private fun validData(): Boolean {
        var valid:Boolean;

        if (inputEmail.text.isNotEmpty()){
            valid=true
        }else{
            inputEmail.error = "enter an email";
            valid=false
        }

        if (inputPassword.text.isNotEmpty()){
            valid=true
        }else{
            inputPassword.error = "enter a password";
            valid=false
        }
        return valid

    }
}