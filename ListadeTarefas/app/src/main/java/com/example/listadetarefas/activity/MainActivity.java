package com.example.listadetarefas.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.listadetarefas.R;
import com.example.listadetarefas.adapter.TarefaAdaper;
import com.example.listadetarefas.model.Tarefa;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TarefaAdaper tarefaAdapter;
    private List<Tarefa> listaTarefas = new  ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);

        //Configurar Recycler View
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new
                DividerItemDecoration(this,
                LinearLayout.VERTICAL));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),LinearLayout.VERTICAL));
        List<Tarefa>;
        tarefaAdaper = new TarefaAdaper();
        recyclerView.setAdapter(tarefaAdapter);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdicionarTarefaActivity.class);
                startActivity(intent);
            }
        });
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

    public void carregarListaTarefas(){
        Tarefa tarefa1 = new Tarefa();
        tarefa1.setNomeTarefa("Levantar da cama");
        listaTarefas.add(tarefa1);

        Tarefa tarefa2 = new Tarefa();
        tarefa1.setNomeTarefa("Lavar o rosto");
        listaTarefas.add(tarefa2);

    }
}