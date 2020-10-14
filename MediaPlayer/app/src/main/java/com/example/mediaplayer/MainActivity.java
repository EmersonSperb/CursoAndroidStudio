package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

   private SeekBar seekBarVolume;
   private AudioManager audioManager;
   private Button buttonPlay,buttonPause,buttonStop;
   private MediaPlayer mediaPlayer;
   ProgressBar progressBarDuracao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        buttonPlay = findViewById(R.id.buttonPlay);
        buttonPause = findViewById(R.id.buttonPause);
        buttonStop = findViewById(R.id.buttonStop);
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.teste);
        inicializarSeekBar();
    }

    private void inicializarSeekBar(){
        seekBarVolume = findViewById(R.id.seekBarVolume);
        //Configura o audio manager
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int volumeMaximo = audioManager.getStreamMaxVolume(audioManager.STREAM_MUSIC);
        final int volumeAtual = audioManager.getStreamVolume(audioManager.STREAM_MUSIC);

        //Configura a seekBar - Valor máximo
        seekBarVolume.setMax(volumeMaximo);
        //Configura progresso atual da seekBar
        seekBarVolume.setProgress(volumeAtual);

        seekBarVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int i = seekBarVolume.getProgress();
               //audioManager.setStreamVolume(audioManager.STREAM_MUSIC,i,audioManager.FLAG_SHOW_UI);
                audioManager.setStreamVolume(audioManager.STREAM_MUSIC,i,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "Volume: " + seekBarVolume.getProgress(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "Volume: " + seekBarVolume.getProgress(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void executarSom(View view){
       if (mediaPlayer != null){
           mediaPlayer.start();
       }
    }

    public void pausarSom(View view){
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }

    public void pararSom(View view){
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.teste);
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        if (mediaPlayer != null){
            mediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mediaPlayer != null && mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }

    }
}
