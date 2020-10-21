package com.example.listadetarefas.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.listadetarefas.R;
import com.example.listadetarefas.helper.TarefaDAO;
import com.example.listadetarefas.model.Tarefa;
import com.google.android.material.textfield.TextInputEditText;

public class AdicionarTarefaActivity extends AppCompatActivity {

    private TextInputEditText editTarefa;
    private Tarefa tarefaAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);

        editTarefa = findViewById(R.id.textTarefa);
        //Recuperar para edição
        tarefaAtual = (Tarefa) getIntent().getSerializableExtra("tarefaSelecionada");
        if (tarefaAtual != null){
            editTarefa.setText(tarefaAtual.getNomeTarefa());
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_adicionar_tarefa,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
           case R.id.itemSalvar:
               TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
            //Executa ação para o Item Salvar
               //Editar
                if (tarefaAtual != null){
                    String nomeTarefa = editTarefa.getText().toString();
                    if (!nomeTarefa.isEmpty()){
                        Tarefa tarefa = new Tarefa();
                        tarefa.setNomeTarefa(nomeTarefa);
                        tarefa.setId(tarefaAtual.getId());
                        //atualizar registro
                        if (tarefaDAO.atualizar(tarefa)){
                            Toast.makeText(getApplicationContext(),"Tarefa atualizada com sucesso",Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(getApplicationContext(),"Erro ao atualizar tarefa",Toast.LENGTH_SHORT).show();
                        }
                    }

                //Incluir
                }else{
                    String nomeTarefa = editTarefa.getText().toString();

                    if (!nomeTarefa.isEmpty()){
                        Tarefa tarefa = new Tarefa();
                        tarefa.setNomeTarefa(nomeTarefa);
                        if (tarefaDAO.salvar(tarefa)){
                            Toast.makeText(getApplicationContext(),"Tarefa salva com sucesso",Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(getApplicationContext(),"Erro ao salvar tarefa",Toast.LENGTH_SHORT).show();
                        }
                }

            }

            break;
        }
        return super.onOptionsItemSelected(item);
    }


}