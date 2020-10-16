package com.example.bancodedadossqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
           //Criar banco
            SQLiteDatabase bancoDados = openOrCreateDatabase("app",MODE_PRIVATE,null);
            //Criar tabela
            /*bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas (nome VARCHAR,idade INT(3))");
            //Insere dados
            bancoDados.execSQL("INSERT INTO pessoas (nome,idade) VALUES ('Mecãnica Simas Turbo',10)");
            bancoDados.execSQL("INSERT INTO pessoas (nome,idade) VALUES ('Benjamim Arrola',19)");
            bancoDados.execSQL("INSERT INTO pessoas (nome,idade) VALUES ('Paula Tejando',25)");
            bancoDados.execSQL("INSERT INTO pessoas (nome,idade) VALUES ('Jalim Rabei',35)");*/
            /*bancoDados.execSQL("INSERT INTO pessoas (nome,idade) VALUES ('Oscar Alho',31)");
            bancoDados.execSQL("INSERT INTO pessoas (nome,idade) VALUES ('Caio Pinto',45)");*/
            //Recuperar os dados
            /*String consulta = "SELECT nome,idade from pessoas " +
                               "WHERE nome = 'Oscar Alho' and idade = 31";*/

            /*String consulta = "SELECT nome,idade from pessoas " +
                    "WHERE (idade >= 30 or idade = 25) and nome LIKE 'P%'";*/

            /*String consulta = "SELECT nome,idade from pessoas " +
                    "WHERE idade IN (25,31,19)";*/

            /*String consulta = "SELECT nome,idade from pessoas " +
                    "WHERE nome LIKE '%au%'";*/

            /*String consulta = "SELECT nome,idade from pessoas " +
                    "WHERE nome IN ('Oscar Alho','Jalim Rabei')";*/

            /*String consulta = "SELECT nome,idade from pessoas " +
                    "WHERE idade BETWEEN 20 and 35";*/

            /*String consulta = "SELECT nome,idade from pessoas " +
                    "ORDER BY idade";*/

            /*String consulta = "SELECT nome,idade from pessoas " +
                    "ORDER BY idade DESC"; */

            /*String consulta = "SELECT nome,idade from pessoas " +
                    "ORDER BY nome ASC";*/

            String consulta = "SELECT DISTINCT nome,idade from pessoas " +
                    "ORDER BY idade DESC LIMIT 3";


            Cursor cursor = bancoDados.rawQuery(consulta,null);
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");
            cursor.moveToFirst();
            while (cursor != null) {
                String nome = cursor.getString(indiceNome);
                String idade = cursor.getString(indiceIdade);
                Log.i("Resultado - Nome " , "nome "+ nome + " idade " + idade);
                cursor.moveToNext();

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}