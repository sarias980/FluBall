package com.example.sergi.fluball;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

/**
 * A placeholder fragment containing a simple view.
 */
public class ConfigMainActivityFragment extends Fragment {
    private static final int RC_SIGN_IN = 123;
    private TextView name;
    private TextView correo;
    private EditText password;

    public ConfigMainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_config_main, container, false);

        name = (TextView) view.findViewById(R.id.nombreUsuario);
        correo = (TextView) view.findViewById(R.id.txtCorreo);
        password = (EditText) view.findViewById(R.id.txtPassword);

        Button update = (Button) view.findViewById(R.id.btnUpdate);


        final FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            updateInfoUser(correo, auth.getCurrentUser().getEmail());
            updateInfoUser(name, auth.getCurrentUser().getDisplayName());
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
                        Log.d("BTN", "Update");
                        updatePasswordUser(String.valueOf(password.getText()), auth);
                        break;
                }
            }
        };

        update.setOnClickListener(listener);

        return view;
    }

    private void updateInfoUser(TextView name, String user) {
        name.setText(user);
    }

    private void updatePasswordUser(String password, FirebaseAuth auth) {
        auth.getCurrentUser().updatePassword(password);
    }
}
