package com.example.organizze.config;

import com.google.firebase.auth.FirebaseAuth;

public class ConfiguracaoFirebase {

    private static FirebaseAuth autenticacao;
    //retorna a instância do firebird

    public static FirebaseAuth getFirebaseAutenticacao() {
        return autenticacao;
    }
}
