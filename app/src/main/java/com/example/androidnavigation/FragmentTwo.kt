package com.example.androidnavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController




class FragmentTwo : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnSecondActivity = view.findViewById<Button>(R.id.btn_second_activity)
        btnSecondActivity.setOnClickListener {
            with(findNavController()){
                if(currentDestination?.id == R.id.fragmentTwo){
                    navigate(R.id.secondActivity)
                }
            }
        }
    }
    companion object {

        @JvmStatic
        fun newInstance() =
            FragmentTwo().apply {
            }
    }
}