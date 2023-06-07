public class Main {
    public static void main(String[] args) {
        Conexion con = new Conexion();
        con.getConnection();
        con.disconect();
    }
}