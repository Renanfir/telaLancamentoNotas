package lancamento;

import conexao.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class lancamentosDAO {

    public ArrayList<lancamentoEntity> buscaLancamentos() {
        ArrayList<lancamentoEntity> array = new ArrayList<>();

        String sql = "SELECT empresa, dataa, debito, credito, valor, historico, complemento FROM lancamento";

        try (Connection conn = Conexao.conectar();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                lancamentoEntity lancamento = new lancamentoEntity();
                lancamento.setEmpresa(resultSet.getString("empresa"));
                lancamento.setDataa(resultSet.getDate("dataa"));
                lancamento.setDebito(resultSet.getInt("debito"));
                lancamento.setCredito(resultSet.getInt("credito"));
                lancamento.setValor(resultSet.getFloat("valor"));
                lancamento.setHistorico(resultSet.getInt("historico"));
                lancamento.setComplemento(resultSet.getString("complemento"));

                array.add(lancamento);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return array;
    }
}
