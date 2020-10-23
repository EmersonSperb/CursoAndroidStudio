package com.example.organizze.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.service.autofill.FillEventHistory;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.organizze.R;
import com.google.firebase.auth.FirebaseAuth;

public class CadastroActivity extends AppCompatActivity {

    private EditText campoNome;
    private EditText campoEmail;
    private EditText campoSenha;
    private Button buttonCadastrar;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        campoNome = findViewById(R.id.editNome);
        campoEmail = findViewById(R.id.editEmail);
        campoSenha = findViewById(R.id.editSenha);
        buttonCadastrar = findViewById(R.id.buttonCadastar);

        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoNome = campoNome.getText().toString();
                String textoEmail = campoEmail.getText().toString();
                String textoSenha = campoSenha.getText().toString();

                if (!textoNome.isEmpty()) {
                    if (!textoEmail.isEmpty()) {
                        if (!textoSenha.isEmpty()) {

                            cadastrarUsuario();

                        } else {
                            Toast.makeText(CadastroActivity.this,
                                    "Informe uma senha",
                                    Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(CadastroActivity.this,
                                "Informe um e-mail",
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(CadastroActivity.this,
                            "Informe um nome",
                            Toast.LENGTH_LONG).show();
                }

            }

            ;

        });
    }

    public void cadastrarUsuario(){

        autenticacao = Fire
    }
}