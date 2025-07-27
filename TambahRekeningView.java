/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pbo2tgs1;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class TambahRekeningView {
    private VBox view;
    private TextField saldoField;
    private ComboBox<String> jenisCombo;
    private Label statusLabel;

    public TambahRekeningView() {
        view = new VBox(10);
        view.setPadding(new Insets(20));

        Label judul = new Label("Tambah Rekening");
        judul.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        jenisCombo = new ComboBox<>();
        jenisCombo.getItems().addAll("Tabungan", "Giro");
        jenisCombo.setValue("Tabungan");

        saldoField = new TextField();
        saldoField.setPromptText("Masukkan saldo awal");

        Button simpanBtn = new Button("Simpan");
        simpanBtn.setOnAction(e -> simpanRekening());

        statusLabel = new Label();
        statusLabel.setTextFill(Color.GREEN);

        view.getChildren().addAll(judul, new Label("Jenis Rekening:"), jenisCombo,
                new Label("Saldo Awal:"), saldoField, simpanBtn, statusLabel);
    }

    public VBox getView() {
        return view;
    }

    private void simpanRekening() {
        String jenis = jenisCombo.getValue();
        String saldoText = saldoField.getText();

        if (saldoText.isEmpty()) {
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("Saldo tidak boleh kosong.");
            return;
        }

        try {
            double saldo = Double.parseDouble(saldoText);
            RekeningOperations ops = new RekeningOperations();
            ops.tambahRekening(jenis, saldo);
            statusLabel.setTextFill(Color.GREEN);
            statusLabel.setText("Rekening berhasil ditambahkan!");
            saldoField.clear();
        } catch (NumberFormatException e) {
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("Saldo harus berupa angka.");
        } catch (Exception e) {
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("Gagal menyimpan rekening: " + e.getMessage());
        }
    }
}
