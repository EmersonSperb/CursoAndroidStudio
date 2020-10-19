package com.example.listadetarefas.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.listadetarefas.R;
import com.example.listadetarefas.adapter.TarefaAdapter;
import com.example.listadetarefas.helper.RecyclerItemClickListener;
import com.example.listadetarefas.model.Tarefa;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TarefaAdapter tarefaAdapter;
    private List<Tarefa> listaTarefas = new  ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);
        //eventos de clique
        recyclerView.addOnItemTouchListener( new RecyclerItemClickListener(
                getApplicationContext(),
                recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                       Log.i("clique","onItemClick");
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        Log.i("clique","onLongItemClick");
                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }

        ));

        //this.carregarListaTarefas();


        //Cria o adapter
        TarefaAdapter tarefaAdapter = new TarefaAdapter(listaTarefas);
        //Configurar Recycler View
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new
                DividerItemDecoration(this,
                LinearLayout.VERTICAL));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        //List<Tarefa>;

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

    public void carregarListaTarefas () {
        Tarefa tarefa1 = new Tarefa();
        tarefa1.setNomeTarefa("Levantar da cama");
        listaTarefas.add(tarefa1);

        Toast.makeText(
                getApplicationContext(),
                tarefa1.getNomeTarefa(),
                LENGTH_LONG).show();

        Tarefa tarefa2 = new Tarefa();
        tarefa2.setNomeTarefa("Lavar o rosto");
        listaTarefas.add(tarefa2);

        Toast.makeText(
                getApplicationContext(),
                tarefa2.getNomeTarefa(),
                LENGTH_LONG).show();

        Tarefa tarefa3 = new Tarefa();
        tarefa3.setNomeTarefa("Se vestir");
        listaTarefas.add(tarefa3);

        Toast.makeText(
                getApplicationContext(),
                tarefa3.getNomeTarefa(),
                LENGTH_LONG).show();

        Tarefa tarefa4 = new Tarefa();
        tarefa4.setNomeTarefa("Tomar café");
        listaTarefas.add(tarefa4);
        Toast.makeText(
                getApplicationContext(),
                tarefa4.getNomeTarefa(),
                LENGTH_LONG).show();
    };

    @Override
    protected void onStart() {
        carregarListaTarefas();

        Toast.makeText(
                getApplicationContext(),
                "Itens: " + listaTarefas.size(),
                LENGTH_LONG).show();
        super.onStart();

    }

}