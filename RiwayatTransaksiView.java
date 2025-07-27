/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pbo2tgs1;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.List;

public class RiwayatTransaksiView {
    private VBox view;

    public RiwayatTransaksiView() {
        view = new VBox(10);
        view.setPadding(new Insets(20));

        Label title = new Label("Riwayat Transaksi");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField tfId = new TextField();
        tfId.setPromptText("ID Rekening");

        Button btnLihat = new Button("Lihat Riwayat");

        TextArea taHasil = new TextArea();
        taHasil.setEditable(false);
        taHasil.setPrefHeight(300);

        btnLihat.setOnAction(e -> {
            try {
                int id = Integer.parseInt(tfId.getText());

                TransaksiOperations transaksiOps = new TransaksiOperations();
                List<Transaksi> transaksiList = transaksiOps.getTransaksiByRekening(id);

                StringBuilder hasil = new StringBuilder();
                for (Transaksi trx : transaksiList) {
                    String tipe = trx.getTipe();
                    double jumlah = trx.getJumlah();
                    String waktu = trx.getTanggal().toString();

                    hasil.append(String.format("%s - Rp%.2f - %s\n", tipe, jumlah, waktu));
                }

                if (hasil.length() == 0) {
                    hasil.append("Belum ada transaksi untuk rekening ini.");
                }

                taHasil.setText(hasil.toString());

            } catch (NumberFormatException ex) {
                taHasil.setText("ID tidak valid.");
            } catch (Exception ex) {
                taHasil.setText("Gagal mengambil data: " + ex.getMessage());
            }
        });

        view.getChildren().addAll(title, tfId, btnLihat, taHasil);
    }

    public VBox getView() {
        return view;
    }
}


