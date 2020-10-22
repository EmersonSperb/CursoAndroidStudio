package com.example.firebaseapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.firebaseapp.R;
import com.firebase.ui.storage.images.FirebaseImageLoader;
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
    private Button buttonDownload;
    private ImageView imageFoto;
    private ImageView imageDownload;
    private EditText editPasta;
    private EditText editArquivo;
    private String nomePasta;
    private String nomeArquivo;
    private Intent data;
    private StorageReference imagemRef;
    private StorageReference storageReference;
    private StorageReference imagens;

    /*private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth usuario = FirebaseAuth.getInstance();*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonUpload = findViewById(R.id.buttonUpload);
        buttonDeletar = findViewById(R.id.buttonDeletar);
        buttonDownload = findViewById(R.id.buttonDownload);
        imageFoto = findViewById(R.id.imageFoto);
        imageDownload = findViewById(R.id.imageDownload);
        editPasta = findViewById(R.id.editPasta);
        editArquivo = findViewById(R.id.editArquivo);

        //Deleta as imagens
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
                storageReference = FirebaseStorage.getInstance().getReference();


                if (editPasta.getText().toString().equals("") ){
                    imagens = storageReference.child("imagens");//.child("foto-perfil");
                    nomeArquivo = editArquivo.getText().toString();
                    if (!nomeArquivo.isEmpty()){
                        imagemRef = imagens.child(nomeArquivo);
                    } else{
                        Toast.makeText(MainActivity.this,
                        "Informe o nome do arquivo.",
                                Toast.LENGTH_LONG).show();
                    }
                }else{
                    nomePasta = editPasta.getText().toString();
                    imagens = storageReference.child("imagens").child(nomePasta);
                    nomeArquivo = editArquivo.getText().toString();
                    if (!nomeArquivo.isEmpty()){
                        imagemRef = imagens.child(nomeArquivo);
                    } else{
                        Toast.makeText(MainActivity.this,
                                "Informe o nome do arquivo.",
                                Toast.LENGTH_LONG).show();
                    }
                     imagemRef = imagens.child(nomeArquivo);
                }

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
        //Envia as imagens
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
                storageReference = FirebaseStorage.getInstance().getReference();
                nomePasta = editPasta.getText().toString();
                if (!nomePasta.isEmpty()){
                    imagens = storageReference.child("imagens").child(nomePasta);
                    if (editArquivo.getText().toString().equals("")){
                        nomeArquivo = UUID.randomUUID().toString();
                    }else{
                        nomeArquivo = editArquivo.getText().toString();
                    }

                }
                else{
                    imagens = storageReference.child("imagens");//.child("foto-perfil");
                    if (editArquivo.getText().toString().equals("")){
                        nomeArquivo = UUID.randomUUID().toString();
                    }else{
                        nomeArquivo = editArquivo.getText().toString();
                    }
                }
                //StorageReference imagens = storageReference.child("imagens").child("foto-perfil");

                //Nome da imagem

                imagemRef = imagens.child(nomeArquivo);
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

        buttonDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Configura para salvar me memória
                imageDownload.setDrawingCacheEnabled(true);
                imageDownload.buildDrawingCache();
                //Recupera o Bitmap
                Bitmap bitmap = imageDownload.getDrawingCache();
                //Compime o Bitmap para png / jpeg
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 85, baos);
                //Converte o baos para pixel brutos para uma matriz de bytes
                //dados da imagem
                byte[] dadosImagem = baos.toByteArray();
                //Define nós para o storage
                storageReference = FirebaseStorage.getInstance().getReference();
                nomePasta = editPasta.getText().toString();

                if (!nomePasta.isEmpty()){
                    imagens = storageReference.child("imagens").child(nomePasta);
                }
                else{
                    imagens = storageReference.child("imagens");//.child("foto-perfil");
                }
                //StorageReference imagens = storageReference.child("imagens").child("foto-perfil");

                //Nome da imagem
                nomeArquivo = editArquivo.getText().toString();
                if (nomeArquivo.isEmpty()){
                   Toast.makeText(getApplicationContext(),
                           "Informe o nome do arquivo",
                           Toast.LENGTH_LONG).show();
                }else{
                    imagemRef = imagens.child(nomeArquivo);
                    Glide.with(MainActivity.this).
                            using(new FirebaseImageLoader()).
                            load(imagemRef).into(imageDownload);

                }

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
