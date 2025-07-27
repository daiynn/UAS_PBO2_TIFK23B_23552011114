/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pbo2tgs1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransaksiOperations {
    private Connection conn;

    public TransaksiOperations() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kasirbank_system", "root", "");
    }

    // Menambahkan transaksi baru
    public void tambahTransaksi(int rekeningId, String tipe, double jumlah) {
        String sql = "INSERT INTO transaksi (rekening_id, tipe, jumlah, tanggal) VALUES (?, ?, ?, NOW())";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, rekeningId);
            ps.setString(2, tipe); // "Setor" atau "Tarik"
            ps.setDouble(3, jumlah);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Mengambil semua transaksi berdasarkan rekening
    public List<Transaksi> getTransaksiByRekening(int rekeningId) {
        List<Transaksi> list = new ArrayList<>();
        String sql = "SELECT * FROM transaksi WHERE rekening_id = ? ORDER BY tanggal DESC";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, rekeningId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String tipe = rs.getString("tipe");
                double jumlah = rs.getDouble("jumlah");
                Timestamp tanggal = rs.getTimestamp("tanggal");

                list.add(new Transaksi(id, rekeningId, tipe, jumlah, tanggal));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        

        return list;
    }
    // Tambahkan ke class TransaksiOperations

    // Catat transaksi setor tunai
    public void setorTunai(int rekeningId, double jumlah) {
        tambahTransaksi(rekeningId, "Setor", jumlah);
    }

    public void tarikTunai(int rekeningId, double jumlah) {
        tambahTransaksi(rekeningId, "Tarik", jumlah);
    }



    // Mengambil semua transaksi (semua rekening)
    public List<Transaksi> getSemuaTransaksi() {
        List<Transaksi> list = new ArrayList<>();
        String sql = "SELECT * FROM transaksi ORDER BY tanggal DESC";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int rekeningId = rs.getInt("rekening_id");
                String tipe = rs.getString("tipe");
                double jumlah = rs.getDouble("jumlah");
                Timestamp tanggal = rs.getTimestamp("tanggal");

                list.add(new Transaksi(id, rekeningId, tipe, jumlah, tanggal));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}

