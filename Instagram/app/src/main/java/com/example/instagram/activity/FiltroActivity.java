package com.example.instagram.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagram.R;
import com.example.instagram.adapter.AdapterMiniaturas;
import com.example.instagram.helper.ConfiguracaoFirebase;
import com.example.instagram.helper.RecyclerItemClickListener;
import com.example.instagram.helper.UsuarioFirebase;
import com.example.instagram.model.Postagem;
import com.example.instagram.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.zomato.photofilters.FilterPack;
import com.zomato.photofilters.imageprocessors.Filter;
import com.zomato.photofilters.utils.ThumbnailItem;
import com.zomato.photofilters.utils.ThumbnailsManager;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FiltroActivity extends AppCompatActivity {

    static
    {
        System.loadLibrary("NativeImageProcessor");
    }

    private ImageView imagemEscolhida;
    private Bitmap imagem;
    private Bitmap imagemFiltro;
    private List<ThumbnailItem> listaFiltros;

    private TextInputEditText textDescricaofiltro;
    private String idUsuarioLogado;
    private Usuario usuarioLogado;
    private ProgressBar progressBar;
    private boolean estaCarregado;

    private RecyclerView recyclerFiltros;
    private AdapterMiniaturas adapterMiniaturas;

    private DatabaseReference usuariosRef;
    private DatabaseReference usuarioLogadoRef;
    private DatabaseReference firebaseRef;
    private DataSnapshot seguidoresSnapshot;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtro);
        //configurações iniciais
        listaFiltros = new ArrayList<>();
        firebaseRef = ConfiguracaoFirebase.getFirebase();
        idUsuarioLogado = UsuarioFirebase.getIdentificadorUsuario();
        usuariosRef = ConfiguracaoFirebase.getFirebase().child("usuarios");
        textDescricaofiltro = findViewById(R.id.textDescricaoFiltro);

        //Recuperar dados da Postagem
        recuperarDadosPostagem();

        //Busca imagem escolhida
        imagemEscolhida = findViewById(R.id.imageFotoEscolhida);
        recyclerFiltros = findViewById(R.id.recyclerFiltros);

        //Configura Tootlbar
        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("Filtros");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_fechar_black);
        //getSupportActionBar().setDisplayShowTitleEnabled(true); //coloca o texto
        //Recupera a imagem escolhida pelo usuário
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            byte [] dadosImagem = bundle.getByteArray("fotoEscolhida");
            //origem dos dados da imagem, início de onde buscar os dados da imagem, tamanho máximo
            imagem = BitmapFactory.decodeByteArray(dadosImagem,0,dadosImagem.length);
            imagemEscolhida.setImageBitmap(imagem);
            //evita que dê erro pela imagens está nula
            imagemFiltro = imagem.copy(imagem.getConfig(), true);
            /*
            imagemFiltro = imagem.copy(imagem.getConfig(),true);
            Filter filter = FilterPack.getNightWhisperFilter(getApplicationContext());
            imagemEscolhida.setImageBitmap(filter.processFilter(imagemFiltro));
            */
            //Configura RecyclerView de filtros

            //Configura recyclerview de filtros
            adapterMiniaturas = new AdapterMiniaturas(listaFiltros,getApplicationContext());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
            recyclerFiltros.setLayoutManager(layoutManager);
            recyclerFiltros.setAdapter(adapterMiniaturas);

            recyclerFiltros.addOnItemTouchListener(
                    new RecyclerItemClickListener(
                            getApplicationContext(),
                            recyclerFiltros,
                            new RecyclerItemClickListener.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    ThumbnailItem item = listaFiltros.get(position);
                                    imagemFiltro = imagem.copy(imagem.getConfig(), true);
                                    Filter filtro = item.filter;
                                    imagemEscolhida.setImageBitmap(filtro.processFilter(imagemFiltro));
                                }

                                @Override
                                public void onLongItemClick(View view, int position) {

                                }

                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                }
                            }
                            )
            );
            //Recupera filtros
            recuperarFiltros();

        }

    }

    private void abrirDialogCarregamento(String titulo){

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle( titulo );
        alert.setCancelable(false);
        alert.setView(R.layout.carregamento);

        dialog = alert.create();
        dialog.show();

    }

    private void recuperarDadosPostagem(){
        abrirDialogCarregamento("Carregando dados, aguarde.");
        usuarioLogadoRef = usuariosRef.child( idUsuarioLogado );
        usuarioLogadoRef.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        //Recupera dados de usuário logado
                        usuarioLogado = dataSnapshot.getValue( Usuario.class );

                        //Recuperar seguidores
                        DatabaseReference seguidoresRef = firebaseRef
                                .child("seguidores")
                                .child(idUsuarioLogado);

                        seguidoresRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                seguidoresSnapshot = snapshot;

                                dialog.cancel();

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
        );

    }

    private void  recuperarFiltros(){
       //limpa lista
        ThumbnailsManager.clearThumbs();
        listaFiltros.clear();

        //Configurar filtro normal
        ThumbnailItem item = new ThumbnailItem();
        item.image = imagem;
        item.filterName = "Normal";
        ThumbnailsManager.addThumb(item);
        //Lista todos os filtros
        List<Filter> filtros = FilterPack.getFilterPack(getApplicationContext());
        for (Filter filtro: filtros){

            ThumbnailItem itemFiltro = new ThumbnailItem();
            itemFiltro.image = imagem;
            itemFiltro.filter = filtro;
            itemFiltro.filterName = filtro.getName();

            ThumbnailsManager.addThumb(itemFiltro);
        }

        listaFiltros.addAll(ThumbnailsManager.processThumbs(getApplicationContext()));
        adapterMiniaturas.notifyDataSetChanged();

    }

    private void publicarPostagem(){

        abrirDialogCarregamento("Salvando postagem");
        final Postagem postagem = new Postagem();
        postagem.setIdUsuario(idUsuarioLogado);
        postagem.setDescricao(textDescricaofiltro.getText().toString());

        //Recuperar dados da imagem do firebase
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        imagemFiltro.compress(Bitmap.CompressFormat.JPEG, 70, baos);
        byte[] dadosImagem = baos.toByteArray();

        //Salvar a imagem no firebase
        StorageReference storageRef = ConfiguracaoFirebase.getFirebaseStorage();
        StorageReference imagemRef = storageRef
                .child("imagens")
                .child("postagens")
                .child(postagem.getId() + ".jpeg");

        UploadTask uploadTask = imagemRef.putBytes(dadosImagem);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(FiltroActivity.this,
                        "Erro ao salvar a imagem. Tente novamente.",
                        Toast.LENGTH_SHORT).
                        show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(FiltroActivity.this,
                        "Sucesso ao salvar postagem",
                        Toast.LENGTH_SHORT).
                        show();

                //taskSnapsohot.getDownloadUrl; Versões antigas Firebase
                imagemRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {

                        Uri url = task.getResult();
                        postagem.setCaminhoFoto(url.toString());

                        //Atualizar qtde de postagens
                        int qtdPostagem = usuarioLogado.getPostagens() + 1;
                        usuarioLogado.setPostagens( qtdPostagem );
                        usuarioLogado.atualizarQtdPostagem();
                        //Salvar postagem
                        if( postagem.salvar(seguidoresSnapshot) ){
                            Toast.makeText(FiltroActivity.this,
                                    "Sucesso ao salvar postagem!",
                                    Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                            finish();
                        }

                    }
                });

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_filtro,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.ic_salvar_postagem :
                publicarPostagem();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return false;
    }
}