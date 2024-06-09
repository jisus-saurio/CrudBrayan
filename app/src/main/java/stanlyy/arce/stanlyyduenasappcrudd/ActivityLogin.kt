package stanlyy.arce.stanlyyduenasappcrudd

import Modelo.Connection
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActivityLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val txtcontrasena = findViewById<TextView>(R.id.txtPass)
        val txtEmail = findViewById<TextView>(R.id.txtEmail)
        val lbAbrirRegister = findViewById<TextView>(R.id.lbRegister)

        lbAbrirRegister.setOnClickListener{
            val pantallaRegister = Intent(this, ActivityRegister::class.java)
            startActivity(pantallaRegister)

            finish()
        }

        btnLogin.setOnClickListener{
            val pantallaPrincipal = Intent(this, MainActivity::class.java)

            GlobalScope.launch(Dispatchers.IO){
                val objConexion = Connection().cadenaConexion()

                val comprobarUsuario = objConexion?.prepareStatement("SELECT * FROM TB_USUARIO WHERE Correo = ? AND Contraseña = ?")!!
                comprobarUsuario.setString(1, txtEmail.text.toString())
                comprobarUsuario.setString(2, txtcontrasena.text.toString())

                val resultado = comprobarUsuario.executeQuery()

                if (resultado.next()){
                    startActivity(pantallaPrincipal)
                }
                else{
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@ActivityLogin, "Credenciales inválidas", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }
    }
}