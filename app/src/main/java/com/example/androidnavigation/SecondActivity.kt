package com.example.androidnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.navigation.fragment.NavHostFragment

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController


        /*findViewById<Button>(R.id.btn_dialog_fragment).setOnClickListener {
            with(navController){
                if(navController.currentDestination?.id == R.id.secondActivity){
                 navigate(R.id.myDialogFragment)
                }
            }
        }*/
        findViewById<Button>(R.id.btn_dialog_fragment).setOnClickListener {
            MyDialogFragment().show(supportFragmentManager,MyDialogFragment.TAG)
        }
    }
}