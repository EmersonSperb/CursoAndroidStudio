package com.example.atmconsultoria;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               enviarEmail();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_clientes,
                R.id.nav_contato,R.id.nav_principal,R.id.nav_sobre,R.id.nav_servico)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void enviarEmail(){
        String celular = "tel:51992988554";
        String imagem = "https://vidadememes.files.wordpress.com/2011/10/datena-ibagens-imagens.png?w=614";
        String endereco = "https://www.google.com/maps/place/R.+Santa+Helena,+15+-+Centenario,+Sapiranga+-+RS,+93815-016/@-29.6341978,-51.0072792,3a,75y,275.14h,90.73t/data=!3m7!1e1!3m5!1sNkXJhjS2JQfKoC_7xwhTnQ!2e0!6s%2F%2Fgeo3.ggpht.com%2Fcbk%3Fpanoid%3DNkXJhjS2JQfKoC_7xwhTnQ%26output%3Dthumbnail%26cb_client%3Dsearch.gws-prod.gps%26thumb%3D2%26w%3D86%26h%3D86%26yaw%3D292.10797%26pitch%3D0%26thumbfov%3D100!7i13312!8i6656!4m5!3m4!1s0x95193f6129483e8b:0xe76f4e8dd48c0225!8m2!3d-29.634132!4d-51.0074769";
        //Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:51992988554"));
        //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(imagem));
        //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(endereco));
        //startActivity(intent);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL , new String[]{"emerson@agilsoft.com.br"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "E-mail de teste");
        intent.putExtra(Intent.EXTRA_TEXT, "Corpo do e-mail de teste");
        intent.setType("message/rfc822");//E-mail
        //intent.setType("text/plain");
        //intent.setType("image/*");
        //intent.setType("application/pdf");
        startActivity(Intent.createChooser(intent,"Escolha um app de e-mail"));


    }
}
