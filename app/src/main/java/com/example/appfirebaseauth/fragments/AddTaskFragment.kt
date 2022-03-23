package com.example.appfirebaseauth.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.appfirebaseauth.R
import com.example.appfirebaseauth.databinding.FragmentAddTaskBinding
import com.example.appfirebaseauth.ViewModel.AppStoreViewModel
import com.example.appfirebaseauth.model.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class AddTaskFragment : Fragment() {

    private lateinit var binding: FragmentAddTaskBinding
    private lateinit var navController: NavController
    private lateinit var appStoreViewModel: AppStoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding= FragmentAddTaskBinding.inflate(inflater, container, false)
        navController=findNavController()

        appStoreViewModel=ViewModelProvider(this).get(AppStoreViewModel::class.java)

        binding.buttonAddTask.setOnClickListener(){
            insertTask()
        }


        return binding.root

    }


    private fun inputNull(task: String, note: String): Boolean{
        return !(TextUtils.isEmpty(binding.TitleTask.text)
                && TextUtils.isEmpty(binding.notetask.text))
    }
    fun getCurrentDate():String{
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        return sdf.format(Date())
    }

    fun getDataProfile():String{
        //Get the currently signed-in user
        val user = Firebase.auth.currentUser
        user?.let {
            for (profile in it.providerData) {
                // UID specific to the provider
                val uid = profile.uid.toString()
            }
        }
        return user!!.uid
    }



    private fun insertTask() {

        val task=binding.TitleTask.text.toString()
        val note=binding.notetask.text.toString()
        val datetask = getCurrentDate()
        val idUser= getDataProfile()
        if(inputNull(task,note)){
            // Create User Object
            val task = Task(0,0,task,note, datetask,false)

            // Add Data to Database
            appStoreViewModel.addUTask(task)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            // Navigate Back
            navController.navigate(R.id.taskListFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill in all the fields", Toast.LENGTH_LONG).show()
        }
    }





}