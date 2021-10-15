package com.example.instagram.model;

import com.example.instagram.helper.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;

public class Postagem {
    /*
    Modelo d ePostagem
    postagens
       <id_usuario>
          <id_postagem_firebase>
             descricao
             caminhofoto
             idUsuario
     */

    private String id;
    private String idUsuario;
    private String descricao;
    private String caminhofoto;

    public Postagem(){
        DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebase();
        DatabaseReference postagemRef = firebaseRef.child("postagens");
        String idPostagem = postagemRef.push().getKey();
        setId(idPostagem);

    }

    public boolean salvar(){
        DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebase();
        DatabaseReference postagensRef = firebaseRef.child("postagens")
                .child(getIdUsuario())
                .child(getId());
        postagensRef.setValue(this);

        return true;

    }

    public Postagem(String id, String idUsuario, String descricao, String caminhofoto) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.descricao = descricao;
        this.caminhofoto = caminhofoto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCaminhofoto() {
        return caminhofoto;
    }

    public void setCaminhofoto(String caminhofoto) {
        this.caminhofoto = caminhofoto;
    }
}
