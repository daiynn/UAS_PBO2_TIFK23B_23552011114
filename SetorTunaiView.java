/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pbo2tgs1;

/**
 *
 * @author wderi
 */
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class SetorTunaiView {
    private VBox view;

    public SetorTunaiView() {
        view = new VBox(10);
        view.setPadding(new Insets(20));

        Label title = new Label("Setor Tunai");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField tfId = new TextField();
        tfId.setPromptText("ID Rekening");

        TextField tfJumlah = new TextField();
        tfJumlah.setPromptText("Jumlah Setor");

        Button btnSetor = new Button("Setor");

        Label lblResult = new Label();

        btnSetor.setOnAction(e -> {
            try {
                int id = Integer.parseInt(tfId.getText());
                double jumlah = Double.parseDouble(tfJumlah.getText());

                RekeningOperations ops = new RekeningOperations();
                Rekening r = ops.getRekeningById(id);

                if (r != null) {
                    r.setor(jumlah);
                    ops.updateSaldo(id, r.getSaldo());

                    TransaksiOperations transaksiOps = new TransaksiOperations();
                    transaksiOps.setorTunai(id, jumlah);

                    lblResult.setText("Setor berhasil. Saldo baru: " + r.getSaldo());
                } else {
                    lblResult.setText("Rekening tidak ditemukan.");
                }
            } catch (Exception ex) {
                lblResult.setText("Input tidak valid / Error: " + ex.getMessage());
            }
        });

        view.getChildren().addAll(title, tfId, tfJumlah, btnSetor, lblResult);
    }

    public VBox getView() {
        return view;
    }
}
