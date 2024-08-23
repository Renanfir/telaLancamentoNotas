package lancamento;

import conexao.Conexao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class lancamentoEntity {

    private String empresa;
    private Date dataa;
    private int debito;
    private int credito;
    private float valor;
    private int historico;
    private String complemento;


    public boolean incluirLancamento() throws ClassNotFoundException {
        String sql = "insert into lancamento ";
        sql += "(empresa,dataa,debito,credito,valor,historico,complemento) ";
        sql += "values (?,?,?,?,?,?,?)";
        Connection con = Conexao.conectar();
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, this.empresa);
            stm.setDate(2, this.dataa);
            stm.setInt(3, this.debito);
            stm.setInt(4, this.credito);
            stm.setFloat(5, this.valor);
            stm.setInt(6, this.historico);
            stm.setString(7, this.complemento);
            stm.execute();
        } catch (SQLException error) {
            System.out.println("Inclusao do lancamento com problema\n, Verifique");
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return empresa +" , "+ dataa +" , "+ debito +" , "+ credito +" , "+ valor +" , "+ historico +" , "+ complemento;
    }


    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public Date getDataa() {
        return dataa;
    }

    public void setDataa(Date dataa) {
        this.dataa = dataa;
    }

    public int getDebito() {
        return debito;
    }

    public void setDebito(int debito) {
        this.debito = debito;
    }

    public int getCredito() {
        return credito;
    }

    public void setCredito(int credito) {
        this.credito = credito;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getHistorico() {
        return historico;
    }

    public void setHistorico(int historico) {
        this.historico = historico;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }






}
