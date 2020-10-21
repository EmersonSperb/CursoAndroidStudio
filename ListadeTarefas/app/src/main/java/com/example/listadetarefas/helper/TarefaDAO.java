package com.example.listadetarefas.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.listadetarefas.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class TarefaDAO implements ITarefaDAO {

    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public TarefaDAO(Context context) {

        DbHelper db = new DbHelper(context);
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Tarefa tarefa) {
        ContentValues cv = new ContentValues();
        cv.put("nome",tarefa.getNomeTarefa());
        try {
            escreve.insert(DbHelper.TABELA_TAREFAS,null, cv);
            Log.i("INFO","Tarefa salva com sucesso");
            //cv.put("status","E");

        }catch(Exception e){
            Log.i("INFO","Erro ao salvar tarefa" + e.getMessage());
            return false;

        }

        return true;
    }

    @Override
    public boolean atualizar(Tarefa tarefa) {
        ContentValues cv = new ContentValues();
        cv.put("nome",tarefa.getNomeTarefa());


        String where = "id = ?";
        String[] argumentos = {tarefa.getId().toString()};
        try {
            escreve.update(DbHelper.TABELA_TAREFAS,cv,"id=?",argumentos);
            Log.i("INFO","Tarefa atualizada com sucesso");
            //cv.put("status","E");

        }catch(Exception e){
            Log.i("INFO","Erro ao atualizar tarefa" + e.getMessage());
            return false;

        }

        return true;
    }

    @Override
    public boolean deletar(Tarefa tarefa) {
        String where = "id = ?";
        String[] argumentos = {tarefa.getId().toString()};
        try {
            escreve.delete(DbHelper.TABELA_TAREFAS,"id=?",argumentos);
            Log.i("INFO","Tarefa excluída com sucesso com sucesso");
            //cv.put("status","E");

        }catch(Exception e){
            Log.i("INFO","Erro ao excluir tarefa" + e.getMessage());
            return false;

        }

        return true;
    }

    @Override
    public List<Tarefa> listar() {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "Select * FROM " + DbHelper.TABELA_TAREFAS + ";";
        Cursor c = le.rawQuery(sql,null);

        while(c.moveToNext()){
            Tarefa tarefa = new Tarefa();
            Long id = c.getLong(c.getColumnIndex("id"));
            String nomeTarefa = c.getString(c.getColumnIndex("nome"));
            tarefa.setId(id);
            tarefa.setNomeTarefa(nomeTarefa);
            tarefas.add(tarefa);

        }
        return tarefas;
    }
}
