package com.example.healthapp_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Conducts a survey of four questions and returns a relaxing activity based on the survey.
 * Passes the relaxing activity and it's description forward to VastausActivity.java.
 * Uses RentoutumisActiviteetit.java class to keep track of the points during the survey.
 * @author Jimi Takamäki
 */
public class KyselyActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.project.MESSAGE";
    public static final String EXTRA_MESSAGE2 = "com.example.project.MESSAGE2";
    private ArrayList<String> kysymykset = new ArrayList<String>();
    private int kysymysNumero = 0;

    RentoutusAktiviteetit kavelylenkki = new RentoutusAktiviteetit("Kävelylenkki", "Pyri keskittymään maisemiin. Voit halutessasi laittaa rentouttavaa musiikkia soimaan.");
    RentoutusAktiviteetit jooga = new RentoutusAktiviteetit("Jooga", "Kokeile joogaa. Tyyli vapaa.");
    RentoutusAktiviteetit ryhmäjumppa = new RentoutusAktiviteetit("Ryhmäjumppa", "Urheile muiden kanssa joko ulkona tai sisällä.");
    RentoutusAktiviteetit rentoutumishetki = new RentoutusAktiviteetit("Rentoutumishetki", "Keskity hengitykseen ja positiivisiin ajatuksiin.");

    @Override

    /**
     * Adds the four questions to the kysymykset arraylist.
     * Sets the question on it's respective TextView.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kysely);

        kysymykset.add("Haluatko rentoutua ulkona?");
        kysymykset.add("Haluatko viettää aikaa yksin?");
        kysymykset.add("Oletko nukkunut hyvin?");
        kysymykset.add("Haluatko kuunnella musiikkia?");

        TextView kysymys = findViewById(R.id.kysymys);
        kysymys.setText(kysymykset.get(0));
    }

    /**
     * Executes when either one of the answer buttons is pressed.
     * Determines which button was pressed and adds points for the relaxing acitivities matching the user's answer.
     * Calls muodostaVastaus function at the end.
     * @param view The button.
     */
    public void onButtonPress(View view) {
        TextView kysymys = findViewById(R.id.kysymys);
        if (this.kysymysNumero == 0) {
            if (view == findViewById(R.id.vastaus1)) {
                kavelylenkki.lisaaPisteita();
                jooga.vahennaPisteita();
                rentoutumishetki.vahennaPisteita();
            } else {
                kavelylenkki.vahennaPisteita();
                jooga.lisaaPisteita();
                rentoutumishetki.lisaaPisteita();
            }
            this.kysymysNumero++;
            kysymys.setText(kysymykset.get(kysymysNumero));
        } else if (this.kysymysNumero == 1) {
            if (view == findViewById(R.id.vastaus1)) {
                kavelylenkki.lisaaPisteita();
                jooga.lisaaPisteita();
                ryhmäjumppa.lisaaPisteita();
                rentoutumishetki.lisaaPisteita();
            } else {
                kavelylenkki.vahennaPisteita();
                jooga.vahennaPisteita();
                ryhmäjumppa.lisaaPisteita();
                rentoutumishetki.vahennaPisteita();
            }
            this.kysymysNumero++;
            kysymys.setText(kysymykset.get(kysymysNumero));
        } else if (this.kysymysNumero == 2) {
            if (view == findViewById(R.id.vastaus1)) {
                kavelylenkki.lisaaPisteita();
                jooga.lisaaPisteita();
                ryhmäjumppa.lisaaPisteita();
                rentoutumishetki.lisaaPisteita();
            } else {
                kavelylenkki.vahennaPisteita();
                jooga.lisaaPisteita();
                ryhmäjumppa.lisaaPisteita();
                rentoutumishetki.lisaaPisteita();
            }
            this.kysymysNumero++;
            kysymys.setText(kysymykset.get(kysymysNumero));
        } else if (this.kysymysNumero == 3) {
            if (view == findViewById(R.id.vastaus1)) {
                kavelylenkki.lisaaPisteita();
                jooga.lisaaPisteita();
                ryhmäjumppa.lisaaPisteita();
                rentoutumishetki.vahennaPisteita();
            } else {
                kavelylenkki.lisaaPisteita();
                jooga.lisaaPisteita();
                ryhmäjumppa.lisaaPisteita();
                rentoutumishetki.lisaaPisteita();
            }
            muodostaVastaus();
        }
    }

    /**
     * Chooses a relaxing activity for the user based off their answers in the survey.
     * Sends the relaxing activity's name and description with intent to VastausAcitivity.java.
     */
    public void muodostaVastaus() {
        String vastausNimi = "vastaus";
        String vastausKuvaus = "kuvaus";
        if (kavelylenkki.getPisteet() > jooga.getPisteet()) {
            if (kavelylenkki.getPisteet() > ryhmäjumppa.getPisteet()) {
                if (kavelylenkki.getPisteet() > rentoutumishetki.getPisteet()) {
                    vastausNimi = kavelylenkki.getNimi();
                    vastausKuvaus = kavelylenkki.getKuvaus();
                } else {
                    vastausNimi = rentoutumishetki.getNimi();
                    vastausKuvaus = rentoutumishetki.getKuvaus();
                }
            } else {
                if (ryhmäjumppa.getPisteet() > rentoutumishetki.getPisteet()) {
                    vastausNimi = ryhmäjumppa.getNimi();
                    vastausKuvaus = ryhmäjumppa.getKuvaus();
                } else {
                    vastausNimi = rentoutumishetki.getNimi();
                    vastausKuvaus = rentoutumishetki.getKuvaus();
                }
            }
        } else {
            if (jooga.getPisteet() > ryhmäjumppa.getPisteet()) {
                if (jooga.getPisteet() > rentoutumishetki.getPisteet()) {
                    vastausNimi = jooga.getNimi();
                    vastausKuvaus = jooga.getKuvaus();
                } else {
                    vastausNimi = rentoutumishetki.getNimi();
                    vastausKuvaus = rentoutumishetki.getKuvaus();
                }
            } else {
                if (ryhmäjumppa.getPisteet() > rentoutumishetki.getPisteet()) {
                    vastausNimi = ryhmäjumppa.getNimi();
                    vastausKuvaus = ryhmäjumppa.getKuvaus();
                } else {
                    vastausNimi = rentoutumishetki.getNimi();
                    vastausKuvaus = rentoutumishetki.getKuvaus();
                }
            }
        }

        Intent intent = new Intent(this, VastausActivity.class);
        intent.putExtra(EXTRA_MESSAGE, vastausNimi);
        intent.putExtra(EXTRA_MESSAGE2, vastausKuvaus);
        startActivity(intent);
    }
}