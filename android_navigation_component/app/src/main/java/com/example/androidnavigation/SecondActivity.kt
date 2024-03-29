package com.example.androidnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.os.bundleOf

import androidx.navigation.fragment.NavHostFragment

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        findViewById<Button>(R.id.btn_dialog_fragment).setOnClickListener {
            MyDialogFragment().show(supportFragmentManager,MyDialogFragment.TAG)
        }

        findViewById<Button>(R.id.btn_open_act_img).setOnClickListener {
            navController.navigate(R.id.url_chatgpt)
        }

        findViewById<Button>(R.id.btn_open_youtube).setOnClickListener {
            navController.navigate(R.id.activity_url_youtube,
                bundleOf(
                    "videoId" to "hoJ4qxcXz5w"
                )
            )
        }
    }
}