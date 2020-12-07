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

public class KyselyActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.project.MESSAGE";
    public static final String EXTRA_MESSAGE2 = "com.example.project.MESSAGE2";
    private ArrayList<String> kysymykset = new ArrayList<String>();
    private int kysymysNumero = 0;
    private ArrayList<String> vastaukset = new ArrayList<String>();
    private int aktiviteetti1Pisteet = 0;
    private int aktiviteetti2Pisteet = 0;
    private int aktiviteetti3Pisteet = 0;
    private int aktiviteetti4Pisteet = 0;

    RentoutusAktiviteetit kavelylenkki = new RentoutusAktiviteetit("Kävelylenkki", "Pyri keskittymään maisemiin. Voit halutessasi laittaa rentouttavaa musiikkia soimaan.");
    RentoutusAktiviteetit jooga = new RentoutusAktiviteetit("Jooga", "Kokeile joogaa. Tyyli vapaa.");
    RentoutusAktiviteetit ryhmäjumppa = new RentoutusAktiviteetit("Ryhmäjumppa", "Urheile muiden kanssa joko ulkona tai sisällä.");
    RentoutusAktiviteetit rentoutumishetki = new RentoutusAktiviteetit("Rentoutumishetki", "Keskity hengitykseen ja positiivisiin ajatuksiin.");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kysely);

        kysymykset.add("Haluatko rentoutua ulkona?");
        kysymykset.add("Haluatko viettää aikaa yksin?");
        kysymykset.add("Oletko nukkunut hyvin?");
        kysymykset.add("Haluatko kuunnella musiikkia?");



        vastaukset.add("Kävelylenkki. Laita rentouttavaa musiikkia soimaan ja keskity maisemiin");
        vastaukset.add("Rentouttavaa joogaa.");
        vastaukset.add("Ryhmäjumpaa, musiikilla tai ilman.");
        vastaukset.add("Rentoutumishetki. Keskity hengitykseen ja positiivisiin ajatuksiin.");

        TextView kysymys = findViewById(R.id.kysymys);
        kysymys.setText(kysymykset.get(0));
    }

    public void onButtonPress(View view) {
        Button button1 = findViewById(R.id.vastaus1);
        Button button2 = findViewById(R.id.vastaus2);
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