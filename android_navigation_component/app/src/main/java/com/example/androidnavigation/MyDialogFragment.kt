package com.example.androidnavigation

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class MyDialogFragment:DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = AlertDialog.Builder(requireContext())
        .setMessage(getString(R.string.place_holder_dialog_fragment))
        .setPositiveButton(getString(R.string.ok)){_,_ -> }
        .create()
    companion object {
        const val TAG = "PurchaseConfirmationDialog"
    }
}