package mylophue.quotationshake.ui.favourites

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import mylophue.quotationshake.R
import mylophue.quotationshake.databinding.FragmentFavouritesBinding

@AndroidEntryPoint
class FavouritesFragment: Fragment(R.layout.fragment_favourites),
    DeleteAllDialogFragment.DeleteInterface, MenuProvider {
    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavouritesViewModel by viewModels()

    val touchHelper: ItemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.END) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun isItemViewSwipeEnabled(): Boolean {
           return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            viewModel.deleteQuotationAtPosition(viewHolder.adapterPosition)
        }

        override fun isLongPressDragEnabled(): Boolean {
            return false
        }
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavouritesBinding.bind(view)

        val adapter = QuotationListAdapter(object: QuotationListAdapter.ItemClicked {
            override fun onClick(author: String) {
                if (author == "Anonymous") {
                    Snackbar.make(view, "No es posible mostrar información si el autor es anónimo.", Snackbar.LENGTH_SHORT).show()
                } else
                    try {
                        startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://en.wikipedia.org/wiki/Special:Search?search=" + author)
                            )
                        )
                    } catch (e: ActivityNotFoundException) {Snackbar.make(view, "No se ha podido iniciar la actividad.", Snackbar.LENGTH_SHORT).show()}
            }
        })
        binding.recyclerView.adapter = adapter
        viewModel.favQuotations.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        viewModel.isDeleteAllVisible.observe(viewLifecycleOwner) {
            requireActivity().invalidateMenu()
        }
        touchHelper.attachToRecyclerView(binding.recyclerView)
    }

    override fun onPrepareMenu(menu: Menu) {
        super.onPrepareMenu(menu)
        menu.findItem(R.id.deleteItem).isVisible = viewModel.isDeleteAllVisible.value ?: false;
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun positiveButton() {
        viewModel.deleteAllQuotations()
    }

    override fun negativeButton() {}

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_favourites, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == R.id.deleteItem) {
            DeleteAllDialogFragment(this).show(childFragmentManager, null)
            return true
        }
        return false
    }
}