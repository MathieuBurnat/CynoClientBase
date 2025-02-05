package ch.leytto.cynoclient

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var light_action: MenuItem
    private lateinit var dark_action: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab_option_add_dog)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Mode : " + AppCompatDelegate.getDefaultNightMode(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val fab2: FloatingActionButton = findViewById(R.id.fab_option_add_client)
        fab2.setOnClickListener { view ->
            Snackbar.make(view, "Mode : " + AppCompatDelegate.getDefaultNightMode(), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_reports, R.id.nav_customers, R.id.nav_dogs, R.id.nav_services, R.id.nav_localities), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        toolbar.setNavigationOnClickListener {
            // Handle navigation icon press
            drawerLayout.openDrawer(Gravity.LEFT);
        }

        toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.light_mode -> {
                    // Handle favorite icon press
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    light_action.isVisible = false
                    dark_action.isVisible = true
                    true
                }
                R.id.dark_mode -> {
                    // Handle search icon press
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    light_action.isVisible = true
                    dark_action.isVisible = false
                    true
                }
                else -> false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        light_action = menu.findItem(R.id.light_mode)
        dark_action = menu.findItem(R.id.dark_mode)

        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        when (currentNightMode) {
            Configuration.UI_MODE_NIGHT_NO -> {light_action.isVisible = false}
            Configuration.UI_MODE_NIGHT_YES -> {dark_action.isVisible = false}
        }

        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun dogDetail(view: View) {
        val intent = Intent(this, DogDetailsActivity::class.java).apply {
            if(view.getId() == R.id.dog_details_button){
                putExtra("dog_id", view.getTag().toString())
            }
        }
        startActivity(intent)
    }

    fun clientDetail(view: View) {
        val intent = Intent(this, ClientInfoActivity::class.java).apply {
            if(view.getId() == R.id.button_client_details){
                putExtra("client_id", view.getTag().toString())
            }
        }
        startActivity(intent)
    }

    fun clientCreateview(view: View) {
        val intent = Intent(this, CreateClientActivity::class.java)
        startActivity(intent)
    }

    fun serviceDetail(view: View) {
        val intent = Intent(this, ServiceDetailsActivity::class.java)
        startActivity(intent)
    }
    fun localityDetail(view: View) {
        val intent = Intent(this, LocalityDetailsActivity::class.java)
        startActivity(intent)
    }
}