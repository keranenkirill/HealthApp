package com.example.healthapp_v1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * information about developers an application (not ready yet)
 * @author Kirill Keränen
 */
public class AboutUs extends AppCompatActivity {
    //initialize variable
    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        //assign variable
        drawerLayout = findViewById(R.id.drawer_layout);
    }

    public void ClickMenu(View view){
        //open drawer
        MainActivity.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view){
        //close drawer
        MainActivity.closeDrawer(drawerLayout);
    }


    public void ClickHome(View view){
        //Redirect activity to home
        MainActivity.redirectActivity(this, MainActivity.class);

    }

    public void ClickDashboard(View view){
        //redirect activity dashboard
        MainActivity.redirectActivity(this, Dashboard.class);

    }

    public void ClickAboutUs(View view){
        //Recreate activity
        recreate();

    }

    /*public void ClickLogout(View view){
        //Close app
        MainActivity.Logout(this);

    }*/



    @Override
    protected void onPause(){
        super.onPause();
        //close drawer
        MainActivity.closeDrawer(drawerLayout);


    }
}