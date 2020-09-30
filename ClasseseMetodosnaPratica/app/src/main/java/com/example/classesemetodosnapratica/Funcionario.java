package com.example.classesemetodosnapratica;
//Classes
//public - fica acessível para todas as classes
//private - fica acessível apaenas dentro da própria classe
//protected - fica acessível apenas dentro de um determinado pacote ou subclasse
//default - fica acessível apenas dentro do pacote se não for definido algum modificador de acesso.

class Funcionario {
    String nome;
    Double salario;

//sem retorno, tipo procedure do delphi
    /*void recuperarSalarioTela(){
        this.salario = this.salario - (this.salario * 0.10);*/
        //System.out.println(this.salario);};

  //tipo função do delphi Pode ser criada sem parâmetros.
    double recuperarSalario(double bonus,double descontoAdicional) {
        this.salario = this.salario - (this.salario * 0.10);
        return this.salario + bonus - descontoAdicional;
        //System.out.println(this.salario);};
    }
}
