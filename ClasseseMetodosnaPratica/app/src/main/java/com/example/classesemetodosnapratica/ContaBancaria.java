package com.example.classesemetodosnapratica;

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
