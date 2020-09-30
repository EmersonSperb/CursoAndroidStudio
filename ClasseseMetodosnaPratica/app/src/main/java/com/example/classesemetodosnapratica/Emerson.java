package com.example.classesemetodosnapratica;

public class Emerson extends Cidadao implements Presidente {
    @Override
    public void ganharEleicao() {
        System.out.println("Ganhar eleição no Brasil");
    }
}
