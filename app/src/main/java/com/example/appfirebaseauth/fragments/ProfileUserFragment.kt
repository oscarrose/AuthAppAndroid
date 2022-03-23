package com.example.appfirebaseauth.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.appfirebaseauth.R
import com.example.appfirebaseauth.databinding.FragmentProfileUserBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ProfileUserFragment : Fragment() {
    //reference
    private lateinit var binding: FragmentProfileUserBinding
    private lateinit var navController: NavController




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentProfileUserBinding.inflate(inflater, container, false)

        //instance navController
        navController=findNavController()

        // exec function for sign Out
        binding.buttonLogout.setOnClickListener(){
            signOut()
        }

        loadDataProfile()


        return binding.root
    }

    private fun signOut(){
        FirebaseAuth.getInstance()
            .signOut().also {
                navController.navigate(R.id.loginFragment2)
            }
    }
    private fun loadDataProfile(){
        //Get the currently signed-in user
        val user = Firebase.auth.currentUser
        user?.let {
            for (profile in it.providerData) {
                // UID specific to the provider
                val uid = profile.uid

                // Name, email address, and profile photo Url
                binding.showName.text = profile.displayName
                binding.showEmail.text = profile.email.toString()

            }
        }

    }

}