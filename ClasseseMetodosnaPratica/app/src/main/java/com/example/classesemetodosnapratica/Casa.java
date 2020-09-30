package com.example.classesemetodosnapratica;
//Classes
//public - fica acessível para todas as classes
//private - fica acessível apaenas dentro da própria classe
//protected - fica acessível apenas dentro de um determinado pacote ou subclasse
//default - fica acessível apenas dentro do pacote se não for definido algum modificador de acesso.

import androidx.annotation.NonNull;

class Casa {
    //propriedades

   String cor;
    //métodos
    void abrirPorta(){
        System.out.println("Porta Aberta");
    }
}
