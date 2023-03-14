package mylophue.quotationshake.ui.newquotation

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import mylophue.quotationshake.R
import mylophue.quotationshake.databinding.FragmentNewQuotationBinding

@AndroidEntryPoint
class NewQuotationFragment: Fragment(R.layout.fragment_new_quotation), MenuProvider {
    private var _binding: FragmentNewQuotationBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NewQuotationViewModel by viewModels()

    private fun getNewQuotation() {
        viewModel.getNewQuotation()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNewQuotationBinding.bind(view)

        viewModel.userName.observe(viewLifecycleOwner) {
            binding.tvWelcome.text = getString(R.string.welcome_message, it)
        }

        viewModel.quotation.observe(viewLifecycleOwner) {
            binding.tvQuotation.text = it?.quotation
            binding.tvAuthor.text = it?.author ?: "Anonymous"
        }

        viewModel.isRefreshing.observe(viewLifecycleOwner) {
            binding.swipeToRefresh.isRefreshing = it
        }

        viewModel.isGreetingsVisible.observe(viewLifecycleOwner) {
            binding.tvWelcome.visibility = if (it) View.VISIBLE else View.INVISIBLE
        }

        viewModel.favIsVisible.observe(viewLifecycleOwner) {
            binding.btnFav.visibility = if (it) View.VISIBLE else View.INVISIBLE
        }

        binding.swipeToRefresh.setOnRefreshListener {
            getNewQuotation()
        }

        binding.btnFav.setOnClickListener {
            viewModel.addToFavourites()
        }

        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        viewModel.existsError.observe(viewLifecycleOwner) {
            if (it != null) {
                Snackbar.make(view, "Error", Snackbar.LENGTH_LONG).show()
                viewModel.resetError()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_new_quotation, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == R.id.refreshItem) {
            getNewQuotation()
            return true
        }
        return false
    }
}

