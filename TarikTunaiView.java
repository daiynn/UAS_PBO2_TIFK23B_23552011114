/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pbo2tgs1;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class TarikTunaiView {
    private VBox view;

    public TarikTunaiView() {
        view = new VBox(10);
        view.setPadding(new Insets(20));

        Label title = new Label("Tarik Tunai");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField tfId = new TextField();
        tfId.setPromptText("ID Rekening");

        TextField tfJumlah = new TextField();
        tfJumlah.setPromptText("Jumlah Tarik");

        Button btnTarik = new Button("Tarik");

        Label lblResult = new Label();

        btnTarik.setOnAction(e -> {
            try {
                int id = Integer.parseInt(tfId.getText());
                double jumlah = Double.parseDouble(tfJumlah.getText());

                RekeningOperations ops = new RekeningOperations();
                Rekening r = ops.getRekeningById(id);

                if (r != null) {
                    if (jumlah > r.getSaldo()) {
                        lblResult.setText("Saldo tidak mencukupi.");
                    } else {
                        r.tarik(jumlah);
                        ops.updateSaldo(id, r.getSaldo());

                        TransaksiOperations transaksiOps = new TransaksiOperations();
                        transaksiOps.tarikTunai(id, jumlah);

                        lblResult.setText("Tarik berhasil. Saldo baru: " + r.getSaldo());
                    }
                } else {
                    lblResult.setText("Rekening tidak ditemukan.");
                }
            } catch (Exception ex) {
                lblResult.setText("Input tidak valid / Error: " + ex.getMessage());
            }
        });

        view.getChildren().addAll(title, tfId, tfJumlah, btnTarik, lblResult);
    }

    public VBox getView() {
        return view;
    }
}

