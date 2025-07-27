/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pbo2tgs1;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.util.List;

public class LihatRekeningView {
    private VBox view;
    private TableView<Rekening> table;

    public LihatRekeningView() {
        view = new VBox(10);
        view.setPadding(new Insets(20));

        Label title = new Label("Daftar Rekening");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        table = new TableView<>();

        // Kolom ID
        TableColumn<Rekening, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());

        // Kolom Jenis
        TableColumn<Rekening, String> jenisCol = new TableColumn<>("Jenis");
        jenisCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getJenis()));

        // Kolom Saldo
        TableColumn<Rekening, Double> saldoCol = new TableColumn<>("Saldo");
        saldoCol.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getSaldo()).asObject());

        // Kolom Bunga
        TableColumn<Rekening, Double> bungaCol = new TableColumn<>("Bunga");
        bungaCol.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().hitungBunga()).asObject());

        table.getColumns().addAll(idCol, jenisCol, saldoCol, bungaCol);

        loadData();

        view.getChildren().addAll(title, table);
    }

    public VBox getView() {
        return view;
    }

    private void loadData() {
        try {
            RekeningOperations ops = new RekeningOperations();
            List<Rekening> data = ops.getSemuaRekening();
            table.getItems().setAll(data);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Gagal memuat data: " + e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }
}
