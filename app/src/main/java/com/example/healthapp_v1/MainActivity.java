package com.example.healthapp_v1;

import androidx.appcompat.app.AppCompatActivity;
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



public class MainActivity extends AppCompatActivity {
    //initialize variable
    DrawerLayout drawerLayout;
    FloatingActionButton fab;

    public static final String EXTRA_MESSAGE = "com.example.project.MESSAGE";

    String vastaus;

    //public static void Logout(AboutUs aboutUs) {
   // }



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

    public void onFabClick(View view) {
        Intent intent = new Intent(this, KyselyActivity.class);
        startActivity(intent);
    }

    public void ClickMenu(View view){
        //Open drawer
        openDrawer(drawerLayout);

        ArrayList<String> nimetMuistiin = new ArrayList<>();

        Collections.addAll(nimetMuistiin,"Fucker", "Shitface", "Assbutter", "Tall fucker");

        MemManager.instance.add("numerot", nimetMuistiin);


        ArrayList<String> nimetMuistista = new ArrayList<>();

        MemManager.instance.get("numerot", nimetMuistista);



        JSONArray nimetMuistista2 = (JSONArray)MemManager.instance.get("numerot");





        for(String item : nimetMuistista){
            Log.w("VARASTO", item);
        }

        Log.w("VARASTO", "" + MemManager.instance.verbose());





    }

    public static void openDrawer(DrawerLayout drawerLayout){
        //Open drawer Layout
        drawerLayout.openDrawer(GravityCompat.START);

    }

    public void ClickLogo(View view){
        //Close drawer
        closeDrawer(drawerLayout);
        
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        //close drawer layout
        //check condition
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            //whn drawer is open
            //close drawer
            drawerLayout.closeDrawer(GravityCompat.START);

        }

    }

    public void ClickHome(View view){
        //recreate activity
        //recreate();



    }

    public void ClickDashboard(View view){
        //redirect activity to dashboard
        redirectActivity(this,Dashboard.class);

    }

    public static void redirectActivity(Activity activity, Class aClass) {
        //initialize intetnt
        Intent intent = new Intent(activity, aClass);
        //set flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //start activity
        activity.startActivity(intent);

    }

    public void ClickAboutUs(View view){
        //redirect activity to aboutus
        redirectActivity(this, AboutUs.class);

    }


    public void ClickLogout(View view){
        //close app
        logout(this);

    }

    public void logout(final Activity activity) {
        //Initialize alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        //Set title
        builder.setTitle("Logout");
        //set message
        builder.setMessage("Are you sure you want to quit?");
        //positive yes button
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Finish activity
                activity.finishAffinity();
                //Exit app
                System.exit(0);

            }
        });
        //Negative button
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Dismiss dialog
                dialog.dismiss();

            }
        });
        //show dialog
        builder.show();

    }

    @Override
    protected void onPause(){
        super.onPause();
        //close drawer
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