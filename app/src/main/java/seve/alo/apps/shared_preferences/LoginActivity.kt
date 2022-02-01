package seve.alo.apps.shared_preferences

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        val editTextPass = findViewById<EditText>(R.id.editTextPass)


        btnLogin.setOnClickListener {
            val email = editTextEmail.toString()
            val pass = editTextPass.toString()

            if (logeo(email, pass)) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }

    }
    
    fun validarEmail(email: String) : Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
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
}