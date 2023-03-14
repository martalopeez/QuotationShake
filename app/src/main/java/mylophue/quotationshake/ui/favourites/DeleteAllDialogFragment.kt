package mylophue.quotationshake.ui.favourites

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels

class DeleteAllDialogFragment(private val delete: DeleteInterface): DialogFragment() {
    //private val viewModel : FavouritesViewModel by activityViewModels()
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        return AlertDialog.Builder(requireContext())
            .setTitle("Delete all favourite quotations")
            .setMessage("Do you really want to delete all quotations?")
            .setNegativeButton("Cancel") { _, _ ->
                delete.negativeButton()
            }.setPositiveButton("Delete") { _, _ ->
                delete.positiveButton()
            }.create()
    }

    interface DeleteInterface {
        fun positiveButton()
        fun negativeButton()
    }
}