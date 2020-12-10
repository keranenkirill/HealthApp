package com.example.healthapp_v1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;


/**
 * Main Page and list-history operations
 * @author Kirill Keränen
 */
public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;

    public static final String EXTRA_MESSAGE = "com.example.project.MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(getIntent().getExtras() == null){
            MemManager.instance.init(this);
            if(!MemManager.instance.has("historia")) {
                MemManager.instance.add("historia", new JSONArray());
            }
        }


        //assign variable
        drawerLayout = findViewById(R.id.drawer_layout);
    }

    /**
     * Luo uuden kysely aktiviteetin
     *
     * @author Jimi Takamaki
     * @param view valikon käyttöliittymä
     */
    public void onFabClick(View view) {
        Intent intent = new Intent(this, KyselyActivity.class);
        startActivity(intent);
    }

    /**
     * Kutsuu openDrawerin
     *
     * @param view valikon käyttöliittymä
     */
    public void ClickMenu(View view){
        //Open drawer
        openDrawer(drawerLayout);

    }

    /**
     * Avaa sivuvalikon
     *
     * @param drawerLayout sivuvalikko
     */
    public static void openDrawer(DrawerLayout drawerLayout){
        drawerLayout.openDrawer(GravityCompat.START);

    }

    /**
     * Kutsuu metodin joka sulkee valikon
     *
     * @param view valikon käyttöliittymä
     */
    public void ClickLogo(View view){
        closeDrawer(drawerLayout);
        
    }

    /**
     * Tarkistaa onko sivuvalikko auki, jos auki niin sulkee
     *
     * @param drawerLayout sivuvalikko
     */
    public static void closeDrawer(DrawerLayout drawerLayout) {

        //check condition
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            //whn drawer is open
            //close drawer
            drawerLayout.closeDrawer(GravityCompat.START);

        }

    }

    public void ClickDashboard(View view){
        //redirect activity to dashboard
        redirectActivity(this,Dashboard.class);
    }

    /**
     * Siirtyy yhdesta toiseen toimintaan
     *
     * @param activity toiminta
     * @param aClass hakee AppCompatActivity tyyppisen luokan
     */
    public static void redirectActivity(Activity activity, Class<?extends AppCompatActivity> aClass) {

        Intent intent = new Intent(activity, aClass);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        activity.startActivity(intent);

    }

    /**
     * Siirtyy yhdestä toiseen toimintaan
     *
     * @param view valikon käyttöliittymä
     */
    public void ClickAboutUs(View view){
        redirectActivity(this, AboutUs.class);

    }

    /**
     * Kutsuu metodin, joka mahdollistaa sovelluksensta poistumisen
     *
     * @param view valikon käyttöliittymä
     */
    public void ClickLogout(View view){
        //close app
        logout(this);

    }

    /**
     * Metodi käynistyessään kysyy käyttäjältä haluaako poistua. Jos "Yes" niin poistuu.
     * Jos "No" niin pysyy sovelluksessa
     *
     * @param activity toiminta
     */
    public void logout(final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setTitle("Logout");

        builder.setMessage("Are you sure you want to quit?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finishAffinity();
                System.exit(0);

            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        builder.show();

    }

    @Override
    protected void onPause(){
        super.onPause();
        closeDrawer(drawerLayout);
        MemManager.instance.save(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<String> historia = new ArrayList<>();
        MemManager.instance.get("historia", historia);
        ListView listViewHomer = findViewById(R.id.homer);
        listViewHomer.setAdapter(new ArrayAdapter<String>(

                this, R.layout.historia_alkio_layout, historia
        ) );

    }
}