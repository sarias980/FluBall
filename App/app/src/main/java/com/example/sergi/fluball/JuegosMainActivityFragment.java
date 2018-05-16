package com.example.sergi.fluball;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A placeholder fragment containing a simple view.
 */
public class JuegosMainActivityFragment extends Fragment {


    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;
    private BallApi api = new BallApi();

    public JuegosMainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_juegos_main, container, false);

        ListView list = (ListView) view.findViewById(R.id.lvJuegos);

        items = new ArrayList<>();
        String[] data = {
                "led",
                "botton"
        };

        items = new ArrayList<>(Arrays.asList(data));
        adapter = new ArrayAdapter<>(
                getContext(),
                R.layout.row_games_info,
                R.id.tvJuego,
                items
        );
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("Sarias", adapterView.getItemAtPosition(i).toString());
                api.getPlay(adapterView.getItemAtPosition(i).toString());
            }
        });

        //adapter=new ArrayAdapter<String>(this,android.R.layout.,items);

        return view;
    }
}
