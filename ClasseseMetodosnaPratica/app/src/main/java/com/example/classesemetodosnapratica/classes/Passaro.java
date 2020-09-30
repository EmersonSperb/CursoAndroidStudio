package com.example.classesemetodosnapratica.classes;
//Classes
//public - fica acessível para todas as classes
//private - fica acessível apaenas dentro da própria classe
//protected - fica acessível apenas dentro de um determinado pacote ou subclasse
//default - fica acessível apenas dentro do pacote se não for definido algum modificador de acesso.

class Passaro extends Animal{
    void voar(){
        System.out.println("Voar como um passaro");
    };

}
