package com.example.proyectouf2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.IOException;

public class RecorderActivity extends AppCompatActivity {

    TextView textView;
    MediaRecorder mediaRecorder;

    public static String fileName = "recorded.3gp";
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private boolean permissionToRecordAccepted = false;
    private String [] permissions = {Manifest.permission.RECORD_AUDIO};
    String file = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + fileName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorder);

        textView = findViewById(R.id.textView);

        ActivityCompat.requestPermissions(RecorderActivity.this, permissions,
                REQUEST_RECORD_AUDIO_PERMISSION);


    }

    public void onClick(View v) {

            if (v.getId() == R.id.btnRecord) {

                record();

            } else if (v.getId() == R.id.btnStop) {

                stopAudio();

            } else if (v.getId() == R.id.btnPlay) {

                play();

            }


    }

    @SuppressLint("SetTextI18n")
    private void play() {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(file);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        textView.setText("Playing Recorded Audio...");

    }

    @SuppressLint("SetTextI18n")
    private void stopAudio() {

        mediaRecorder.stop();
        mediaRecorder.release();

        textView.setText("Recording Stopped");
    }

    @SuppressLint("SetTextI18n")
    private void record() {

        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setOutputFile(file);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);



        try {
            mediaRecorder.prepare();

        } catch (IOException e) {
            e.printStackTrace();
            Log.e("RECORDING","No es pot iniciar la gravacio");
        }
        mediaRecorder.start();
        textView.setText("Audio Recording...");
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            String[] permissions,
            int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_RECORD_AUDIO_PERMISSION:
                permissionToRecordAccepted = grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (!permissionToRecordAccepted) {
            Toast.makeText(
                    this,
                    "Permission needed",
                    Toast.LENGTH_LONG
            ).show();
            RecorderActivity.this.finish();
        }
    }
}