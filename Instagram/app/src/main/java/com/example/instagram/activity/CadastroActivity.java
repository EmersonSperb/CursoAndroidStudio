package com.example.instagram.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.instagram.R;
import com.example.instagram.config.ConfiguracaoFirebase;
import com.example.instagram.helper.Base64Custom;
import com.example.instagram.helper.UsuarioFirebase;
import com.example.instagram.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastroActivity extends AppCompatActivity {
    private EditText campoEmail;
    private EditText campoSenha;
    private EditText campoNome;
    private Button buttonCadastro;
    private Usuario usuario;
    private FirebaseAuth autenticacao;
    private ProgressBar progressBarCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        inicializarComponentes();

    }
    public void inicializarComponentes(){
        campoNome = findViewById(R.id.editCadastroNome);
        campoEmail = findViewById(R.id.editCadastroEmail);
        campoSenha = findViewById(R.id.editCadastroSenha);
        buttonCadastro = findViewById(R.id.buttonCadastrar);
        progressBarCadastro = findViewById(R.id.progressCadastro);
        campoNome.requestFocus();
    };

    public void validarCadastroUsuario(View view){
        String textoNome = campoNome.getText().toString();
        String textoEmail = campoEmail.getText().toString();
        String textoSenha = campoSenha.getText().toString();

        if (!textoNome.isEmpty()) {
            if (!textoEmail.isEmpty()) {
                if (!textoSenha.isEmpty()) {
                    progressBarCadastro.setVisibility(View.VISIBLE);
                    usuario = new Usuario();
                    usuario.setNome(textoNome);
                    usuario.setEmail(textoEmail);
                    usuario.setSenha(textoSenha);
                    cadastrar(usuario);

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


    public void cadastrar(Usuario usuario){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()).addOnCompleteListener(this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            progressBarCadastro.setVisibility(View.GONE);
                            Toast.makeText(CadastroActivity.this,
                                    "Usuário cadastrado com sucesso",
                                    Toast.LENGTH_SHORT).
                                    show();
                            String idUsuario = Base64Custom.codificarBase64(usuario.getEmail());
                            usuario.setIdUsuario(idUsuario);
                            usuario.salvar();

                            UsuarioFirebase.atualizarNomeUsuario(usuario.getNome());

                            finish();

                            abrirTelaPrincipal();

                        }else{

                            progressBarCadastro.setVisibility(View.GONE);

                            String excecao = "";
                            try {
                                throw task.getException();
                            }catch(
                                    FirebaseAuthWeakPasswordException e){
                                excecao = "Digite uma senha mais forte";
                            }catch(
                                    FirebaseAuthInvalidCredentialsException e){
                                excecao = "Problemas no endereço de e-mail";
                            }catch(
                                    FirebaseAuthUserCollisionException e){
                                excecao = "Usuário já existente";
                            }catch (Exception e){
                                excecao = "Erro ao cadastrar usuário " + e.getMessage();
                                e.printStackTrace();
                            }


                            Toast.makeText(CadastroActivity.this,
                                    excecao,
                                    Toast.LENGTH_SHORT).
                                    show();
                        }
                    }
                });
    }

    public void abrirTelaPrincipal() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}