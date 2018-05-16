package com.example.sergi.fluball;

import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.lang.reflect.InvocationTargetException;

public class DispositivosMainActivity extends AppCompatActivity {

    private ApManager apManager = new ApManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispositivos_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        activarHotSpot();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void activarHotSpot() {
        try {
            //Poner la configuracion a WifiHotspot
            apManager.configApState("fluidra", "12345678", this); // change Ap state :boolean
            apManager.setHotspotName("fluidra", "12345678", this);
            //Activar WifiHotspot

            Log.d("Wifi", "combiado");

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
