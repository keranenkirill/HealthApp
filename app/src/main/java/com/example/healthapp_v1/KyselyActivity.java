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
    private ArrayList<String> kysymykset = new ArrayList<String>();
    private int kysymysNumero = 0;
    private ArrayList<String> vastaukset = new ArrayList<String>();
    private int aktiviteetti1Pisteet = 0;
    private int aktiviteetti2Pisteet = 0;
    private int aktiviteetti3Pisteet = 0;
    private int aktiviteetti4Pisteet = 0;

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
                this.aktiviteetti1Pisteet++;
                this.aktiviteetti2Pisteet--;
                this.aktiviteetti4Pisteet--;
            } else {
                this.aktiviteetti1Pisteet--;
                this.aktiviteetti2Pisteet++;
                this.aktiviteetti4Pisteet++;
            }
            this.kysymysNumero++;
            kysymys.setText(kysymykset.get(kysymysNumero));
        } else if (this.kysymysNumero == 1) {
            if (view == findViewById(R.id.vastaus1)) {
                this.aktiviteetti1Pisteet++;
                this.aktiviteetti2Pisteet++;
                this.aktiviteetti3Pisteet--;
                this.aktiviteetti4Pisteet++;
            } else {
                this.aktiviteetti1Pisteet--;
                this.aktiviteetti2Pisteet--;
                this.aktiviteetti3Pisteet++;
                this.aktiviteetti4Pisteet--;
            }
            this.kysymysNumero++;
            kysymys.setText(kysymykset.get(kysymysNumero));
        } else if (this.kysymysNumero == 2) {
            if (view == findViewById(R.id.vastaus1)) {
                this.aktiviteetti1Pisteet++;
                this.aktiviteetti2Pisteet++;
                this.aktiviteetti3Pisteet++;
                this.aktiviteetti4Pisteet++;
            } else {
                this.aktiviteetti1Pisteet--;
                this.aktiviteetti2Pisteet++;
                this.aktiviteetti3Pisteet--;
                this.aktiviteetti4Pisteet++;
            }
            this.kysymysNumero++;
            kysymys.setText(kysymykset.get(kysymysNumero));
        } else if (this.kysymysNumero == 3) {
            if (view == findViewById(R.id.vastaus1)) {
                this.aktiviteetti1Pisteet++;
                this.aktiviteetti2Pisteet++;
                this.aktiviteetti3Pisteet++;
                this.aktiviteetti4Pisteet--;
            } else {
                this.aktiviteetti1Pisteet++;
                this.aktiviteetti2Pisteet++;
                this.aktiviteetti3Pisteet++;
                this.aktiviteetti4Pisteet++;
            }
            muodostaVastaus();
        }
    }

    public void muodostaVastaus() {
        String vastaus = "vastaus";
        if (aktiviteetti1Pisteet > aktiviteetti2Pisteet) {
            if (aktiviteetti1Pisteet > aktiviteetti3Pisteet) {
                if (aktiviteetti1Pisteet > aktiviteetti4Pisteet) {
                    vastaus = this.vastaukset.get(0);
                } else {
                    vastaus = this.vastaukset.get(3);
                }
            } else {
                if (aktiviteetti3Pisteet > aktiviteetti4Pisteet) {
                    vastaus = this.vastaukset.get(2);
                } else {
                    vastaus = this.vastaukset.get(3);
                }
            }
        } else {
            if (aktiviteetti2Pisteet > aktiviteetti3Pisteet) {
                if (aktiviteetti2Pisteet > aktiviteetti4Pisteet) {
                    vastaus = this.vastaukset.get(1);
                } else {
                    vastaus = this.vastaukset.get(3);
                }
            } else {
                if (aktiviteetti3Pisteet > aktiviteetti4Pisteet) {
                    vastaus = this.vastaukset.get(2);
                } else {
                    vastaus = this.vastaukset.get(3);
                }
            }
        }

        Intent intent = new Intent(this, VastausActivity.class);
        intent.putExtra(EXTRA_MESSAGE, vastaus);
        startActivity(intent);
    }
}