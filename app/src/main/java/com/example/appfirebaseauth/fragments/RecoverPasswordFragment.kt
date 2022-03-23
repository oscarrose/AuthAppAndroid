package com.example.appfirebaseauth.fragments

import android.os.Binder
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.appfirebaseauth.R
import com.example.appfirebaseauth.databinding.FragmentLoginBinding
import com.example.appfirebaseauth.databinding.FragmentRecoverPasswordBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RecoverPasswordFragment : Fragment() {

    private  lateinit var binding: FragmentRecoverPasswordBinding
    private  lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentRecoverPasswordBinding.inflate(inflater, container, false)

        //search the navController
        navController=findNavController()

        //exec the actions the recover password
        binding.btRecoverPassword.setOnClickListener(){
            resetPassword()
        }

        return binding.root
    }


    //function for validate the input
    private fun validData(): Boolean {
        var valid:Boolean;

        if (binding.inputEmailRecover.text.isNotEmpty()){
            valid=true
        }else{
            binding.inputEmailRecover.error = "enter an email";
            valid=false
        }
        return valid
    }

    //message the alert for the login
    private fun showAlertSendMessage() {

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Password reset")
            .setMessage("a message has been sent to the email")

            .setPositiveButton(resources.getString(R.string.AcceptError)) { _, _ ->
                navController.navigate(R.id.loginFragment2)
            }
            .show()

    }

    //function for recover password
    private  fun resetPassword(){
        if(validData()){
            val emailAddress = binding.inputEmailRecover.text.toString()
            Firebase.auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(tag, "Email sent.")
                        showAlertSendMessage()

                    }
                }
        }

    }
}