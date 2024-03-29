package com.example.androidnavigationcomponent2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.navOptions


class MainFragment : Fragment() {

    private lateinit var btnGlobalActionStart: Button
    private lateinit var btnGotoSendMoneyGraph: Button
    private lateinit var btnGotoViewBalanceFragment: Button
    private val LOG_TAG = "NAVIGATION_COMPONENT_TAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        val view:View = inflater.inflate(R.layout.fragment_main, container, false)

        btnGotoViewBalanceFragment =  view.findViewById(R.id.btn_goto_viewBalanceFragment)

        btnGotoSendMoneyGraph = view.findViewById(R.id.btn_goto_sendMoneyGraph)

        btnGlobalActionStart = view.findViewById(R.id.btn_global_action_start)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(view){
            btnGotoViewBalanceFragment.setOnClickListener {
                with(findNavController()){
                    val action = MainFragmentDirections.actionMainFragmentToViewBalanceFragment()
                    navigate(action,
                        navOptions {
                            anim {
                                enter = android.R.animator.fade_in
                                exit = android.R.animator.fade_out
                            }
                        }
                    )
                }
            }

            btnGotoSendMoneyGraph.setOnClickListener {
                with(findNavController()){
                    val action = MainFragmentDirections.actionMainFragmentToSendMoneyGraph()
                    navigate(action)
                }
            }

            btnGlobalActionStart.setOnClickListener {
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