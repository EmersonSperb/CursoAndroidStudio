package com.emerson.cursoandroid.assistencia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.emerson.cursoandroid.assistencia.R;

public class LoginActivity extends AppCompatActivity {
    EditText editSigla,editSenha;
    Button buttonSair,buttonEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editSenha = findViewById(R.id.editTextSenha);
        editSigla = findViewById(R.id.editTextSigla);
        buttonEntrar = findViewById(R.id.buttonEntrar);
        buttonSair = findViewById(R.id.buttonSair);


        buttonSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        buttonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               abreTelaPrincipal();
            }
        });

    }

    public void  abreTelaPrincipal(){
        Intent principal = new Intent(this, MainActivity.class);
        startActivity(principal);
    }
}
