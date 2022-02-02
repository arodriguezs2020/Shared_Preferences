package seve.alo.apps.shared_preferences

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

class LoginActivity : AppCompatActivity() {

    var preferencias : SharedPreferences?= null
    var switchRecordar: Switch?= null
    var editTextEmail : EditText?= null
    var editTextPass : EditText?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        switchRecordar = findViewById<Switch>(R.id.switchRecordar)

        // Modo en que se crea el archivo, si no se necesita multiples Preferencias usamos las Shared por defecto
        preferencias = PreferenceManager.getDefaultSharedPreferences(this)

        // Si se necesitan multiples preferencias
        preferencias = getSharedPreferences("Preferencias", Context.MODE_PRIVATE)

        ponerPreferenciasSiExisten()

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPass = findViewById(R.id.editTextPass)

        btnLogin.setOnClickListener {
            val email = editTextEmail!!.text.toString()
            val pass = editTextPass!!.text.toString()

            if (logeo(email, pass)) {
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
                guardarPreferencias(email, pass)
            }
        }
    }
    
    fun validarEmail(email: String) : Boolean {
        return !email.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    
    fun validarPass(pass: String) : Boolean {
        return pass.length >= 5
    }
    
    fun logeo(email: String, pass: String) : Boolean {
        if (!validarEmail(email)) {
            Toast.makeText(this, "Email no valido, intenta nuevamente por favor", Toast.LENGTH_SHORT).show()
            return false
        } else if (!validarPass(pass)) {
            Toast.makeText(this, "Password no valido, debe contener mínimo 5 carácteres", Toast.LENGTH_SHORT).show()
            return false
        } else {
            return true
        }
    }

    fun guardarPreferencias(email: String, pass: String) {
        if (switchRecordar!!.isChecked) {
            preferencias!!.edit()
                .putString("email", email)
                .putString("pass", pass)
                .apply()
        }
    }

    fun ponerPreferenciasSiExisten() {
        val email = preferencias?.getString("email", "")
        val pass = preferencias?.getString("pass", "")

        if (!email.isNullOrEmpty() && !pass.isNullOrEmpty()) {
            editTextEmail!!.setText(email)
            editTextPass!!.setText(pass)
            switchRecordar!!.isChecked = true
        }
    }
}