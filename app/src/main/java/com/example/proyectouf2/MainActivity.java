package com.example.proyectouf2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }


    /** Called when the user taps the Send button */
    public void abrirMapa(View view) {
        Intent intent = new Intent(this, MapaActivity.class);

        startActivity(intent);
    }

    public void abrirRecorder(View view) {
        Intent intent = new Intent(this, RecorderActivity.class);

        startActivity(intent);
    }

}