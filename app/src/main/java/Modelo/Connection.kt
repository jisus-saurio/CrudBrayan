package Modelo

import java.sql.Connection
import java.sql.DriverManager

class Connection {

        fun cadenaConexion(): Connection? {
             return try{
                val url = "jdbc:oracle:thin:@10.10.0.79:1521:xe"
                val user = "STANLYY_DEVELOPER"
                val password = "fakedrips"

                val connection = DriverManager.getConnection(url, user, password)

               connection
            } catch (e: Exception) {
                println("este es el error:$e")
                 null
            }
        }

}


