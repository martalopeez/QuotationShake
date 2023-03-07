package mylophue.quotationshake.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.view.MenuProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationBarView
import mylophue.quotationshake.R
import mylophue.quotationshake.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MenuProvider {
    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = binding.fragmentContainerView.getFragment<NavHostFragment>().navController
        (binding.bottomNavigationView as NavigationBarView).setupWithNavController(navController)

        setSupportActionBar(binding.materialToolbar)

        val appBar = AppBarConfiguration(setOf(R.id.newQuotationFragment, R.id.favouritesFragment, R.id.settingsFragment))
        setupActionBarWithNavController(navController, appBar)

        addMenuProvider(this)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_about, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == R.id.aboutItem) navController.navigate(R.id.aboutDialogFragment)
        return true
    }
}