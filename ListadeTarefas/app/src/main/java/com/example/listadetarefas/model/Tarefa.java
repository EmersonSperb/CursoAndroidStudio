package com.example.listadetarefas.model;
import java.io.Serializable;

public class Tarefa implements Serializable {
    public Tarefa(Long id, String nomeTarefa) {
        this.id = id;
        this.nomeTarefa = nomeTarefa;
    }

    private Long id;
    private String nomeTarefa;

    public Tarefa(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeTarefa() {
        return nomeTarefa;
    }

    public void setNomeTarefa(String nomeTarefa) {
        this.nomeTarefa = nomeTarefa;
    }

}


