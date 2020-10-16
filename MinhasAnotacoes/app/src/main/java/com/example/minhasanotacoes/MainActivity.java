package com.example.minhasanotacoes;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText editAnotacao;
    private String textoRecuperado;
    private AnotacaoPreferencias preferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editAnotacao = findViewById(R.id.editAnotacao);
        preferencias = new AnotacaoPreferencias(this);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            textoRecuperado = editAnotacao.getText().toString();

                if (textoRecuperado.equals("")){
                    Snackbar.make(view, "Digite sua anotação", Snackbar.LENGTH_LONG).show();
                }else{
                    preferencias.salvarAnotacao(textoRecuperado);
                    Snackbar.make(view, "Anotação incluída", Snackbar.LENGTH_LONG).show();

                }

            }
        });

        //Recuperar anotacao
        //o ! na frente equivale ao not
        String anotacao = preferencias.recuperarAnotacao();
        if (!anotacao.equals("")){
            editAnotacao.setText(anotacao);
        }
    }

}