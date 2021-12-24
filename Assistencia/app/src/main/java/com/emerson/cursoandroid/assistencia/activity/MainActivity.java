package com.emerson.cursoandroid.assistencia.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.emerson.cursoandroid.assistencia.R;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements Serializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Configura toolbar
        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("Inicio");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_close_24);
        getSupportActionBar().setElevation(4);
    }

    @Override
    public boolean onSupportNavigateUp() {

        finish();
        return false;

    }

    public void abreTelaOrdens(View view){
        Intent ordens = new Intent(this,OrdensActivity.class);
        startActivity(ordens);

    }

    public void abreTelaClientes(View view){
        Intent clientes = new Intent(this,ClientesActivity.class);
        startActivity(clientes);

    }
}