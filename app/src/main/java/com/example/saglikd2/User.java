package com.example.saglikd2;

public class User {
    private String dersadi;
    private String dersinifi;
    private String tarih;

    public User() {
    }

    public User(String dersadi, String dersinifi, String tarih) {
        this.dersadi = dersadi;
        this.dersinifi = dersinifi;
        this.tarih = tarih;
    }

    public String getDersadi() {
        return dersadi;
    }

    public void setDersadi(String dersadi) {
        this.dersadi = dersadi;
    }

    public String getDersinifi() {
        return dersinifi;
    }

    public void setDersinifi(String dersinifi) {
        this.dersinifi = dersinifi;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }
}
