package com.example.passandodadosactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SegundaActivity extends AppCompatActivity {
    private TextView textNome;
    private TextView textIdade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        textNome = findViewById(R.id.textNome);
        textIdade = findViewById(R.id.textIdade);
        //Recuperar dados enviados
        Bundle dados = getIntent().getExtras();
        String nome = dados.getString("nome");
        int idade = dados.getInt("idade");

        Usuario usuario = (Usuario) dados.getSerializable("objeto");

        //textNome.setText(nome);
        //textIdade.setText(String.valueOf(idade));
        textNome.setText(usuario.getUsuario());
        textIdade.setText(usuario.getEmail());

    }
}
