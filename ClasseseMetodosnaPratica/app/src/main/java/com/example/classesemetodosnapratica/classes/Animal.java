package com.example.classesemetodosnapratica.classes;
//Classes
//public - fica acessível para todas as classes
//private - fica acessível apaenas dentro da própria classe
//protected - fica acessível apenas dentro de um determinado pacote ou subclasse
//default - fica acessível apenas dentro do pacote se não for definido algum modificador de acesso.

public class Animal {
    protected int tamanho;
    protected String cor;
    protected double peso;

    void dormir(){
        System.out.println("Dormir como um animal");
    }

    void correr(){
         System.out.println("Correr como um ");
    }

    void setCor(String cor){
        //permite formatar ou validar
       this.cor = cor;
    }

    String getCor(){
       return this.cor;
    }

    void setTamanho(int tamanho){
       this.tamanho = tamanho;
    }

    int getTamanho(){
       return this.tamanho;
    }

    void setPeso(double peso){
        this.peso = peso;
    }

    double getPeso(){
        return this.peso;
    }

}
