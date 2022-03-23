package com.example.appfirebaseauth.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.appfirebaseauth.R
import com.example.appfirebaseauth.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class RegisterFragment : Fragment() {
    private lateinit var database: DatabaseReference

    //reference
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentRegisterBinding.inflate(inflater, container, false)

        //instance navController
        navController=findNavController()

        //..Firebase realTime
        database = Firebase.database.reference


        //exec function the created user
        binding.buttonSave.setOnClickListener(){
            registerCountUser ()
        }

        return binding.root
    }

    // function for valid of data
    private fun validData():Boolean{
        var valid:Boolean=true;

        if(binding.inputName.text!!.isEmpty()){
           binding.layoutName.error = getString(R.string.messageError)
            valid=false
        }else{
            binding.layoutName.error = null
            valid=true
        }

        if(binding.inputLastName.text!!.isEmpty()){
            binding.layoutLastname.error = getString(R.string.messageError)
            valid=false
        }else{
            binding.layoutLastname.error = null
            valid=true
        }
        if(binding.inputEmail.text!!.isEmpty()){
            binding.layoutEmail.error = getString(R.string.messageError)
            valid=false
        }else{
            binding.layoutEmail.error = null
            valid=true
        }

        if(binding.inputPassword.text!!.isEmpty()){
            binding.inputPass.error = getString(R.string.messageError)
            valid=false
        }else{
            binding.inputPass.error = null
            valid=true
        }
        if(binding.inputDate.text!!.isEmpty()){
            binding.layoutDate.error = getString(R.string.messageError)
            valid=false
        }else{
            binding.layoutDate.error = null
            valid=true
        }

        if(binding.inputCountry.text!!.isEmpty()){
           binding.layoutCountry.error = getString(R.string.messageError)
            valid=false
        }else{
            binding.layoutCountry.error = null
            valid=true
        }

        if(binding.inputCity.text!!.isEmpty()){
            binding.layoutcity.error = getString(R.string.messageError)
            valid=false
        }else{
            binding.layoutcity.error = null
            valid=true
        }

        if(binding.inputAddress.text!!.isEmpty()){
           binding.layoutAddress.error = getString(R.string.messageError)
            valid=false
        }else{
            binding.layoutAddress.error = null
            valid=true
        }
        if(binding.selectGender.text!!.isEmpty()){
           binding.radioGender.error = getString(R.string.messageError)
            valid=false
        }else{
            binding.radioGender.error = null
            valid=true
        }


        return valid
    }

    //show error the register failed
    private fun showAlertAuth(){
        val builderMessage= AlertDialog.Builder(requireContext())
        builderMessage.setTitle("Alert error")
        builderMessage.setMessage("an error occurred while authenticating the user")
        builderMessage.setPositiveButton("Accept", null)
        val message: AlertDialog =builderMessage.create()
        message.show()

    }

    //function for register user
    private fun registerCountUser () {
        if (validData()) {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                binding.inputEmail.text.toString(),
                binding.inputPassword.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    //save of data user saveUsers() //show fragment profile
                        Log.i(tag,it.result?.user?.email ?:"")
                        navController.navigate(R.id.profileUserFragment)
                } else {
                    showAlertAuth()
                }

            }
        }

    }

}