package com.example.snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private Button buttonAbrir;
    private Button buttonFechar;
    private Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAbrir = findViewById(R.id.buttonAbrir);
        buttonFechar = findViewById(R.id.buttonFechar);
        /*buttonAbrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               snackbar =  Snackbar.make(
                        view,
                        "Confirmar",
                        Snackbar.LENGTH_INDEFINITE
                ).setAction("Confirmar",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                buttonAbrir.setText("Botão abir alterado");
                            }
                        });
               snackbar.show();
               //snackbar.dismiss();


            }
        });*/

        buttonAbrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(
                        view,
                        "Confirmar",
                        Snackbar.LENGTH_INDEFINITE
                ).setAction("Confirmar",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                buttonAbrir.setText("Botão abir alterado");
                            }
                        }).setActionTextColor(getResources().getColor( R.color.colorAccent))
                        .show();
                //snackbar.dismiss();


            }
        });

        buttonFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });


    }

}