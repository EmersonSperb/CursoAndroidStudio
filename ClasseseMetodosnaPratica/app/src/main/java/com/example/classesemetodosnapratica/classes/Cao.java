package com.example.classesemetodosnapratica.classes;
//Classes
//public - fica acessível para todas as classes
//private - fica acessível apaenas dentro da própria classe
//protected - fica acessível apenas dentro de um determinado pacote ou subclasse
//default - fica acessível apenas dentro do pacote se não for definido algum modificador de acesso.

class Cao extends Animal {
    void latir(){
        System.out.println("Latir como um cão");
    }

    void correr(){
        super.correr();
        System.out.println("cão");
    };

}
