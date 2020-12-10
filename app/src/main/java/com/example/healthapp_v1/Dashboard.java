package com.example.healthapp_v1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
/**
 * visual operations of the drawer
 * @author Kirill Keränen
 */
public class Dashboard extends AppCompatActivity {
    //initialize variable
    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //Assign variable
        drawerLayout = findViewById(R.id.drawer_layout);
    }

    /**
     * Avaa valikon
     *
     * @author Anni Karlsson
     * @param view valikon käyttöliittymä
     */
    public void ClickMenu(View view){

        MainActivity.openDrawer(drawerLayout);
    }

    /**
     * Sulkee valikon
     *
     * @param view valikon käyttöliittymä
     */
    public void ClickLogo(View view){
        MainActivity.closeDrawer(drawerLayout);
    }

    /**
     * Siirtyy kotinäytölle
     *
     * @param view valikon käyttöliittymä
     */
    public void CLickHome(View view){
        MainActivity.redirectActivity(this,MainActivity.class);

    }

    /**
     * Sulkee valikon
     *
     * @param view valikon käyttöliittymä
     */
    public void ClickDashboard(View view){
        //recreate  activity
        recreate();
    }

    /**
     * @author Anni Karlsson
     * @param view
     */
    public void ClickAboutUs(View view){
        //redirect activity about us
        MainActivity.redirectActivity(this,AboutUs.class);
    }

    /**
     * @author Anni Karlsson
     * @param view
     */
    public void ClickLogout(View view){
        //Close app
        //MainActivity.Logout(this);

    }

    @Override
    protected void onPause(){
        super.onPause();
        //close drawer
        MainActivity.closeDrawer(drawerLayout);


    }




}