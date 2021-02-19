package com.example.instagram.activity;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.instagram.R;
import com.example.instagram.model.Usuario;

public class PerfilAmigoActivity extends AppCompatActivity {

    private Usuario usuarioSelecionado;
    private Button buttonAcaoPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_amigo);

        //Inicializar componentes
        buttonAcaoPerfil = findViewById(R.id.buttonAcaoPerfil);
        buttonAcaoPerfil.setText("Seguir");

        //Configura toolbar
        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("Pefil");
        setSupportActionBar( toolbar );

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);

        //Recuperar usuario selecionado
        Bundle bundle = getIntent().getExtras();
        if( bundle != null ){
            usuarioSelecionado = (Usuario) bundle.getSerializable("usuarioSelecionado");

            //Configura nome do usuário na toolbar
            getSupportActionBar().setTitle( usuarioSelecionado.getNome() );

        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return false;
    }
}
