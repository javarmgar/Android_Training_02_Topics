package com.example.androidnavigationcomponent2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavDirections
import androidx.navigation.findNavController


class ChooseAmountFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_amount, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(view){
            findViewById<Button>(R.id.btn_global_action_start).setOnClickListener {
                with(findNavController()){
                    val action: NavDirections = ChooseAmountFragmentDirections.actionGlobalMainFragment()
                    navigate(action)
                }
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ChooseAmountFragment().apply {
            }
    }
}