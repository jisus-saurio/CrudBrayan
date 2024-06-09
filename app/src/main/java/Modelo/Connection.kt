package Modelo

import java.sql.Connection
import java.sql.DriverManager

class Connection {

        fun cadenaConexion(): Connection? {
             return try{
                val url = "jdbc:oracle:thin:@192.168.68.110:1521:xe"
                val user = "SYSTEM"
                val password = "fakedrips"

                val connection = DriverManager.getConnection(url, user, password)

               connection
            } catch (e: Exception) {
                println("este es el error:$e")
                 null
            }
        }

}


