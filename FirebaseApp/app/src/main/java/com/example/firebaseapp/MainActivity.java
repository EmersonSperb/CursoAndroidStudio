package com.example.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth usuario = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Deslogar usuário
        //usuario.signOut();

        //Logar Usuário
        /*usuario.signInWithEmailAndPassword("emerson@agilsoft.com.br",
                "agilsoft123").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.i("SignIn","Usuário logado com sucesso!");
                }else{
                    Log.i("SignIn","Erro ao logar usuário.");

                }
            }
        });*/

        //Verifica se usuário está logado
        /*if (usuario.getCurrentUser() != null){
            Log.i("CurrenUser","Usuário logado!");
        }else{
            Log.i("CurrentUser","Usuário não logado!");
        }*/

        //Cadastro de Usuário
        /*usuario.createUserWithEmailAndPassword(
                "emersonsperb1983@gmail.com", "agilsoft123")
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            Log.i("CreateUser","Usuário cadastrado com sucesso!");
                        }else{
                            Log.i("CreateUser","Erro ao cadastrar usuário.");

                        }
                    }
                });


        //referencia.child("pontos").setValue("200");
        //referencia.child("usuarios2").child("001").child("nome").setValue("Lorpa");
        /*
        DatabaseReference usuarios = referencia.child("usuarios");
        DatabaseReference produtos = referencia.child("produtos");
        usuarios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("FIREBASE: ",snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Usuario usuario = new Usuario();
        usuario.setNome("Jalim");
        usuario.setSobrenome("Rabei");
        usuario.setIdade(25);
        usuarios.child("002").setValue(usuario);*/


        /*Produto produto = new Produto();
        produto.setNome("Moto Z2 Play");
        produto.setMarca("Motorola");
        produto.setPreco(1400.00);
        produtos.child("002").setValue(produto);*/



    }
}