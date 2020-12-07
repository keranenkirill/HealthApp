package com.example.healthapp_v1;

public class RentoutusAktiviteetit {
    private String nimi;
    private String kuvaus;
    private int pisteet;

    public RentoutusAktiviteetit(String nimi, String kuvaus) {
        this.nimi = nimi;
        this.kuvaus = kuvaus;
        this.pisteet = 0;
    }

    public void lisaaPisteita() {
        this.pisteet++;
    }

    public void vahennaPisteita() {
        this.pisteet--;
    }

    public int getPisteet() {
        return this.pisteet;
    }

    public String getNimi() {
        return this.nimi;
    }

    public String getKuvaus() {
        return this.kuvaus;
    }
}
