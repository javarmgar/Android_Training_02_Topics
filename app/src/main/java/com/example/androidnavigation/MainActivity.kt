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

        findViewById<Button>(R.id.btn_fragment_1).setOnClickListener {
            navController.navigate(R.id.fragmentOne)
        }

        findViewById<Button>(R.id.btn_fragment_2).setOnClickListener {
            navController.navigate(R.id.fragmentTwo)
        }
    }

}