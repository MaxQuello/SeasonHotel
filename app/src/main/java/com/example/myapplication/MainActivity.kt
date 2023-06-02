package com.example.login

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.login.databinding.ActivityHomeBinding
import com.google.android.material.navigation.NavigationView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.Fragment



class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var nv: NavigationView
    private lateinit var binding: ActivityHomeBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.hide()

        drawerLayout = binding.drawerLayout
        nv = binding.navigationView
        val itemMenu = nv.menu.getItem(2)
        itemMenu.setOnMenuItemClickListener { menuItem ->
            Log.i("mytag", "funziono")
            when (menuItem.itemId) {
                R.id.contattaci_item -> {
                    Log.i("mytag", "funziono veramente")
                    val intent = Intent(Intent.ACTION_DIAL)
                    intent.data = Uri.parse("tel:${getString(R.string.numeroTelefono)}")
                    startActivity(intent)
                    true
                }

                else -> false
            }
        }

        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            R.string.drawer_open,
            R.string.drawer_close
        )

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val openDrawerButton: ImageButton = binding.openDrawerButton

        openDrawerButton.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)

        }

        val loadFragmentButton = binding.logoHomeButton
        loadFragmentButton.setOnClickListener {
            loadFragment(HomeFragment())

            }

        }

    private fun loadFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment)
        fragmentTransaction.commit()

    }
}


