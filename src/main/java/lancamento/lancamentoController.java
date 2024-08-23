package lancamento;



import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
public class lancamentoController extends Application implements Initializable {


    private String empresaSelecionada;

    public void setEmpresaSelecionada(String empresaSelecionada) {
        this.empresaSelecionada = empresaSelecionada;
    }


    @FXML
    private ChoiceBox boxAnos;
    ObservableList<String> list = FXCollections.observableArrayList
            ("2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025");


    @Override
    public void start(Stage stage) throws IOException {
        stage.setResizable(false);
    }

    @FXML
    private DatePicker boxData;

    @FXML
    private TextField boxDebito, boxComplemento, boxHistorico, boxValor, boxCredito;

    @FXML
    private TableView<lancamentoEntity> tabelaLancamento;

    @FXML
    private TableColumn<lancamentoEntity, Date> dataaColumn;

    @FXML
    private TableColumn<lancamentoEntity, Integer> creditoColumn, debitoColumn, historicoColumn;

    @FXML
    private TableColumn<lancamentoEntity, Float> valorColumn;

    @FXML
    private TableColumn<lancamentoEntity, String> complementoColumn;

    private ObservableList<lancamentoEntity> lancamentoList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        boxAnos.setItems(list);

    }


    @FXML
    private void enviarDados(ActionEvent event) throws IOException, ClassNotFoundException {
        lancamentoEntity lanc = new lancamentoEntity();
        lanc.setEmpresa(empresaSelecionada);
        lanc.setDataa(Date.valueOf(boxData.getValue()));
        lanc.setDebito(Integer.parseInt(boxDebito.getText()));
        lanc.setCredito(Integer.parseInt(boxCredito.getText()));
        lanc.setValor(Float.parseFloat(boxValor.getText()));
        lanc.setHistorico(Integer.parseInt(boxHistorico.getText()));
        lanc.setComplemento(boxComplemento.getText());

        dataaColumn.setCellValueFactory(new PropertyValueFactory<>("dataa"));
        debitoColumn.setCellValueFactory(new PropertyValueFactory<>("debito"));
        creditoColumn.setCellValueFactory(new PropertyValueFactory<>("credito"));
        valorColumn.setCellValueFactory(new PropertyValueFactory<>("valor"));
        historicoColumn.setCellValueFactory(new PropertyValueFactory<>("historico"));
        complementoColumn.setCellValueFactory(new PropertyValueFactory<>("complemento"));

        if (lanc.incluirLancamento()) {
            System.out.println("Lançamento incluído com sucesso");

            lancamentosDAO dao = new lancamentosDAO();
            ArrayList<lancamentoEntity> lancamentos = dao.buscaLancamentos();

            lancamentoList.clear();


            for (lancamentoEntity lancamento : lancamentos) {
                if (lancamento.getEmpresa().equals(empresaSelecionada)) {
                    lancamentoList.add(lancamento);
                }
            }


            tabelaLancamento.setItems(lancamentoList);

        } else {
            System.out.println("Erro ao incluir o lançamento");

        }
    }
}
