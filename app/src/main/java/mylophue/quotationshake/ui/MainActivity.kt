package mylophue.quotationshake.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mylophue.quotationshake.R
import mylophue.quotationshake.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}