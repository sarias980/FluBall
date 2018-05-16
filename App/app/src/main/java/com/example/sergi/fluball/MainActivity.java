package com.example.sergi.fluball;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.nearby.messages.MessageListener;

public class MainActivity extends AppCompatActivity {
    //private MessageListener mMessageListener;
    //private Message mMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*mMessageListener = new MessageListener() {
            @Override
            public void onFound(Message message) {
                Log.d("TAG", "Found message: " + new String(message.getContent()));
            }

            @Override
            public void onLost(Message message) {
                Log.d("TAG", "Lost sight of message: " + new String(message.getContent()));
            }
        };*/

        //mMessage = new Message("Hello World".getBytes());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        //Nearby.getMessagesClient(this).publish(mMessage);
        //Nearby.getMessagesClient(this).subscribe(mMessageListener);
    }

    @Override
    public void onStop() {
        //Nearby.getMessagesClient(this).unpublish(mMessage);
        //Nearby.getMessagesClient(this).unsubscribe(mMessageListener);

        super.onStop();
    }
}
