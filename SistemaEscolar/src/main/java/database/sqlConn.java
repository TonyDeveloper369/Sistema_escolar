package database;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class sqlConn {
    private static final String url = "jdbc:mysql://localhost:3306/sistema_escolar";
    private static final String user = "root";
    private static final String password = "...";

    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(url, user, password);
    }
    public static void testConnetion(){
        try (Connection conn = getConnection()){
            System.out.println("Conexao foi bem estabelecida com sucesso " + conn);

        }catch (SQLException e){
            System.out.println("fala a conexao" + e.getMessage());
            System.out.println("verifique: ");
            System.out.println("1. MySql esta rodando ");
            System.out.println("2. O banco " + url + "realmente existe");
            System.out.println("3. O usuario ou senha estao corretos");
        }
    }


}
