package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    public static Connection conectar() {
        Connection con = null;

        String url = "jdbc:mysql://localhost:3306/wkcopia";
        String user = "root";
        String senha = "root";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,user,senha);

        } catch (SQLException | ClassNotFoundException erro){
            System.out.println("Falha na conexão com o banco de dados " + erro);
            return con;
        }

        return con;
    }
}