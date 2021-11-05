package com.example.instagram.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.instagram.R;
import com.example.instagram.adapter.AdapterGrid;
import com.example.instagram.helper.ConfiguracaoFirebase;
import com.example.instagram.helper.UsuarioFirebase;
import com.example.instagram.model.Postagem;
import com.example.instagram.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PerfilAmigoActivity extends AppCompatActivity {

    private Usuario usuarioSelecionado;
    private Usuario usuarioLogado;
    private Button buttonAcaoPerfil;
    private CircleImageView imagePerfil;
    private TextView textPublicacoes, textSeguidores, textSeguindo;
    private GridView gridViewPerfil;
    private AdapterGrid adapterGrid;

    private DatabaseReference firebaseRef;
    private DatabaseReference usuariosRef;
    private DatabaseReference usuarioAmigoRef;
    private DatabaseReference usuarioLogadoRef;
    private DatabaseReference seguidoresRef;
    private DatabaseReference postagensUsuarioRef;
    private ValueEventListener valueEventListenerPerfilAmigo;

    private String idUsuarioLogado;

    private List<Postagem> postagens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_amigo);

        firebaseRef = ConfiguracaoFirebase.getFirebase();
        usuariosRef = firebaseRef.child("usuarios");
        seguidoresRef = firebaseRef.child("seguidores");
        idUsuarioLogado = UsuarioFirebase.getDadosUsuarioLogado().getId();

        inicializarComponentes();

        //Configura toolbar
        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("Perfil");
        setSupportActionBar( toolbar );

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);

        //Recuperar usuario selecionado
        Bundle bundle = getIntent().getExtras();
        if( bundle != null ){
            usuarioSelecionado = (Usuario) bundle.getSerializable("usuarioSelecionado");

            postagensUsuarioRef = ConfiguracaoFirebase.getFirebase()
                    .child("postagens")
                    .child(usuarioSelecionado.getId());
            //Configura nome do usuário na toolbar
            getSupportActionBar().setTitle( usuarioSelecionado.getNome() );
            //Recuperar foto do usuário
            String caminhoFoto = usuarioSelecionado.getCaminhoFoto();
            if(caminhoFoto != null){
                Uri url = Uri.parse(caminhoFoto);
                Glide.with(PerfilAmigoActivity.this)
                        .load(url)
                        .into(imagePerfil);
            }

        }

        //Inicializar image loader
        inicializarImageLoader();

        carregarFotosPostagem();

        gridViewPerfil.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Postagem postagem = postagens.get( position );
                Intent i = new Intent(getApplicationContext(), VisualizarPostagemActivity.class );

                i.putExtra("postagem", postagem );
                i.putExtra("usuario", usuarioSelecionado );

                startActivity( i );
            }
        });

    }

    public void inicializarImageLoader(){
        // Create global configuration and initialize ImageLoader with this config
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(this)
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024)
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(100)
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
                .build();
        ImageLoader.getInstance().init( config );
    }

    public void carregarFotosPostagem(){

        postagens = new ArrayList<>();
        //Recupera as fotos postadas pelo usuario
        postagensUsuarioRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Configurar o tamanho do grid
                int tamanhoGrid = getResources().getDisplayMetrics().widthPixels;
                int tamanhoImagem = tamanhoGrid / 3;
                gridViewPerfil.setColumnWidth( tamanhoImagem );

                List<String> urlFotos = new ArrayList<>();
                for( DataSnapshot ds: dataSnapshot.getChildren() ){
                    Postagem postagem = ds.getValue( Postagem.class );
                    postagens.add(postagem);
                    urlFotos.add( postagem.getCaminhoFoto() );
                    Log.i("postagem", "url:" + postagem.getCaminhoFoto() );
                }

                /*int qtdPostagem = urlFotos.size();
                textPublicacoes.setText( String.valueOf(qtdPostagem) );*/

                //Configurar adapter
                adapterGrid = new AdapterGrid(getApplicationContext(), R.layout.grid_postagem, urlFotos );
                gridViewPerfil.setAdapter( adapterGrid );

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    private void recuperarDadosUsuarioLogado(){

        usuarioLogadoRef = usuariosRef.child(idUsuarioLogado);
        usuarioLogadoRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //recupera dados do usuario logado
                usuarioLogado = snapshot.getValue(Usuario.class);
                /*verifica se o usuário já está seguindo o amigo selecionando
                 * */
                verificaSegueUsuarioAmigo();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void verificaSegueUsuarioAmigo(){
       DatabaseReference seguidorRef = seguidoresRef.
               child(usuarioSelecionado.getId())
               .child(idUsuarioLogado);

       seguidorRef.addListenerForSingleValueEvent(
               new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                       if(snapshot.exists()){//já seguindo
                           Log.i("dadosUsuario","seguindo");
                           habilitarBotaoSeguir(true);
                       }else{//não está seguindo
                           Log.i("dadosUsuario","não está seguindo");
                           habilitarBotaoSeguir(false);
                       }
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {

                   }
               }
       );
    }

    private void salvarSeguidor(Usuario uLogado, Usuario uAmigo){
        /*seguidores
              idSeguindo
                 idLogado
                    dados seguindo
        * */


        HashMap<String, Object> dadosUsuarioLogado = new HashMap<>();
        dadosUsuarioLogado.put("nome",uLogado.getNome());
        dadosUsuarioLogado.put("caminhoFoto",uLogado.getCaminhoFoto());

        DatabaseReference seguidorRef = seguidoresRef
                .child(uAmigo.getId())
                .child(uLogado.getId());
                seguidorRef.setValue(dadosUsuarioLogado);

                buttonAcaoPerfil.setText("Seguindo");
                buttonAcaoPerfil.setOnClickListener(null);
                //Incrementar seguindo do usuário logado
                int seguindo = uLogado.getSeguindo() + 1;
                HashMap<String, Object> dadosSeguindo = new HashMap<>();
                dadosSeguindo.put("seguindo",seguindo);
                DatabaseReference usuarioSeguindo = usuariosRef.
                        child(uLogado.getId());
                usuarioSeguindo.updateChildren(dadosSeguindo);

                //Incrementar seguidores do amigo
                int seguidores = uAmigo.getSeguidores() + 1;
                HashMap<String, Object> dadosSeguidores = new HashMap<>();
                dadosSeguidores.put("seguidores",seguidores);
                DatabaseReference usuarioSeguidores = usuariosRef.
                child(uAmigo.getId());
                usuarioSeguidores.updateChildren(dadosSeguidores);

    }

    private void  habilitarBotaoSeguir(boolean segueUsuario){
       if (segueUsuario){
              buttonAcaoPerfil.setText("Seguindo");
       }else{
           buttonAcaoPerfil.setText("Seguir");

           //Adiciona evento para segui usuário
           buttonAcaoPerfil.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   salvarSeguidor(usuarioLogado,usuarioSelecionado);
                   //verificaSegueUsuarioAmigo();
               }
           });

       }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Recuperar dados do amigo selecionado
        recuperarDadosPerfilAmigo();

        //Recuperar dados usuario logado
        recuperarDadosUsuarioLogado();
    }

    @Override
    protected void onStop() {
        super.onStop();
        usuarioAmigoRef.removeEventListener(valueEventListenerPerfilAmigo);
    }

    private void recuperarDadosPerfilAmigo(){
       usuarioAmigoRef = usuariosRef.child(usuarioSelecionado.getId());
       valueEventListenerPerfilAmigo = usuarioAmigoRef.
               addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                       Usuario usuario = snapshot.getValue(Usuario.class);

                       String postagens = String.valueOf(usuario.getPostagens());
                       String seguidores = String.valueOf(usuario.getSeguidores());
                       String seguindo = String.valueOf(usuario.getSeguindo());

                       textPublicacoes.setText(postagens);
                       textSeguidores.setText(seguidores);
                       textSeguindo.setText(seguindo);
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {

                   }
               });
    }

    private void inicializarComponentes(){
        //Inicializar componentes
        buttonAcaoPerfil = findViewById(R.id.buttonAcaoPerfil);
        buttonAcaoPerfil.setText("Carregando");
        imagePerfil = findViewById(R.id.imagePerfil);
        //Referências a usuários
        textPublicacoes = findViewById(R.id.textPublicacoes);
        textSeguidores = findViewById(R.id.textSeguidores);
        textSeguindo = findViewById(R.id.textSeguindo);
        //imagePerfil = findViewById(R.id.imagePerfil);
        gridViewPerfil = findViewById(R.id.gridViewPerfil);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return false;
    }
}
