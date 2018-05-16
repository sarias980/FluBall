package com.example.sergi.fluball;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

    public JuegosMainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_juegos_main, container, false);

        ListView list = (ListView) view.findViewById(R.id.lvJuegos);

        items = new ArrayList<>();
        String[] data = {
                "Scrap",
                "Patata Caliente"
        };

        items = new ArrayList<>(Arrays.asList(data));
        adapter = new ArrayAdapter<>(
                getContext(),
                R.layout.row_games_info,
                R.id.tvJuego,
                items
        );
        list.setAdapter(adapter);


        //adapter=new ArrayAdapter<String>(this,android.R.layout.,items);


        return view;
    }
}
