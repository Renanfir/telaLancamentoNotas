package com.example.gene_sys_wk;


import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO {


    public ArrayList<String> buscarUsuarioESenha() {
        ArrayList<String> array = new ArrayList<>();

        String sql = "SELECT usuario, senha FROM users";

        try (Connection conn = Conexao.conectar();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                array.add(resultSet.getString("usuario"));
                array.add(resultSet.getString("senha"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return array;
    }
}

