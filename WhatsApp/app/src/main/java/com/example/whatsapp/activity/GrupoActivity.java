package com.example.whatsapp.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.whatsapp.adapter.ContatosAdapter;
import com.example.whatsapp.adapter.GrupoSelecionadoAdapter;
import com.example.whatsapp.config.ConfiguracaoFirebase;
import com.example.whatsapp.helper.RecyclerItemClickListener;
import com.example.whatsapp.helper.UsuarioFirebase;
import com.example.whatsapp.model.Usuario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.AdapterView;

import com.example.whatsapp.R;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GrupoActivity extends AppCompatActivity {

    private RecyclerView recyclerMembros,recyclerMembrosSelecionados;
    private ContatosAdapter contatosAdapter;
    private GrupoSelecionadoAdapter grupoSelecionadoAdapter;
    private List<Usuario> listaMembros = new ArrayList<>();
    private List<Usuario> listaMembrosSelecionados = new ArrayList<>();
    private DatabaseReference usuariosRef;
    private ValueEventListener valueEventListenerMembros;
    private FirebaseUser usuarioAtual;
    private Toolbar toolbar;
    private FloatingActionButton fabAvancarCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_grupo);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Novo Grupo");
        atualizarMembrosToolbar();
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerMembros = findViewById(R.id.recyclerMembros);
        recyclerMembrosSelecionados = findViewById(R.id.recyclerMembrosSelecionados);
        contatosAdapter = new ContatosAdapter(listaMembros,getApplicationContext());
        grupoSelecionadoAdapter = new GrupoSelecionadoAdapter(listaMembrosSelecionados,getApplicationContext());
        usuarioAtual  = UsuarioFirebase.getUsuarioAtual();

        usuariosRef = ConfiguracaoFirebase.getFirebaseDatabase().child("usuarios");
        fabAvancarCadastro = findViewById(R.id.fabAvancarCadastro);
        //Configurar adapter
        //configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerMembros.setLayoutManager(layoutManager);
        recyclerMembros.setHasFixedSize(true);
        contatosAdapter = new ContatosAdapter(listaMembros, getApplicationContext());
        recyclerMembros.setAdapter(contatosAdapter);

        recyclerMembros.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerMembros,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                Usuario usuarioSelecionado = listaMembros.get(position);
                                listaMembros.remove(usuarioSelecionado);
                                contatosAdapter.notifyDataSetChanged();
                                boolean cabecalho = usuarioSelecionado.getEmail().isEmpty();
                                if (!cabecalho){
                                   listaMembrosSelecionados.add(usuarioSelecionado);
                                   grupoSelecionadoAdapter.notifyDataSetChanged();
                                   atualizarMembrosToolbar();
                                }

                                //Intent i = new Intent(getApplicationContext(), GrupoActivity.class);
                                //startActivity(i);
                                /*boolean cabecalho = usuarioSelecionado.getEmail().isEmpty();
                                if (cabecalho){

                                    Intent i = new Intent(getApplicationContext(), GrupoActivity.class);
                                    startActivity(i);

                                }else{
                                    Intent i = new Intent(getApplicationContext(), ChatActivity.class);
                                    i.putExtra("chatContato",usuarioSelecionado);
                                    startActivity(i);
                                }*/

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

        grupoSelecionadoAdapter = new GrupoSelecionadoAdapter(listaMembrosSelecionados, getApplicationContext());

        RecyclerView.LayoutManager layoutManagerHorizontal = new LinearLayoutManager(
                getApplicationContext(),LinearLayoutManager.HORIZONTAL,
                false);

        recyclerMembrosSelecionados.setLayoutManager(layoutManagerHorizontal);
        recyclerMembrosSelecionados.setHasFixedSize(true);
        recyclerMembrosSelecionados.setAdapter(grupoSelecionadoAdapter);

        recyclerMembrosSelecionados.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerMembrosSelecionados,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                Usuario usuarioSelecionado = listaMembrosSelecionados.get(position);
                                //Sai da lista dos selecionados
                                listaMembrosSelecionados.remove(usuarioSelecionado);
                                grupoSelecionadoAdapter.notifyDataSetChanged();
                                boolean cabecalho = usuarioSelecionado.getEmail().isEmpty();
                                if (!cabecalho){
                                    listaMembros.add(usuarioSelecionado);
                                    contatosAdapter.notifyDataSetChanged();
                                    atualizarMembrosToolbar();
                                }

                                //Intent i = new Intent(getApplicationContext(), GrupoActivity.class);
                                //startActivity(i);
                                /*boolean cabecalho = usuarioSelecionado.getEmail().isEmpty();
                                if (cabecalho){

                                    Intent i = new Intent(getApplicationContext(), GrupoActivity.class);
                                    startActivity(i);

                                }else{
                                    Intent i = new Intent(getApplicationContext(), ChatActivity.class);
                                    i.putExtra("chatContato",usuarioSelecionado);
                                    startActivity(i);
                                }*/

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

        //Evita duplicação dos contatos
        //listaMembros.clear();

        /*Define usuário com e-mail vazio
        em caso de e-mail vazio o usuário será usado como cabeçalho
        exibindo Novo Grupo*/
        Usuario itemGrupo = new Usuario();
        itemGrupo.setNome("Novo Grupo");
        itemGrupo.setEmail("");
        listaMembros.add(itemGrupo);

        //return view;

        fabAvancarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GrupoActivity.this,CadastroGrupoActivity.class);
                i.putExtra("membros", (Serializable) listaMembrosSelecionados);
                startActivity(i);
            }
        });

    };


    public void recuperarContatos(){
        valueEventListenerMembros = usuariosRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dados: snapshot.getChildren()){

                    Usuario usuario = dados.getValue(Usuario.class);
                    String emailUsuarioAtual = usuarioAtual.getEmail();
                    String emailUsuario = usuario.getEmail();
                    if (!emailUsuarioAtual.equals(emailUsuario)){
                        listaMembros.add(usuario);
                    }

                }
                contatosAdapter.notifyDataSetChanged();
                atualizarMembrosToolbar();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }

    @Override
    public void onStart() {
        super.onStart();
        recuperarContatos();
    }

    @Override
    public void onStop() {
        super.onStop();
        usuariosRef.removeEventListener(valueEventListenerMembros);
    }

    public void atualizarMembrosToolbar(){
        int totalSelecionados = listaMembrosSelecionados.size();
        int total = listaMembros.size() + totalSelecionados;
        toolbar.setSubtitle(totalSelecionados + " de " + total + " selecionados");
    }
}