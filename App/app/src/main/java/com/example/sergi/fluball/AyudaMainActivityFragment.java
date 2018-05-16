package com.example.sergi.fluball;

import android.app.DownloadManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.*;

/**
 * A placeholder fragment containing a simple view.
 */
public class AyudaMainActivityFragment extends Fragment {

    private TextView texto;
    private String mensaje;

    public AyudaMainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ayuda_main, container, false);

        texto = (TextView) view.findViewById(R.id.tvHelp);
        final FirebaseAuth auth = FirebaseAuth.getInstance();

        //FirebaseFirestore db = FirebaseFirestore.getInstance();

        mensaje = "Desde el equipo de desarollo de FluBall queremos ayudarte en todo lo que podamos, si tiene algun problema contacte con nosotros al siguente correo electronico: sat@fluidra.com";
        //mensaje = auth.getCurrentUser();
        texto.setText(mensaje);

        return view;
    }
}
