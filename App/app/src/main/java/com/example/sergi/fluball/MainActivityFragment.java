package com.example.sergi.fluball;

import android.app.DownloadManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

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
        ImageView imagen = (ImageView) view.findViewById(R.id.imageUser);

        final FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            updateNameUser(name,auth.getCurrentUser().getDisplayName());
            Log.d("Imagen", auth.getCurrentUser().getPhotoUrl().toString());
            //new DownLoadImageTask(imagen).execute(auth.getCurrentUser().getPhotoUrl().toString());
            Glide.with(this).load(auth.getCurrentUser().getPhotoUrl().toString())
                    .apply(bitmapTransform(new CropCircleTransformation()))
                    .into(imagen);

            FirebaseFirestore db = FirebaseFirestore.getInstance();

            db.collection("msg_ayuda")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot document : task.getResult()) {
                                    Log.d("Sarias", document.getId() + " => " + document.getData());
                                }
                            } else {
                                Log.w("Sarias", "Error getting documents.", task.getException());
                            }
                        }
                    });

        } else {
            doLogin();
        }

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btnJuegos:
                        Log.d("BTN", "Juego");
                        intent = new Intent(getContext(), JuegosMainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.btnDispositivos:
                        Log.d("BTN", "Dispo");
                        intent = new Intent(getContext(), DispositivosMainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.btnOpciones:
                        Log.d("BTN", "Opc");
                        intent = new Intent(getContext(), ConfigMainActivity.class);
                        //intent.putExtra(auth, "FirebaseAuth");
                        startActivity(intent);
                        break;
                    case R.id.btnHelp:
                        Log.d("BTN", "Help");
                        intent = new Intent(getContext(), AyudaMainActivity.class);
                        startActivity(intent);
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

    private void doLogin() {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Arrays.asList(
                                new AuthUI.IdpConfig.EmailBuilder().build(),
                                new AuthUI.IdpConfig.PhoneBuilder().build(),
                                new AuthUI.IdpConfig.GoogleBuilder().build())).build(),
                RC_SIGN_IN);
    }

    private void updateNameUser(TextView name, String user) {
        name.setText(user);
    }

    private class DownLoadImageTask extends AsyncTask<String,Void,Bitmap> {
        ImageView imageView;

        public DownLoadImageTask(ImageView imageView){
            this.imageView = imageView;
        }

        /*
            doInBackground(Params... params)
                Override this method to perform a computation on a background thread.
         */
        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){ // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }

        /*
            onPostExecute(Result result)
                Runs on the UI thread after doInBackground(Params...).
         */
        protected void onPostExecute(Bitmap result){
            imageView.setImageBitmap(result);
        }
    }
}
