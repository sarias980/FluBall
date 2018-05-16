package com.example.sergi.fluball;

import android.net.Uri;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import android.os.NetworkOnMainThreadException;
import java.util.ArrayList;

/**
 * Created by sarias on 17/01/18.
 */

public class BallApi {
    private String BASE_URL ="http://192.168.43.9:8080";

    String getPlay(String name) {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("juego")
                .appendPath(name)
                .build();
        String url = builtUri.toString();

        try {
            Log.d("URL", url);
            String JsonResponse = HttpUtils.get(url);
            return "succes";
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NetworkOnMainThreadException e) {
            e.printStackTrace();
        }
        return null;
    }
}

