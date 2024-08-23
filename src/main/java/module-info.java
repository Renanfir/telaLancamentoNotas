module com.example.gene_sys_wk {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.gene_sys_wk to javafx.fxml;
    exports com.example.gene_sys_wk;

    opens lancamento to javafx.fxml;
    exports lancamento;
}