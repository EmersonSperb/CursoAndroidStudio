package com.example.youtube.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtube.R;
import com.example.youtube.adapter.AdapterVideo;
import com.example.youtube.api.YoutubeService;
import com.example.youtube.helper.RetrofitConfig;
import com.example.youtube.helper.YoutubeConfig;
import com.example.youtube.listener.RecyclerItemClickListener;
import com.example.youtube.model.Item;
import com.example.youtube.model.Resultado;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    //Widgets
    private RecyclerView recyclerVideos;

    //private List<Video> videos = new ArrayList<>();
    private List<Item> videos = new ArrayList<>();
    private AdapterVideo adapterVideo;
    private MaterialSearchView searchView;
    //Retrofit
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializar componentes
        recyclerVideos = findViewById(R.id.recyclerVideos);
        searchView = findViewById(R.id.searchView);

        //Configurações iniciais
        retrofit = RetrofitConfig.getRetrofit();

        //Configura toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Youtube");
        setSupportActionBar( toolbar );


        recuperarVideos();

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {

            }
        });

    }

    private void recuperarVideos(){

        /*Video video1 = new Video();
        video1.setTitulo("Vídeo 1 muito interessante!");
        videos.add( video1 );

        Video video2 = new Video();
        video2.setTitulo("Vídeo 2 muito interessante!");
        videos.add( video2 );*/


        YoutubeService youtubeService = retrofit.create( YoutubeService.class );

        youtubeService.recuperarVideos(
                "snippet", "date", "20",
                YoutubeConfig.CHAVE_YOUTUBE_API, YoutubeConfig.CANAL_ID
        ).enqueue(new Callback<Resultado>() {
            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {
                Log.d("resultado", "resultado: " + response.toString() );
            }

            @Override
            public void onFailure(Call<Resultado> call, Throwable t) {

            }
        });

    }

    public void configurarRecyclerView(){
        adapterVideo = new AdapterVideo(videos, this);
        recyclerVideos.setHasFixedSize( true );
        recyclerVideos.setLayoutManager(new LinearLayoutManager(this));
        recyclerVideos.setAdapter( adapterVideo );

        //Configura evento de clique
        recyclerVideos.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        this,
                        recyclerVideos,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                Item video = videos.get(position);
                                String idVideo = video.id.videoId;

                                Intent i = new Intent(MainActivity.this, PlayerActivity.class);
                                i.putExtra("idVideo", idVideo );
                                startActivity(i);

                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);

        MenuItem item = menu.findItem(R.id.menu_search);
        searchView.setMenuItem(item);

        return true;
    }
}
