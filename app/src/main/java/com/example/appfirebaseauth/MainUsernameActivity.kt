package com.example.appfirebaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.appfirebaseauth.databinding.ActivityMainBinding
import com.example.appfirebaseauth.databinding.ActivityMainUsernameBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

/*
enum class ProviderType {
    Basic
}*/
class MainUsernameActivity : AppCompatActivity() {

    private lateinit var buttonLogout:Button
    private lateinit var showName:TextView
    private lateinit var showEmail:TextView

    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivityMainUsernameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_username)

        buttonLogout=findViewById(R.id.buttonLogout)
        showName=findViewById(R.id.showName)
        showEmail=findViewById(R.id.showEmail)

        database = Firebase.database.reference

        val blunde:Bundle?=intent.extras
        val email:String?=blunde?.getString("email")
        val id:String?=blunde?.getString("id")
       // val name:String?=blunde?.getString("name")
        setupProfile(email ?: "")
        //getData(id ?:"")
    }

    //load the data users
   /*private fun getData(id: String){
        database=FirebaseDatabase.getInstance().getReference("Users")
        database.child(id).get().addOnSuccessListener {

            val name=it.child("name").value
            Toast.makeText(this,"success",Toast.LENGTH_SHORT).show()
            binding.showName.text=name.toString()
            //${} ${it.child("lastName")}"
            Log.i("firebase", "Got value ${it.value}")


        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }

    }*/


    private fun setupProfile(email: String) {
        showEmail.text=email
       // showName.text=provider
        buttonLogout.setOnClickListener(){
            FirebaseAuth.getInstance().signOut()
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
            onBackPressed()
        }

    }

}

