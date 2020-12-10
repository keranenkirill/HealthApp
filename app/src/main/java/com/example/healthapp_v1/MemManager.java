package com.example.healthapp_v1;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Kyselyn tallentaminen
 * @author Kirill Keranen (ryhmä 7)
 * @author Alex L (ryhmä 9)
 *
 */
public class MemManager {
    public static MemManager instance = new MemManager();
    private static final String FILE_ID = "MEMORY_APP_PRIVATEALLOCATION";

    private JSONObject json_store;



    private MemManager(){
        Log.d("STORAGE", "hi");

    }

    /**
     *
     * @return palauttaa json stringina
     */
    public String verbose(){//
        return json_store.toString();
    }

    /**
     *
     *  @param context tuhoaa tiedoston jos jokin menee vikaan, voi menettaa koko datan
     */
    public void purge(Context context){//
        context.deleteFile(FILE_ID);

    }

    /**
     *  Alustaa puhelimen muistista json-muotoisen tiedoston
     *  Jos tiedosto ei loydy, se luo stringistä tyhjän json tiedoston
     *  @param context activityn konteksti
     */
    public void init(Context context){
        File internal_filepath = context.getFilesDir();
        File fileref = new File(internal_filepath, FILE_ID);
        byte[] content;//8-bitin lista

        try{
            if(fileref.exists()){//jos viite on olemassa, tiedosto on internal muistossa
                InputStream reader = new FileInputStream(fileref);

                content = new byte[reader.available()];//luo bittilistan, niin ison kuin tidoston koko
                reader.read(content);
                reader.close();//sulkee yhteyden tiedostoon

                try{//kääntää kopioidun "content"-bittilistan stringiksi ja sitten stringinstä Json objektiksi
                    this.json_store = new JSONObject(new String(content, StandardCharsets.UTF_8));//jos onnistuu niin luodaan viite "json_store" joka viittaa JSONobjektiin

                }catch(JSONException ex){//jos ei onnistu niin laittaa logiin errorin
                    Log.w("InternelStorage", "Error parsing to JSONObject", ex);

                }

            }else{//jos tiedostoa ei ollut olemassa, niin se luodaan alla
                OutputStream writer = new FileOutputStream(fileref);
                writer.write(("{}").getBytes());//luodaan stringi joka muutetaan biteiksi ja tallenetaan tiedostoon
                writer.close();

                this.json_store = new JSONObject();//uusi viite

            }
        }catch(IOException ioe){// jos tiedoston lukeminen Jsoniksi epäonnistuu...
            Log.w("InternalStorage", "Error writing " + fileref, ioe);//logiin error teksti

        }

    }

    /**
     * Palauttaa JSON listasta objektin
     * @author keran
     * @param name JSONlistan alkion nimi
     * @return Object JSON listan alkio
     */
    public Object get(String name){// hae json listasta
        return json_store.opt(name);
    }

    /**
     *  jos json tiedostossa on stringin niminen alkio
     * @param name JSONlistan alkion nimi
     * @return palauttaa json tiedoston
     */
    public boolean has(String name){
        return json_store.has(name);

    }

    /**
     * tuodaan tietyn nimisesta lokerosta dataa
     * @param name datalokero
     * @param list
     */
    public void get(String name, ArrayList list){
        JSONArray jar = json_store.optJSONArray(name);

        try {
            for(int i = 0; i< jar.length(); i++){
                list.add(jar.get(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tallenetaan uusi tieto tiedostoon
     * @param name JSON listan alkion nimi
     * @param item objekti
     */
    public void add(String name, Object item){
        try{
            json_store.putOpt(name, item);

        }catch(JSONException e){
            e.printStackTrace();

        }

    }


    /**
     * Muuttaa arraylistin sisällön json muotoon ja tallentaa
     * @param name
     * @param list Json-t
     */
    public void add(String name, ArrayList list){
        JSONArray jar = new JSONArray();

        for(Object item : list){
            jar.put(item);
        }

        try {// tallentaa
            json_store.putOpt(name, jar);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * lisätään laitteen omaan muistiin uusi tieto
     * @param context activityn konteksti
     */
    public void save(Context context){
        File internal_filepath = context.getFilesDir();
        File fileref = new File(internal_filepath, FILE_ID);

        try{
            OutputStream writer = new FileOutputStream(fileref);
            writer.write(json_store.toString().getBytes());

        }catch(IOException ioe){//jos virhe
            Log.w("InternalStorage", "Error writing " + fileref, ioe);

        }

    }
}
