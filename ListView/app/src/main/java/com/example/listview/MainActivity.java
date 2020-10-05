package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
<<<<<<< HEAD
<<<<<<< HEAD
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ListView listLocais;
    private String[] itens = {"Sapiranga","Campo Bom","Dois Irmãos","Novo Hamburgo",
            "Taquara","Parobé","Araricá","Nova Hartz","Morro Reuter","Santa Maria do Herval",
            "São Leopoldo","Ivoti","Estância Velha","Sapucaia do Sul","Esteio","Canoas",
            "Porto Alegre","Igrejinha","Três Coroas","Gramado","Canela","São Francisco de Paula"
    };
=======

public class MainActivity extends AppCompatActivity {
>>>>>>> 49bb5d973a37a7f8ff238ea960818ef5d051ce0e
=======

public class MainActivity extends AppCompatActivity {
>>>>>>> 49bb5d973a37a7f8ff238ea960818ef5d051ce0e

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD
<<<<<<< HEAD

        listLocais = findViewById(R.id.listLocais);
        //Cria adaptador para a Lista
        ArrayAdapter<String> adaptadorLocais = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                itens
        );
        //Associa adaptador
        listLocais.setAdapter(adaptadorLocais);
        //Evento de clique
        listLocais.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String valorSelecionado = listLocais.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),
                        valorSelecionado,
                        Toast.LENGTH_LONG
                ).show();
            }
        });
=======
>>>>>>> 49bb5d973a37a7f8ff238ea960818ef5d051ce0e
=======
>>>>>>> 49bb5d973a37a7f8ff238ea960818ef5d051ce0e
    }
}
