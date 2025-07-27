/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pbo2tgs1;

public class Rekening {
    private int id;
    private String jenis;
    private double saldo;

    public Rekening(int id, String jenis, double saldo) {
        this.id = id;
        this.jenis = jenis;
        this.saldo = saldo;
    }

    public int getId() {
        return id;
    }

    public String getJenis() {
        return jenis;
    }

    public double getSaldo() {
        return saldo;
    }

    public double hitungBunga() {
        if (jenis.equalsIgnoreCase("Tabungan")) {
            return saldo * 0.02;
        } else if (jenis.equalsIgnoreCase("Giro")) {
            return saldo * 0.01;
        } else {
            return 0;
        }
    }

    // untuk menyetor uang
    public void setor(double jumlah) {
        this.saldo += jumlah;
    }

    public void tarik(double jumlah) {
        this.saldo -= jumlah;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
