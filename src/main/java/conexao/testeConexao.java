package conexao;

import java.sql.Connection;

public class testeConexao {
    public static void main(String[] args) {
        Connection con = Conexao.conectar();
        if (con ==  null) {
            System.out.println("Nao deu certo");
        } else {
            System.out.println("Banco conectado com sucesso");
        }
    }

}
