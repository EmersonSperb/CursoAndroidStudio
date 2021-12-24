package com.emerson.cursoandroid.assistencia.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.emerson.cursoandroid.assistencia.R;

import java.io.Serializable;

public class ClientesActivity extends AppCompatActivity implements Serializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_close_24);
        getSupportActionBar().setElevation(4);
    }

    @Override
    public boolean onSupportNavigateUp() {

        finish();
        return false;

    }
}