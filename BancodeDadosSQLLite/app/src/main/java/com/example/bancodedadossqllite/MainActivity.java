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
            //bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas (nome VARCHAR,idade INT(3))");

            //Apagar a tabela
            //bancoDados.execSQL("DROP TABLE pessoas");

            //Recriar tabela com campo codigo
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas (id INTEGER PRIMARY KEY AUTOINCREMENT,nome VARCHAR,idade INT(3))");

            //Insere dados
            /*bancoDados.execSQL("INSERT INTO pessoas (nome,idade) VALUES ('Mecânica Simas Turbo',10)");
            bancoDados.execSQL("INSERT INTO pessoas (nome,idade) VALUES ('Paula Tejando',25)");
            bancoDados.execSQL("INSERT INTO pessoas (nome,idade) VALUES ('Deide Costa',66)");
            bancoDados.execSQL("INSERT INTO pessoas (nome,idade) VALUES ('Oscar Alho',31)");
            bancoDados.execSQL("INSERT INTO pessoas (nome,idade) VALUES ('Jalim Rabei',45)");
            bancoDados.execSQL("INSERT INTO pessoas (nome,idade) VALUES ('Paula Dinhas',15)");
            bancoDados.execSQL("INSERT INTO pessoas (nome,idade) VALUES ('Tomas Turbando',21)");
            bancoDados.execSQL("INSERT INTO pessoas (nome,idade) VALUES ('Mia Regaça',36)");
            bancoDados.execSQL("INSERT INTO pessoas (nome,idade) VALUES ('Power Guido',37)");
            bancoDados.execSQL("INSERT INTO pessoas (nome,idade) VALUES ('Jailson Mendes',45)");
            bancoDados.execSQL("INSERT INTO pessoas (nome,idade) VALUES ('Cuca Beludo',52)");
            bancoDados.execSQL("INSERT INTO pessoas (nome,idade) VALUES ('Kemel Pinto',28)");
            bancoDados.execSQL("INSERT INTO pessoas (nome,idade) VALUES ('Cintia Miavara',30)");*/


            //Atualizar Registros
            //bancoDados.execSQL("UPDATE pessoas SET nome = 'Timelo Rego' WHERE nome = 'Oscar Alho'");
            //Excluir Registros
            //bancoDados.execSQL("DELETE FROM pessoas WHERE nome = 'Mecãnica Simas Turbo'");
            //bancoDados.execSQL("DELETE FROM pessoas WHERE id = 1");
            //bancoDados.execSQL("DELETE FROM pessoas");


            //Recuperar os dados
            /*String consulta = "SELECT id,nome,idade from pessoas " +
                               "WHERE nome = 'Oscar Alho' and idade = 31";*/

            /*String consulta = "SELECT id,nome,idade from pessoas " +
                    "WHERE (idade >= 30 or idade = 25) and nome LIKE 'P%'";*/

            /*String consulta = "SELECT id,nome,idade from pessoas " +
                    "WHERE idade IN (25,31,19)";*/

            /*String consulta = "SELECT id,nome,idade from pessoas " +
                    "WHERE nome LIKE '%au%'";*/

            /*String consulta = "SELECT id,nome,idade from pessoas " +
                    "WHERE nome IN ('Oscar Alho','Jalim Rabei')";*/

            /*String consulta = "SELECT id,nome,idade from pessoas " +
                    "WHERE idade BETWEEN 20 and 35";*/

            /*String consulta = "SELECT id,nome,idade from pessoas " +
                    "ORDER BY idade";*/

            /*String consulta = "SELECT id,nome,idade from pessoas " +
                    "ORDER BY idade DESC"; */

            /*String consulta = "SELECT id,nome,idade from pessoas " +
                    "ORDER BY nome ASC";*/

            /*String consulta = "SELECT DISTINCT id,nome,idade from pessoas " +
                    "ORDER BY idade DESC LIMIT 19";*/

            String consulta = "SELECT * from pessoas " +
                    "ORDER BY id";


            Cursor cursor = bancoDados.rawQuery(consulta,null);
            int indiceId = cursor.getColumnIndex("id");
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");
            cursor.moveToFirst();
            while (cursor != null) {
                String id = cursor.getString(indiceId);
                String nome = cursor.getString(indiceNome);
                String idade = cursor.getString(indiceIdade);
                Log.i("Resultado - Nome " , "Codigo " + id + " nome "+ nome + " idade " + idade);
                cursor.moveToNext();

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}