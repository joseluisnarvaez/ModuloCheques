package cl.starttoken.ModuloCheques;

import java.sql.Connection;

import cl.starttoken.ModuloCheques.sqlite.SQLiteJDBC;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//    	Login V = new Login();      // creamos una ventana
//          V.setVisible(true);             // hacemos visible la ventana creada
    	
    	Connection conexion = SQLiteJDBC.coneccion();
    	
    	SQLiteJDBC.crearTablas(conexion);
    	
    	SQLiteJDBC.closedConeccion(conexion);
    }
}
