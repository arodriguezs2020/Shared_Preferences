package seve.alo.apps.shared_preferences

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.google.android.material.appbar.CollapsingToolbarLayout

class MainActivity : AppCompatActivity() {

    var toolbarColaps : CollapsingToolbarLayout? = null

    var preferencias : SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbarColaps = findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar)
        toolbarColaps!!.title = "Beneficios de usar Kotlin"

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        preferencias = PreferenceManager.getDefaultSharedPreferences(this)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean  = when(item.itemId){

        R.id.menuSalir -> {
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            true
        }
        R.id.menuOlvidar -> {
            preferencias!!.edit().clear().apply()
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            true
        }
        else ->false
    }


}