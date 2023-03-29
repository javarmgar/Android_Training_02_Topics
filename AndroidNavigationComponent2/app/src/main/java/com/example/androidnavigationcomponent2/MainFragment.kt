package com.example.androidnavigationcomponent2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.findNavController


class MainFragment : Fragment() {

    private val LOG_TAG = "NAVIGATION_COMPONENT_TAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(view){
            findViewById<Button>(R.id.btn_goto_viewBalanceFragment).setOnClickListener {
                with(findNavController()){
                    if(currentDestination?.id == R.id.mainFragment){
                        val action = MainFragmentDirections.actionMainFragmentToViewBalanceFragment()
                        navigate(action)
                    }
                }
            }

            findViewById<Button>(R.id.btn_goto_sendMoneyGraph).setOnClickListener {
                with(findNavController()){
                    if(currentDestination?.id == R.id.mainFragment){
                        val action = MainFragmentDirections.actionMainFragmentToSendMoneyGraph()
                        navigate(action)
                    }
                }
            }

            findViewById<Button>(R.id.btn_global_action_start).setOnClickListener {
                with(findNavController()){
                    if(currentDestination?.id == R.id.mainFragment){
                        Toast.makeText(this.context,"${LOG_TAG}You are al ready in the screen", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
    companion object {

        @JvmStatic
        fun newInstance() =
            MainFragment().apply {
            }
    }
}