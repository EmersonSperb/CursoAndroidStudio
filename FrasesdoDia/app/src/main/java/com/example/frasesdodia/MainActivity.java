package com.example.frasesdodia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gerarNovaFrase(View view){

        String[] frases = {"Frase 0",
                           "Frase 1",
                           "Frase 2",
                           "Frase 3",
                           "Frase 4",
                           "Frase 5"}
        ;

        int indice = new Random().nextInt(frases.length);//(bound: 5)
        TextView texto = findViewById(R.id.textResultado);
        texto.setText( frases[indice] );


    }
}
