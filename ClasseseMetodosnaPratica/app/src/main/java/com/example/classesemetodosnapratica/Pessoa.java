package com.example.classesemetodosnapratica;
//Classes
//public - fica acessível para todas as classes
//private - fica acessível apaenas dentro da própria classe
//protected - fica acessível apenas dentro de um determinado pacote ou subclasse
//default - fica acessível apenas dentro do pacote se não for definido algum modificador de acesso.

public class Pessoa {
   private String nome;
   private int idade;

   public void exibirDados(String nome){
       System.out.println("Exibir apenas nome: " + nome);
   }

    public void exibirDados(String nome, int idade){
        System.out.println("Exibir nome: " + nome + " idade: " + idade);
    }
}
