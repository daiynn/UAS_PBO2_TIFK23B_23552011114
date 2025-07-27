/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pbo2tgs1;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class DashboardKasirView {
    private Stage primaryStage;

    public DashboardKasirView(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public BorderPane getView() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

        // Sidebar Menu
        VBox menu = new VBox(10);
        menu.setPadding(new Insets(10));
        menu.setStyle("-fx-background-color: #f0f0f0;");

        Label title = new Label("Kasir Bank Menu");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Button btnTambah = new Button("Tambah Rekening");
        Button btnLihat = new Button("Lihat Semua Rekening");
        Button btnSetor = new Button("Setor Tunai");
        Button btnTarik = new Button("Tarik Tunai");
        Button btnRiwayat = new Button("Riwayat Transaksi");
        Button btnLogout = new Button("Logout");

        // Gaya tombol
        for (Button btn : new Button[]{btnTambah, btnLihat, btnSetor, btnTarik, btnRiwayat, btnLogout}) {
            btn.setPrefWidth(180);
            btn.setStyle("-fx-font-size: 14px;");
        }

        // Aksi setiap tombol (sementara tampilkan alert saja)
        btnTambah.setOnAction(e -> {
    TambahRekeningView tambahView = new TambahRekeningView();
    root.setCenter(tambahView.getView());
});

        btnLihat.setOnAction(e -> {
    LihatRekeningView lihatView = new LihatRekeningView();
    root.setCenter(lihatView.getView());
});

        btnSetor.setOnAction(e -> {
    SetorTunaiView setorView = new SetorTunaiView();
    root.setCenter(setorView.getView());
});

        btnTarik.setOnAction(e -> {
    TarikTunaiView tarikView = new TarikTunaiView();
    root.setCenter(tarikView.getView());
});

        btnRiwayat.setOnAction(e -> {
    RiwayatTransaksiView riwayatView = new RiwayatTransaksiView();
    root.setCenter(riwayatView.getView());
});

        btnLogout.setOnAction(e -> {
            LoginView loginView = new LoginView(primaryStage);
            primaryStage.setScene(new Scene(loginView.getView(), 800, 600));
        });

        menu.getChildren().addAll(
                title, btnTambah, btnLihat, btnSetor, btnTarik, btnRiwayat, btnLogout
        );

        root.setLeft(menu);

        // Konten utama kosong dulu, nanti diisi masing-masing View
        Label contentLabel = new Label("Selamat datang di Aplikasi Kasir Bank!");
        contentLabel.setStyle("-fx-font-size: 16px;");
        VBox content = new VBox(contentLabel);
        content.setPadding(new Insets(20));
        root.setCenter(content);

        return root;
    }

    private void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText("Klik: " + message);
        alert.showAndWait();
    }
}
