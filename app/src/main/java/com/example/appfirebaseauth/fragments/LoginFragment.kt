package com.example.appfirebaseauth.fragments

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.appfirebaseauth.R
import com.example.appfirebaseauth.data.AppDatabase
import com.example.appfirebaseauth.databinding.FragmentLoginBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.ktx.Firebase


class LoginFragment : Fragment() {

    //reference
    private lateinit var binding: FragmentLoginBinding
    private lateinit var navController:NavController
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {


        // Inflate the layout for this fragment
        binding= FragmentLoginBinding.inflate(inflater, container, false)




        //instance navController
        navController= findNavController()


        //distinction of the fragments--- show registerFragment
        binding.ButtonSignup.setOnClickListener(){
            navController.navigate(R.id.registerFragment)
        }

        // show recoverPasswordFragment
        binding.ButtonForgetPassword.setOnClickListener(){
            navController.navigate(R.id.recoverPasswordFragment)
        }

        //show profileUserFragment
        binding.ButtonLogin.setOnClickListener(){
            navController.navigate(R.id.profileUserFragment)
        }


        //invoke fun for log in
        binding.ButtonLogin.setOnClickListener(){
            loginAccount()
        }





        return binding.root
    }

    //function for validate the input
    private fun validData(): Boolean {
        var valid:Boolean;

        if (binding.inputUserName.text.isNotEmpty()){
            valid=true
        }else{
            binding.inputUserName.error = "enter an email";
            valid=false
        }

        if (binding.inputPass.text.isNotEmpty()){
            valid=true
        }else{
            binding.inputPass.error = "enter a password";
            valid=false
        }
        return valid

    }

    //message the alert for the login
    private fun showAlertAuth() {

        MaterialAlertDialogBuilder(requireContext())
            .setTitle(resources.getString(R.string.titleAlert))
            .setMessage(resources.getString(R.string.messageErrorLogin))

            .setPositiveButton(resources.getString(R.string.AcceptError)) { _, _ ->
                clearInput()
            }
            .show()

    }

    //clear the input the fragment
    private  fun clearInput(){
        binding.inputUserName.text.clear()
        binding.inputPass.text.clear()
    }

    // function for log in
    private fun loginAccount () {
      if (validData()) {
          FirebaseAuth.getInstance().signInWithEmailAndPassword(
              binding.inputUserName.text.toString(),
              binding.inputPass.text.toString()
          ).addOnCompleteListener {
              if (it.isSuccessful) {
                  //get user log in
                  Log.i(tag,it.result?.user?.email ?: "")

                  navController.navigate(R.id.taskListFragment)

                  clearInput()

              } else {
                  showAlertAuth()
              }

          }
      }


    }

}