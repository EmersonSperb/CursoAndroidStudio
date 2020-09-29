package com.example.classesemetodosnapratica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     /*Funcionario funcionario = new Funcionario();
     funcionario.nome = "Emerson";
     funcionario.salario = 2500.00;
     //funcionario.recuperarSalarioTela();
     double salarioRecuperado = funcionario.recuperarSalario(400,60);
     System.out.println("O salário do funcionário é " + salarioRecuperado);*/
    /*Casa minhaCasa = new Casa();
    minhaCasa.cor = "blue";
    Casa minhaCasa2;
    minhaCasa2 = new Casa();
    System.out.println(minhaCasa.cor);
    minhaCasa.abrirPorta();*/
   Animal animal = new Animal();
   animal.correr();
   animal.dormir();

   Cao cao = new Cao();
   cao.dormir();
   cao.correr();
   cao.latir();

   Passaro passaro = new Passaro();
   passaro.voar();
   passaro.correr();
   passaro.dormir();

   cao.setCor("Caramelo");
   System.out.println(cao.getCor());

   passaro.setTamanho(20);
   System.out.println(passaro.getTamanho());
    }
}
