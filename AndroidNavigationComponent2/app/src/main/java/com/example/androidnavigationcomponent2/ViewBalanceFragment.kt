package com.example.androidnavigationcomponent2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController

class ViewBalanceFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_balance, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(view){
            findViewById<Button>(R.id.btn_global_action_start).setOnClickListener {
                with(findNavController()){
                    if(currentDestination?.id == R.id.viewBalanceFragment){
                        val action = ViewBalanceFragmentDirections.actionGlobalMainFragment()
                        navigate(action)
                    }
                }
            }
            findViewById<Button>(R.id.btn_animation_fragment).setOnClickListener {
                with(findNavController()){
                    if(currentDestination?.id == R.id.viewBalanceFragment){
                        val action = ViewBalanceFragmentDirections.actionViewBalanceFragmentToAnimationFragment()
                        navigate(action)
                    }
                }
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ViewBalanceFragment().apply {
            }
    }
}