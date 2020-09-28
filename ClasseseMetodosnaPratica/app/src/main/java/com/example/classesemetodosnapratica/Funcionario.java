package com.example.classesemetodosnapratica;

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
