package com.example.appfirebaseauth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.appfirebaseauth.data.AppDatabase
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    //reference
     private lateinit var navController:NavController
     private lateinit var bottomNavigationView:BottomNavigationView
    private lateinit var analytics: FirebaseAnalytics


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //google Analytics
        analytics = Firebase.analytics
        val bundle=Bundle()
        bundle.putString("messageAnalysis", "Integration firebase complete")
        analytics.logEvent("InitScreen",bundle)

        //mount the database
        AppDatabase.getDatabase(this);


        setContentView(R.layout.activity_main)

        //riding the database


        //search the bottomNavigationView
        bottomNavigationView=findViewById(R.id.bottomNavView)

        //Navigate to a destination
         val hostNav=  supportFragmentManager.findFragmentById(R.id.navigationHot) as NavHostFragment
        navController=hostNav.navController



        //controller of when show bottomNavigationView
        navController.addOnDestinationChangedListener {_, destination   ,_ ->
            when (destination.id){
                R.id.profileUserFragment, R.id.taskListFragment ->
                {
                    bottomNavigationView.visibility=View.VISIBLE
                }
                else ->{
                    bottomNavigationView.visibility=View.GONE
                }

            }
        }

        //linking the navcontroller with bottomnavigationView
        NavigationUI.setupWithNavController(bottomNavigationView,navController);



    }


}