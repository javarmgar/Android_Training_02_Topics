package com.example.androidnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        findViewById<Button>(R.id.btn_fragment_2).setOnClickListener {
            val action = FragmentOneDirections.actionFragmentOneToFragmentTwo()
            navController.navigate(action)
        }

        findViewById<Button>(R.id.btn_fragment_1).setOnClickListener {
            val action = FragmentTwoDirections.actionFragmentTwoToFragmentOne()
            navController.navigate(action)
        }
/*
        findViewById<Button>(R.id.btn_fragment_2).setOnClickListener {
            if(navController.currentDestination?.id != R.id.fragmentTwo)
                navController.navigate(R.id.myDialogFragment)
        }*/
    }

}