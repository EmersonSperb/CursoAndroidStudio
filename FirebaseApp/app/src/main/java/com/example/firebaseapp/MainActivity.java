package com.example.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private Button buttonUpload;
    private Button buttonDeletar;
    private ImageView imageFoto;
    private EditText editPasta;

    /*private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth usuario = FirebaseAuth.getInstance();*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonUpload = findViewById(R.id.buttonUpload);
        buttonDeletar = findViewById(R.id.buttonDeletar);
        imageFoto = findViewById(R.id.imageFoto);
        editPasta = findViewById(R.id.editPasta);

        buttonDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Configura para salvar me memória
                imageFoto.setDrawingCacheEnabled(true);
                imageFoto.buildDrawingCache();
                //Recupera o Bitmap
                Bitmap bitmap = imageFoto.getDrawingCache();
                //Compime o Bitmap para png / jpeg
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 85, baos);
                //Converte o baos para pixel brutos para uma matriz de bytes
                //dados da imagem
                byte[] dadosImagem = baos.toByteArray();
                //Define nós para o storage
                StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                //StorageReference imagens = storageReference.child("imagens").child("foto-perfil");
                StorageReference imagens = storageReference.child("imagens");//.child("foto-perfil");
                StorageReference imagemRef = imagens.child("celular.jpeg");
                imagemRef.delete().addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,
                                "Exclusão da imagem falhou",
                                Toast.LENGTH_LONG)
                                .show();

                    }
                }).addOnSuccessListener(MainActivity.this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this,
                                "Imagem excluída com sucesso",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }
        });

        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Configura para salvar me memória
                imageFoto.setDrawingCacheEnabled(true);
                imageFoto.buildDrawingCache();
                //Recupera o Bitmap
                Bitmap bitmap = imageFoto.getDrawingCache();
                //Compime o Bitmap para png / jpeg
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 85, baos);
                //Converte o baos para pixel brutos para uma matriz de bytes
                //dados da imagem
                byte[] dadosImagem = baos.toByteArray();
                //Define nós para o storage
                StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                String nomePasta = editPasta.getText().toString();
                if (!nomePasta.isEmpty()){
                    StorageReference imagens = storageReference.child("imagens").child(nomePasta);
                };
                //StorageReference imagens = storageReference.child("imagens").child("foto-perfil");
                StorageReference imagens = storageReference.child("imagens");//.child("foto-perfil");
                //Nome da imagem
                String nomeArquivo = UUID.randomUUID().toString();
                StorageReference imagemRef = imagens.child(nomeArquivo + ".jpeg");

                //Retorna objeto que irá controlar o upload
                UploadTask uploadTask = imagemRef.putBytes(dadosImagem);
                //Tratando erros
                uploadTask.addOnFailureListener(MainActivity.this,
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this,
                                        "Upload da imagem falhou",
                                        Toast.LENGTH_LONG)
                                        .show();
                            }
                        });
                uploadTask.addOnSuccessListener(MainActivity.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                       //Uri url = taskSnapshot.DownloadUrl();
                        imagemRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                              Uri url = task.getResult();
                                Toast.makeText(MainActivity.this,
                                        "Sucesso ao fazer upload da imagem " + url.toString(),
                                        Toast.LENGTH_LONG)
                                        .show();
                            }
                        });
                    }
                });

            }
        });

                //DatabaseReference usuarios = referencia.child("usuarios");
                //Busca usuário específico
                //Query usuarioPesquisa = usuarios.orderByChild("nome").equalTo("Oscar");

                //Limita quantidade de resultados
                //Query usuarioPesquisa = usuarios.orderByKey().limitToFirst(4);
                //Query usuarioPesquisa = usuarios.orderByKey().limitToLast(4);

                //A partir de
                //Query usuarioPesquisa = usuarios.orderByChild("idade").startAt(34);

                //Até
                //Query usuarioPesquisa = usuarios.orderByChild("idade").endAt(28);

                //Entre dois valores
                //Query usuarioPesquisa = usuarios.orderByChild("idade").startAt(25).endAt(37);

                //Entre dois textos
        /*Query usuarioPesquisa = usuarios.orderByChild("nome").startAt("O").endAt("P" + "\uf8ff");//\uf8ff aumenta o alcance da busca. Se não colocar
        //DatabaseReference usuariosPesquisa = usuarios.child("-MKBEDhcbn-ywGNjDUzF");
        usuarioPesquisa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Usuario dadosUsuario = snapshot.getValue(Usuario.class);

                Log.i("Nome",dadosUsuario.getNome() + " " +dadosUsuario.getSobrenome() + " " +dadosUsuario.getIdade());

                Log.i("Dados Usuário",snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
                //Identificador único para registro
        /*Usuario usuario = new Usuario();
        usuario.setNome("Adolfo");
        usuario.setSobrenome("Dias");
        usuario.setIdade(37);
        //cria identificador único para o registro
        usuarios.push().setValue(usuario);*/

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
