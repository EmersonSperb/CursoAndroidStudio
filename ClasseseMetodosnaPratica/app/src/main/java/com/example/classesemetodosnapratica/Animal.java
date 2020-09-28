package com.example.classesemetodosnapratica;

class Animal {
    int tamanho;
    String cor;
    double peso;

    void dormir(){
        System.out.println("Dormir como um animal");
    }

    void correr(){
         System.out.println("Correr como um animal");
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
