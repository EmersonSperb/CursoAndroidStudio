package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

   private SeekBar seekBarVolume;
   private Button buttonPlay,buttonPause,buttonStop;
   private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBarVolume = findViewById(R.id.seekBarVolume);
        buttonPlay = findViewById(R.id.buttonPlay);
        buttonPause = findViewById(R.id.buttonPause);
        buttonStop = findViewById(R.id.buttonStop);
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.teste);

    }

    public void executarSom(View view){
       if (mediaPlayer != null){
           mediaPlayer.start();
       }
    }

    public void pausarSom(View view){

    }

    public void pararSom(View view){

    }
}
