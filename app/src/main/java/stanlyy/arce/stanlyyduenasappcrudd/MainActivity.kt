package stanlyy.arce.stanlyyduenasappcrudd

import Modelo.Connection
import Modelo.DataClassTicket
import RecyclerViewHelper.Adaptador
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar las vistas
        val txtTitulo = findViewById<EditText>(R.id.txtTitulo)
        val txtDescripcion = findViewById<EditText>(R.id.txtDescripcion)
        val txtAutor = findViewById<EditText>(R.id.txtAutor)
        val txtCorreo = findViewById<EditText>(R.id.txtCorreoTick)
        val txtEstado = findViewById<EditText>(R.id.txtEstado)
         val txtFechaFin = findViewById<EditText>(R.id.txtFechaFin)
        val btnAgregar = findViewById<Button>(R.id.btn_agregarTick)
        val rcvProductos = findViewById<RecyclerView>(R.id.rcvElementos)

// Asignar un layout al RecyclerView
        rcvProductos.layoutManager = LinearLayoutManager(this)

        // Función para obtener datos
        fun obtenerDatos(): List<DataClassTicket> {
            val tickets = mutableListOf<DataClassTicket>()
            val objConexion = Connection().cadenaConexion()

            objConexion?.use { connection ->
                val statement = connection.createStatement()
                val resultSet = statement.executeQuery("SELECT * FROM TB_TICKET")

                resultSet.use { rs ->
                    while (rs.next()) {
                        val numTicket = rs.getInt("NUM_TICKET")
                        val titulo = rs.getString("TÍTULO")
                        val descripcion = rs.getString("DESCRIPCIÓN")
                        val autor = rs.getString("AUTOR")
                        val correo = rs.getString("CORREO")
                        val fechaCreacion = rs.getDate("FECHA_CREACIÓN")
                        val estado = rs.getString("ESTADO")
                        val fechaFinalizacion = rs.getString("FECHA_FINALIZACIÓN")

                        val ticket = DataClassTicket (numTicket, titulo, descripcion, autor, correo, fechaCreacion, estado, fechaFinalizacion)
                        tickets.add(ticket)
                    }
                }
            }
            return tickets
        }

// Asignar un adaptador
        CoroutineScope(Dispatchers.IO).launch {
            val ticketsBd = obtenerDatos()
            withContext(Dispatchers.Main) {
                val miAdapter = Adaptador(ticketsBd)
                rcvProductos.adapter = miAdapter
            }
        }

// Acción para el botón Agregar
        btnAgregar.setOnClickListener {
            try {
                CoroutineScope(Dispatchers.IO).launch {
                    val objConexion = Connection().cadenaConexion()

                    objConexion?.use { connection ->
                        val crearTicket = connection.prepareStatement("INSERT INTO TB_TICKET (título, descripción, autor, correo, estado, Fecha_Finalización) VALUES (?, ?, ?, ?, ?, ?)")

                        crearTicket.setString(1, txtTitulo.text.toString())
                        crearTicket.setString(2, txtDescripcion.text.toString())
                        crearTicket.setString(3, txtAutor.text.toString())
                        crearTicket.setString(4, txtCorreo.text.toString())
                        crearTicket.setString(5, txtEstado.text.toString())
                        crearTicket.setString(6, txtFechaFin.text.toString())
                        crearTicket.executeUpdate()
                    }

                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@MainActivity, "Ticket guardado", Toast.LENGTH_SHORT).show()
                        // Actualizar la lista de tickets después de agregar uno nuevo
                        val ticketsActualizados = obtenerDatos()
                        val miAdapter = Adaptador(ticketsActualizados)
                        rcvProductos.adapter = miAdapter
                    }
                }
            } catch (ex: Exception) {
                println("REGISTER: Loco este es el error: $ex")
            }
        }

    }
}