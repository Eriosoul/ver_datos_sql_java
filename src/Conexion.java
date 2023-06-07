import java.sql.*;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    static Connection con = null;

    public Conexion(){
    }

    // conexion con nuestra base de datos
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prueba", "root", "");
            System.out.println("Conexion establecida");
            getDataSQL();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Ha ocurido un error");
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        }
        return con;
    }

    public void disconect(){
        try{
            con.close();
        }catch (SQLException ex){
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getDataSQL(){
        try{
            Statement consulta = con.createStatement();
            ResultSet registros = consulta.executeQuery("select * from persona");
            if (!registros.isBeforeFirst()) {
                System.out.println("La tabla está vacía");
            } else {
                while (registros.next()) {
                    System.out.println("Nombre: " + registros.getString("nombre"));
                    System.out.println("Puesto: " + registros.getString("profesion"));
                    System.out.println("Edad: " + registros.getInt("edad"));
                    System.out.println("--------------------------");
                }
            }
        }catch (Exception ex){
            System.out.println("Error" + ex);
        }
    }
}
