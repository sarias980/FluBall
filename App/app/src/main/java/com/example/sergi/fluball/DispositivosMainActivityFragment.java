package com.example.sergi.fluball;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A placeholder fragment containing a simple view.
 */
public class DispositivosMainActivityFragment extends Fragment {

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;

    public DispositivosMainActivityFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dispositivos_main, container, false);

        ListView list = (ListView) view.findViewById(R.id.lvDisp);

        items = new ArrayList<>();
        String[] data = {
                "FluBall de Sarias",
                "FluBall de Lluc"
        };

        items = new ArrayList<>(Arrays.asList(data));
        adapter = new ArrayAdapter<>(
                getContext(),
                R.layout.row_ball_info,
                R.id.tvBallName,
                items
        );
        list.setAdapter(adapter);

        //activarHotSpot();

        return view;
    }

    private void activarHotSpot() {
        try {
            //Poner la configuracion a WifiHotspot
            ApManager.configApState("fluidra", "12345678", this.getContext()); // change Ap state :boolean
            //ApManager.setHotspotName(config.getName(), config.getPass(), this);
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
