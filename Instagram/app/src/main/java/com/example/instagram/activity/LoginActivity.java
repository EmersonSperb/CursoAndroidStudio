package com.example.instagram.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.instagram.R;
import com.example.instagram.config.ConfiguracaoFirebase;
import com.example.instagram.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText campoEmail;
    private EditText campoSenha;
    private TextView textCadastrar;
    private Button buttonLogin;
    private FirebaseAuth autenticacao;
    private Usuario usuario;
    private ProgressBar progressBarLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        verificarUsuarioLogado();
        inicializarComponentes();
        progressBarLogin.setVisibility(View.GONE);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBarLogin.setVisibility(View.VISIBLE);
                String textoEmail = campoEmail.getText().toString();
                String textoSenha = campoSenha.getText().toString();

                if (!textoEmail.isEmpty()) {
                    if (!textoSenha.isEmpty()) {
                        usuario = new Usuario();
                        usuario.setEmail(textoEmail);
                        usuario.setSenha(textoSenha);
                        validarLogin();


                    } else {
                        Toast.makeText(LoginActivity.this,
                                "Informe uma senha",
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this,
                            "Informe um e-mail",
                            Toast.LENGTH_LONG).show();
                }
            }

        });

    }

    public void inicializarComponentes(){
        campoEmail = findViewById(R.id.editLoginEmail);
        campoSenha = findViewById(R.id.editLoginSenha);
        textCadastrar  = findViewById(R.id.textCadastrar);
        buttonLogin = findViewById(R.id.buttonLogin);
        progressBarLogin = findViewById(R.id.progressLogin);

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        campoEmail.requestFocus();
    }

    public void validarLogin() {
        //autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressBarLogin.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this,
                                    "Login efetuado com sucesso",
                                    Toast.LENGTH_SHORT).
                                    show();

                            abrirTelaPrincipal();

                        } else {
                            progressBarLogin.setVisibility(View.GONE);

                            String excecao = "";
                            try {
                                throw task.getException();
                            } catch (
                                    FirebaseAuthInvalidUserException e) {
                                excecao = "Usuário desativado ou inexistente";
                            } catch (
                                    FirebaseAuthInvalidCredentialsException e) {
                                excecao = "E-mail e senha não correspondem a um usuario cadastrado.";
                            } catch (Exception e) {
                                excecao = "Erro ao logar usuário " + e.getMessage();
                                e.printStackTrace();
                            }


                            Toast.makeText(LoginActivity.this,
                                    excecao,
                                    Toast.LENGTH_SHORT).
                                    show();
                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //autenticacao.signOut();
        FirebaseUser usuarioAtual = autenticacao.getCurrentUser();
        if (usuarioAtual != null){
            abrirTelaPrincipal();
        }
    }

    public void verificarUsuarioLogado(){
      autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
      if (autenticacao.getCurrentUser() != null){
          startActivity(new Intent(this, MainActivity.class));
          finish();
        }
    }

    public void abrirTelaPrincipal() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }


    public void abrirTelaCadastro(View view) {
        startActivity(new Intent(LoginActivity.this, CadastroActivity.class));
        finish();
    }

    ;
    }

