/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pbo2tgs1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RekeningOperations {
    private Connection conn;

    public RekeningOperations() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kasirbank_system", "root", "");
    }

    public List<Rekening> getSemuaRekening() {
        List<Rekening> list = new ArrayList<>();
        String sql = "SELECT * FROM rekening";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String jenis = rs.getString("jenis");
                double saldo = rs.getDouble("saldo");
                list.add(new Rekening(id, jenis, saldo));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void tambahRekening(String jenis, double saldo) {
        String sql = "INSERT INTO rekening (jenis, saldo) VALUES (?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, jenis);
            ps.setDouble(2, saldo);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Tambahkan ke dalam class RekeningOperations

    //
    public Rekening getRekeningById(int id) {
        String sql = "SELECT * FROM rekening WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String jenis = rs.getString("jenis");
                double saldo = rs.getDouble("saldo");
                return new Rekening(id, jenis, saldo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update saldo rekening
    public void updateSaldo(int id, double saldoBaru) {
        String sql = "UPDATE rekening SET saldo = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, saldoBaru);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    
}
