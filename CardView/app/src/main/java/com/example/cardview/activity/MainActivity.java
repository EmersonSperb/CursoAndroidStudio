package com.example.cardview.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.cardview.R;
import com.example.cardview.adapter.PostagemAdapter;
import com.example.cardview.model.Postagem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerPostagem;
    private List<Postagem> postagens = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerPostagem = findViewById(R.id.recyclerView);
        //definir layout
        //Também podem ser usados nos recycler view
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayout.HORIZONTAL);

        //RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);

        recyclerPostagem.setLayoutManager(layoutManager);
        //definir adapter
        this.prepararPostagens();
        PostagemAdapter adapter = new PostagemAdapter( postagens );
        recyclerPostagem.setAdapter(adapter);
    }

    public void prepararPostagens(){
        Postagem p = new Postagem("Emerson Sperb","teste",R.drawable.imagem1);
        this.postagens.add(p);

        p = new Postagem("Emerson Sperb","outro teste",R.drawable.imagem2);
        this.postagens.add(p);

        p = new Postagem("Emerson Sperb","mais outro teste",R.drawable.imagem3);
        this.postagens.add(p);

        p = new Postagem("Emerson Sperb","quarto teste",R.drawable.imagem4);
        this.postagens.add(p);

    }
}
