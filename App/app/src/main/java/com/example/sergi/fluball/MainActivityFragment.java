package com.example.sergi.fluball;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static final int RC_SIGN_IN = 123;
    private Intent intent;
    public TextView name;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        name = (TextView) view.findViewById(R.id.nombreUsuario);
        Button juego = (Button) view.findViewById(R.id.btnJuegos);
        Button dispositivos = (Button) view.findViewById(R.id.btnDispositivos);
        Button opciones = (Button) view.findViewById(R.id.btnOpciones);
        Button help = (Button) view.findViewById(R.id.btnHelp);

        final FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            updateNameUser(name,auth.getCurrentUser().getEmail());
        } else {
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(Arrays.asList(
                                    new AuthUI.IdpConfig.EmailBuilder().build(),
                                    new AuthUI.IdpConfig.PhoneBuilder().build(),
                                    new AuthUI.IdpConfig.GoogleBuilder().build())).build(),
                    RC_SIGN_IN);
        }

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btnJuegos:
                        Log.d("BTN", "Juego");
                        //intent = new Intent(getContext(), JuegosMainActivity.class);
                        //startActivity(intent);
                        break;
                    case R.id.btnDispositivos:
                        Log.d("BTN", "Dispo");
                        //intent = new Intent(getContext(), DispositivosMainActivity.class);
                        //startActivity(intent);
                        break;
                    case R.id.btnOpciones:
                        Log.d("BTN", "Opc");
                        intent = new Intent(getContext(), ConfigMainActivity.class);
                        //intent.putExtra(auth, "FirebaseAuth");
                        startActivity(intent);
                        break;
                    case R.id.btnHelp:
                        Log.d("BTN", "Help");
                        //intent = new Intent(getContext(), HelpMainActivity.class);
                        //startActivity(intent);
                        break;
                }
            }
        };

        juego.setOnClickListener(listener);
        dispositivos.setOnClickListener(listener);
        opciones.setOnClickListener(listener);
        help.setOnClickListener(listener);


        return view;
    }

    private void updateNameUser(TextView name, String user) {
        name.setText(user);
    }
}
