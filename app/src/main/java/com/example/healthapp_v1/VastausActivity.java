package com.example.healthapp_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;

public class VastausActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.project.MESSAGE";
    private String vastaus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vastaus);

        Intent intent = getIntent();
        vastaus = intent.getStringExtra(KyselyActivity.EXTRA_MESSAGE);

        TextView textView = findViewById(R.id.vastaus);
        textView.setText(vastaus);
    }

    public void onButtonPress(View view) {
        if (view == findViewById(R.id.suorita)) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(EXTRA_MESSAGE, vastaus);


            /**
            JSONArray historia = (JSONArray)MemManager.instance.get("historia");

            historia.put(vastaus);

            */


            startActivity(intent);
        } else if (view == findViewById(R.id.peruuta)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}