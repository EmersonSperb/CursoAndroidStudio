package com.example.classesemetodosnapratica;
//Construtores
//public - fica acessível para todas as classes
//private - fica acessível apaenas dentro da própria classe
//protected - fica acessível apenas dentro de um determinado pacote ou subclasse
//default - fica acessível apenas dentro do pacote se não for definido algum modificador de acesso.
//sempre inicia as palavras com Maiúsculas

public class ContaBancaria {

    private int numeroConta;
    private double saldo;

   public ContaBancaria (){
      System.out.println("Configurações iniciais.");
   }

    public ContaBancaria (int nConta){
       this.numeroConta = nConta;
        //System.out.println("Construtor chamado! Conta: " + nConta);
    }
}
