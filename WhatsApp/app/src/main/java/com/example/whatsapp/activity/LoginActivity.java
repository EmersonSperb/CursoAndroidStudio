package com.example.whatsapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.whatsapp.R;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText editEmail;
    private TextInputEditText editSenha;
    private Button buttonLogin;
    private TextView textCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        editEmail = findViewById(R.id.editTextEmail);
        editSenha = findViewById(R.id.editTextSenha);
        buttonLogin = findViewById(R.id.buttonLogin);
        textCadastrar = findViewById(R.id.textCadastrar);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }
    public void abrirTelaCadastro(View view){
        startActivity(new Intent(this,CadastroActivity.class));
        finish();
    };



}
