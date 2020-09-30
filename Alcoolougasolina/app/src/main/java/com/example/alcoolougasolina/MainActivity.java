package com.example.alcoolougasolina;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calcular(View view) {
        double valorAlcool,valorGasolina,valorResultado;
        String melhorCombustivel;

        TextInputEditText precoAlcool = findViewById(R.id.textPrecoAlcool);
        TextInputEditText precoGasolina = findViewById(R.id.textPrecoGasolina);

    }
}
