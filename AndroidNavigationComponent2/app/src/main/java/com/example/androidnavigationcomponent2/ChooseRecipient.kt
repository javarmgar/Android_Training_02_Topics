package com.example.androidnavigationcomponent2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController


class ChooseRecipient : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_recipient, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(view){
            findViewById<Button>(R.id.btn_goto_chooseAmountFragment).setOnClickListener {
                findNavController().apply {
                    if(currentDestination?.id == R.id.chooseRecipient){
                        navigate(R.id.chooseAmountFragment)
                    }
                }
            }
            findViewById<Button>(R.id.btn_global_action_start).setOnClickListener {
                with(findNavController()){
                    if(currentDestination?.id == R.id.chooseRecipient){
                        navigate(R.id.action_global_mainFragment)
                    }
                }
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ChooseRecipient().apply {
            }
    }
}