package com.example.alertdialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void abrirDialog(View view){
        //Instanciar
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        //Titulo
        dialog.setTitle("Título");
        //Cancelamento
        dialog.setCancelable(false);//false não sai se clicar fora, true permite clicar fora
        //ícone
        dialog.setIcon(android.R.drawable.ic_menu_view);
        //mensagem
        dialog.setMessage("Mensagem de teste");
        //definir botões
        dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),
                        "Clicou no botão sim",
                        Toast.LENGTH_LONG).show();
            }
        });

        dialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),
                        "Clicou no botão não",
                        Toast.LENGTH_LONG).show();
            }
        });


        dialog.create();
        dialog.show();
    }

}
