package com.example.appfirebaseauth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class RegisterActivity : AppCompatActivity() {

    //variable declare
    private lateinit var registerUser:Button;   private lateinit var cancelRegister:Button;
    private lateinit var inputName:EditText;    private lateinit var inputLastname :EditText;
    private lateinit var inputEmail :EditText;  private lateinit var inputPhone :EditText;
    private lateinit var inputPassword :EditText; private lateinit var inputDate :EditText;
    private lateinit var inputCountry :EditText;   private lateinit var inputCity :EditText;
    private lateinit var inputAddress :EditText;   private lateinit var radioGender:TextInputLayout;
    private lateinit var inputGender:EditText;
    //variable the inputMaterialDesign
    private lateinit var layoutName:TextInputLayout;    private lateinit var layoutLastname:TextInputLayout;
    private lateinit var layoutEmail:TextInputLayout;   private lateinit var layoutPassword:TextInputLayout;
    private lateinit var layoutPhone:TextInputLayout;   private lateinit var layoutDate:TextInputLayout;
    private lateinit var layoutCountry:TextInputLayout; private lateinit var layoutCity:TextInputLayout;
    private lateinit var layoutAddress:TextInputLayout;

    //variable of db
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        //..Firebase realTime
        database = Firebase.database.reference
        title="Create new account";

      /*  val textView = findViewById<AutoCompleteTextView>(R.id.selectGender)
        val genders: Array<out String> = resources.getStringArray(R.array.gender)
        ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, genders)
            .also { adapter ->
            textView.setAdapter(adapter)
        }*/
        searchInput()
        registerCount()
        cancelRegister.setOnClickListener(){

        }

    }



    //register user
    private fun registerCount () {
        registerUser.setOnClickListener() {
            if (validData()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    inputEmail.text.toString(),
                    inputPassword.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        //save of data user
                       saveUsers()
                        //show activity profile
                        val id=inputPhone.text.toString()
                        showProfile(it.result?.user?.email ?: "",id)

                    } else {
                        showAlertAuth()
                    }

                }
            }

        }
    }

    //save of data users
    private  fun saveUsers(){

        database=FirebaseDatabase.getInstance().getReference("Users")
        database.child(inputLastname.text.toString()).setValue(Users(
            name = inputName.text.toString(),
            lastName = inputLastname.text.toString(),
            phone = inputPhone.text.toString(),
            gender = inputGender.text.toString(),
            dateOfBirth =inputDate.text.toString(),
            country = inputCountry.text.toString(),
            city = inputCity.text.toString(),
            address = inputAddress.text.toString(),
            email = inputEmail.text.toString(),
            id = inputPhone.text.toString()+inputName.text.toString()


        ))
    }
    //valid of data
    private fun validData():Boolean{
        var valid:Boolean=true;

        if(inputName.text.isEmpty()){
            layoutName.error = getString(R.string.messageError)
            valid=false
        }else{
           layoutName.error = null
            valid=true
        }

        if(inputLastname.text.isEmpty()){
            layoutLastname.error = getString(R.string.messageError)
            valid=false
        }else{
            layoutLastname.error = null
            valid=true
        }
        if(inputEmail.text.isEmpty()){
            layoutEmail.error = getString(R.string.messageError)
            valid=false
        }else{
           layoutEmail.error = null
            valid=true
        }

        if(inputPassword.text.isEmpty()){
            layoutPassword.error = getString(R.string.messageError)
            valid=false
        }else{
            layoutPassword.error = null
            valid=true
        }
        if(inputDate.text.isEmpty()){
            layoutDate.error = getString(R.string.messageError)
            valid=false
        }else{
            layoutDate.error = null
            valid=true
        }

        if(inputCountry.text.isEmpty()){
            layoutCountry.error = getString(R.string.messageError)
            valid=false
        }else{
            layoutCountry.error = null
            valid=true
        }

        if(inputCity.text.isEmpty()){
            layoutCity.error = getString(R.string.messageError)
            valid=false
        }else{
            layoutCity.error = null
            valid=true
        }

        if(inputAddress.text.isEmpty()){
            layoutAddress.error = getString(R.string.messageError)
            valid=false
        }else{
            layoutAddress.error = null
            valid=true
        }
        if(inputGender.text.isEmpty()){
            radioGender.error = getString(R.string.messageError)
            valid=false
        }else{
            radioGender.error = null
            valid=true
        }


        return valid
    }

    //search the obj
    private fun searchInput(){
        registerUser=findViewById(R.id.buttonSave);
        cancelRegister=findViewById(R.id.buttonCancel);

        inputName=findViewById(R.id.inputName);
        inputLastname=findViewById(R.id.inputLastName);
        inputEmail=findViewById(R.id.inputEmail);
        inputPassword=findViewById(R.id.inputPassword);
        inputPhone=findViewById(R.id.inputPhone);
        inputDate=findViewById(R.id.inputDate);
        inputCountry=findViewById(R.id.inputCountry);
        inputCity=findViewById(R.id.inputCity);
        inputAddress=findViewById(R.id.inputAddress);
        radioGender=findViewById(R.id.radioGender);
        inputGender=findViewById(R.id.selectGender);


        //search layout of input
        layoutName=findViewById(R.id.layoutName);
        layoutLastname=findViewById(R.id.layoutLastname);
        layoutEmail=findViewById(R.id.layoutEmail);
        layoutPassword=findViewById(R.id.inputPass);
        layoutPhone=findViewById(R.id.layoutPhone);
        layoutCountry=findViewById(R.id.layoutCountry);
        layoutAddress=findViewById(R.id.layoutAddress);
        layoutDate=findViewById(R.id.layoutDate);
        layoutCity=findViewById(R.id.layoutcity);

        
    }

    //show error the register failed
    private fun showAlertAuth(){
        val builderMessage=AlertDialog.Builder(this)
        builderMessage.setTitle("Alert error")
        builderMessage.setMessage("an error occurred while authenticating the user")
        builderMessage.setPositiveButton("Accept", null)
        val message:AlertDialog=builderMessage.create()
        message.show()

    }


    //show profile of user
    private fun showProfile(email:String, id:String){
        val profileIntent :Intent=Intent(this, MainUsernameActivity::class.java).apply {
            putExtra("email",email)
            putExtra("id",id)
            //putExtra("name", name)
        }
        startActivity(profileIntent)

    }
}