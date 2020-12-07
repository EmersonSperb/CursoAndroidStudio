package com.example.instagram.model;

import com.example.instagram.config.ConfiguracaoFirebase;
import com.example.instagram.helper.UsuarioFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Usuario implements Serializable {

    private String idUsuario;
    private String nome;
    private String email;
    private String senha;
    private String foto;

    public Usuario() {
    }

    public void salvar(){
        DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();
        //Dá para usar getIdUsuario no lugar de this.idUsuario
        firebase.child("usuarios")
                .child(this.idUsuario)
                .setValue(this);
    }

    public void atualizar(){
        String identificadorUsuario = UsuarioFirebase.getIdentificadorUsuario();
        DatabaseReference dataBase = ConfiguracaoFirebase.getFirebaseDatabase();
        DatabaseReference usuarioRef = dataBase.child("usuarios").child(identificadorUsuario);

        Map<String,Object> valoresUsuario = converterParaMap();
        usuarioRef.updateChildren(valoresUsuario);
    }

    @Exclude
    public Map<String,Object> converterParaMap(){

        HashMap<String,Object> usuarioMap = new HashMap<>();
        usuarioMap.put("nome",getNome());
        usuarioMap.put("email",getEmail());
        usuarioMap.put("foto",getFoto());

        return usuarioMap;
    }

    @Exclude
    public String getIdUsuario() {

        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {

        this.idUsuario = idUsuario;
    }

    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }
    @Exclude
    public String getSenha() {

        return senha;
    }

    public void setSenha(String senha) {

        this.senha = senha;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
