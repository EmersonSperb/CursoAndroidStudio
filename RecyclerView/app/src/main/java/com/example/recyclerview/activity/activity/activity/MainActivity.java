package com.example.recyclerview.activity.activity.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.recyclerview.R;
import com.example.recyclerview.activity.activity.adapter.Adapter;
import com.example.recyclerview.activity.activity.model.Filme;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Filme> listaFilmes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        //Criar Listagem
         this.criarFilmes();
        //Configurar adapter
        Adapter adapter = new Adapter(listaFilmes);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new
                DividerItemDecoration(this,
                LinearLayout.VERTICAL));
        //Configurar Recycler View
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void criarFilmes(){
       Filme filme = new Filme("A volta dos que não foram","Comédia","2000");
       this.listaFilmes.add( filme );

        filme = new Filme("Wayne's World","Comédia","1992");
        this.listaFilmes.add( filme );

        filme = new Filme("Wayne's World 2","Comédia","1993");
        this.listaFilmes.add( filme );

        filme = new Filme("Office Space","Comédia","1999");
        this.listaFilmes.add( filme );

        filme = new Filme("Simpsons","Comédia","2006");
        this.listaFilmes.add( filme );
    }
}
