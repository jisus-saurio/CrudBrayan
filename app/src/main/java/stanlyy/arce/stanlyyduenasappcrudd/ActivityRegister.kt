package stanlyy.arce.stanlyyduenasappcrudd

import Modelo.Connection
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID
import java.sql.Connection as Connection1

class ActivityRegister : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val txtNombre = findViewById<EditText>(R.id.txtNombre)
        val txtApellido = findViewById<EditText>(R.id.txtApellido)
        val txtCorreo = findViewById<EditText>(R.id.txtCorreo)
        val txtPassword = findViewById<EditText>(R.id.txtContrasena)
        val btnRegister = findViewById<Button>(R.id.btnRegistrarse)

        val imvRegresar = findViewById<ImageView>(R.id.imv_regresar)



        imvRegresar.setOnClickListener{
            val activityLogin = Intent(this, ActivityLogin::class.java)
            startActivity(activityLogin)
            finish()
        }

        btnRegister.setOnClickListener {
            try {
                val ActivityLogin = Intent(this, ActivityLogin::class.java)
                GlobalScope.launch (Dispatchers.IO){
                    //Abrir corrutina
                    val objConnection = Connection().cadenaConexion()

                    val crearUsuario = objConnection?.prepareStatement("INSERT INTO TB_USUARIO (Nombre, Apellido, Correo, Contraseña) VALUES (?,?,?,?)")!!

                    crearUsuario.setString(1, txtNombre.text.toString())
                    crearUsuario.setString( 2, txtApellido.text.toString())
                    crearUsuario.setString(3, txtCorreo.text.toString())
                    crearUsuario.setString(4, txtPassword.text.toString())
                    crearUsuario.executeUpdate()

                    withContext(Dispatchers.Main){
                        Toast.makeText(this@ActivityRegister, "Usuario Creado", Toast.LENGTH_SHORT).show()

                        startActivity(ActivityLogin)
                        finish()

                    }

                }


            }
            catch (ex:Exception){
                println("REGISTER: Loco este es el error:$ex")

            }
        }
    }
}
