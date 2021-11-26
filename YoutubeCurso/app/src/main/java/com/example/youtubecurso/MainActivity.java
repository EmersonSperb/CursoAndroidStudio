package com.example.youtubecurso;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MainActivity extends YouTubeBaseActivity
        implements YouTubePlayer.OnInitializedListener {

    private YouTubePlayerView youTubePlayerView;
    private static final String GOOGLE_API_KEY = "AIzaSyCAkLvtOGRmFPK2QdohCoPLZZgw-QcGoJM";
    private YouTubePlayer.PlaybackEventListener playbackEventListener;
    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
            @Override
            public void onLoading() {
                Toast.makeText(MainActivity.this,
                        "Video carregando",
                        Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onLoaded(String s) {
                Toast.makeText(MainActivity.this,
                        "Video carregado",
                        Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onAdStarted() {
                Toast.makeText(MainActivity.this,
                        "Propaganda iniciou",
                        Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onVideoStarted() {
                Toast.makeText(MainActivity.this,
                        "Video está começando",
                        Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onVideoEnded() {
                Toast.makeText(MainActivity.this,
                        "Video chegou ao final",
                        Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onError(YouTubePlayer.ErrorReason errorReason) {
                Toast.makeText(MainActivity.this,
                        "Erro ao recuperar eventos de carregamento!",
                        Toast.LENGTH_SHORT ).show();
            }
        };

        playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
            @Override
            public void onPlaying() {
                Toast.makeText(MainActivity.this,
                        "Video executando",
                        Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onPaused() {
                Toast.makeText(MainActivity.this,
                        "Video pausado",
                        Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onStopped() {
                Toast.makeText(MainActivity.this,
                        "Video parado",
                        Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onBuffering(boolean b) {
                Toast.makeText(MainActivity.this,
                        "Video pré-carregando",
                        Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onSeekTo(int i) {
                Toast.makeText(MainActivity.this,
                        "Movimentando SeekBar",
                        Toast.LENGTH_SHORT ).show();
            }
        };

        youTubePlayerView = findViewById(R.id.viewYoutubePlayer);
        youTubePlayerView.initialize(GOOGLE_API_KEY, this );


    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean foiRestaurado) {

        Toast.makeText(this,
                "Sucesso ao iniciar o Player!",
                Toast.LENGTH_SHORT).show();

        //youTubePlayer.loadVideo("qRXkEQOtQ98");
        Log.i("estado_player", "estado: " + foiRestaurado );
        youTubePlayer.setPlaybackEventListener( playbackEventListener );
        if ( !foiRestaurado ){
            //youTubePlayer.cueVideo("6dRCkyde7ys");
            youTubePlayer.cuePlaylist("PLWz5rJ2EKKc9ofd2f-_-xmUi07wIGZa1c");
        }



    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this,
                "Erro ao iniciar o Player!" + youTubeInitializationResult.toString(),
                Toast.LENGTH_SHORT).show();
    }
}
