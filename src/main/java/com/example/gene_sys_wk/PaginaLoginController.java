package com.example.gene_sys_wk;

import conexao.Conexao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import lancamento.lancamentoController;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class PaginaLoginController implements Initializable {

    public String empresaSelecionada;



    @FXML
    private ComboBox box;
        ObservableList<String> list = FXCollections.observableArrayList("SM_COM", "ABC_GUIND", "GENESYS_CONTABILIDADE", "MASTER");



    @FXML
    private TextField usuarioDigitado;

    @FXML
    private TextField senhaDigitada;

    @FXML
    private Button botaoEntrar;


    @FXML
    private void handleButtonClick(ActionEvent event) throws IOException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ArrayList<String> usuariosEsenha = usuarioDAO.buscarUsuarioESenha();

        for (int i = 0; i < usuariosEsenha.size(); i += 2) {
            if (usuariosEsenha.get(i).equals(usuarioDigitado.getText()) && usuariosEsenha.get(i + 1).equals(senhaDigitada.getText())) {
                System.out.println("Entrou com sucesso");
                System.out.println(empresaSelecionada);
                empresaSelecionada = (String) box.getValue();

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("lancamento.fxml"));
                Parent root = fxmlLoader.load();
                lancamentoController lancController = fxmlLoader.getController();
                lancController.setEmpresaSelecionada(empresaSelecionada); // Passando a empresa selecionada

                Scene scene = new Scene(root, 1000, 800);
                Stage stage = (Stage) botaoEntrar.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } else {
                System.out.println("Entrada bloqueada");
            }
        }


    }


    public String getEmpresaSelecionada() {
        return empresaSelecionada;
    }

    public void setEmpresaSelecionada(String empresaSelecionada) {
        this.empresaSelecionada = empresaSelecionada;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
        box.setItems(list);
        box.setValue(list.get(0));
    }
}

