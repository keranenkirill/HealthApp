package com.example.healthapp_v1;

/**
 * Keeps track of each relaxing activity's points and also includes their names and descriptions.
 * @author Merili Ruuto
 */
public class RentoutusAktiviteetit {
    private String nimi;
    private String kuvaus;
    private int pisteet;

    /**
     * This is the constructor. Sets the relaxing acitivitys' name and desctiption.
     * @param nimi Name of the relaxing acitivity.
     * @param kuvaus Description of the relaxing activity.
     */
    public RentoutusAktiviteetit(String nimi, String kuvaus) {
        this.nimi = nimi;
        this.kuvaus = kuvaus;
        this.pisteet = 0;
    }

    /**
     * Adds points for the relaxing activity.
     */
    public void lisaaPisteita() {
        this.pisteet++;
    }

    /**
     * Substracts points from the relaxing activity.
     */
    public void vahennaPisteita() {
        this.pisteet--;
    }

    /**
     * Returns the points of the relaxing activity.
     * @return the points of the relaxing activity.
     */
    public int getPisteet() {
        return this.pisteet;
    }

    /**
     * Returns the name of the relaxing activity.
     * @return the name of the relaxing activity.
     */
    public String getNimi() {
        return this.nimi;
    }

    /**
     * Returns the description of the relaxing activity.
     * @return the description of the relaxing activity.
     */
    public String getKuvaus() {
        return this.kuvaus;
    }
}
