package com.example.appfirebaseauth.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appfirebaseauth.R
import com.example.appfirebaseauth.Adapter.AppAdapter
import com.example.appfirebaseauth.databinding.FragmentTaskListBinding
import com.example.appfirebaseauth.ViewModel.AppStoreViewModel

class TaskListFragment : Fragment() {

    private lateinit var binding: FragmentTaskListBinding
    private  lateinit var navController: NavController
    private lateinit var appStoreViewModel: AppStoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
       binding= FragmentTaskListBinding.inflate(inflater, container, false)


        //search the navController
        navController=findNavController()

        //orientation and more the recyclearView
        val adapter= AppAdapter()
        val recyclerView=binding.taskListRecyclearView
        recyclerView.adapter=adapter
        recyclerView.layoutManager=GridLayoutManager(requireContext(),2,
            RecyclerView.VERTICAL,false)

        //negation
        binding.floatingActionButton.setOnClickListener(){
            navController.navigate(R.id.addTaskFragment)
        }

        // get the AppStoreViewModel
        //for subscribe the AppStoreViewModel
        appStoreViewModel=ViewModelProvider(requireActivity()).get(AppStoreViewModel::class.java)

        appStoreViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            adapter.setDatatask(it)
        })


           /* binding.taskListRecyclearView.adapter=AppAdapter(it)
        }*/

        return binding.root
    }


}