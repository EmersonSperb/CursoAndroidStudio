package com.example.whatsapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.whatsapp.R;
import com.example.whatsapp.adapter.ContatosAdapter;
import com.example.whatsapp.config.ConfiguracaoFirebase;
import com.example.whatsapp.fragment.ContatosFragment;
import com.example.whatsapp.fragment.ConversasFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth autenticacao;

    private SmartTabLayout smartTabLayout;
    private ViewPager viewPager;

    private MaterialSearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("Whatsapp");
        setSupportActionBar(toolbar);

        smartTabLayout = findViewById(R.id.viewPagerTab);
        viewPager = findViewById(R.id.viewPager);

        getSupportActionBar().setElevation(0);
        //Configurar adapter para as abas
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("Conversas", ConversasFragment.class)
                .add("Contatos", ContatosFragment.class)
                .create());

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewPagerTab);
        viewPagerTab.setViewPager(viewPager);

        searchView = findViewById(R.id.materialSearchPrincipal);
        //listener para Search View
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {
                ConversasFragment fragment = (ConversasFragment) adapter.getPage(0);
                fragment.recarregarConversas();
            }
        });
        //Listener para caixa de texto
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Log.i("evento","onQueryTextSubmit");
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Log.i("evento","onQueryTextChange");

                switch (viewPager.getCurrentItem()){
                    case 0 :
                        ConversasFragment conversasFragment = (ConversasFragment) adapter.getPage(0);
                        if (newText != null && !newText.isEmpty()){
                            conversasFragment.pesquisarConversas(newText.toLowerCase());
                        }else{
                            conversasFragment.recuperarConversas();
                        }
                        break;

                    case 1 :
                        ContatosFragment contatosFragment = (ContatosFragment) adapter.getPage(1);
                        if (newText != null && !newText.isEmpty()){
                            contatosFragment.pesquisarContatos(newText.toLowerCase());
                        }else{
                            contatosFragment.recarregarContatos();
                        }
                        break;

                }



                return true;
            }
        });

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);

        MenuItem item = menu.findItem(R.id.menuPesquisa);
        searchView.setMenuItem(item);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuSair :
                deslogarUsuario();
                abrirTelaLogin();
                break;

            case R.id.menuConfiguracoes :
                abrirTelaConfiguracoes();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void deslogarUsuario(){

        try {
            autenticacao.signOut();
            }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void abrirTelaLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    public void abrirTelaConfiguracoes() {
        //startActivity(new Intent(this, ConfiguracoesActivity.class));
        Intent intent = new Intent(this, ConfiguracoesActivity.class);
        startActivity(intent);
    }

}