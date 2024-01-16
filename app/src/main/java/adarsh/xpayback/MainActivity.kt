package adarsh.xpayback

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import adarsh.xpayback.databinding.ActivityMainBinding
import android.os.Looper
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var isExit = false
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.toolbar.setNavigationOnClickListener {
            // check if the current fragment is the start destination (listProductFragment) and if so, call finish()
            if (navController.currentDestination?.id == R.id.listUserFragment) {
                if (isExit) {
                    finish()
                } else {
                    isExit = true
                    Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show()
                       android.os.Handler(Looper.getMainLooper()).postDelayed({
                            isExit = false
                        }, 2000)
                }
            }else{
                onBackPressed()
            }

        }
        navController.navigate(R.id.listUserFragment)
    }


}
