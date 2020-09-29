package com.example.pedrapapeloutesoura;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void selecionadoPedra(View view){
       //System.out.println("Selecionado Pedra");
       this.opcaoSelecionada("pedra");
    }

    public void selecionadoPapel(View view){
        //System.out.println("Selecionado Papel");
        this.opcaoSelecionada("papel");
    }

    public void selecionadoTesoura(View view){
        //System.out.println("Selecionado Tesoura");
        this.opcaoSelecionada("tesoura");
    }

    public void opcaoSelecionada(String escolhaUsuario){
        ImageView imagemResultado = findViewById(R.id.imageResultado);

        int numero= new Random().nextInt(3);
        /*Random random = new Random();
        random.NextInt;
         */
        String[] opcoes = {"pedra","papel","tesoura"};
        String escolhaApp = opcoes[ numero ];
        switch (escolhaApp) {
            case "pedra" :  imagemResultado.setImageResource(R.drawable.pedra);
               break;
            case "papel" :  imagemResultado.setImageResource(R.drawable.papel);
                break;
            case "tesoura" :  imagemResultado.setImageResource(R.drawable.tesoura);
               break;
        }
        TextView textoResultado = findViewById(R.id.textResultado);

        if (
           (escolhaApp == "tesoura" && escolhaUsuario == "papel")||
           (escolhaApp == "papel" && escolhaUsuario == "pedra") ||
           (escolhaApp == "pedra" && escolhaUsuario == "tesoura"))

{           textoResultado.setText("Você perdeu :(");
        }else if(
                (escolhaUsuario == "tesoura" && escolhaApp == "papel")||
                (escolhaUsuario == "papel" && escolhaApp == "pedra") ||
                (escolhaUsuario == "pedra" && escolhaApp == "tesoura")) {
            textoResultado.setText("Você venceu :)");
        }else{
            textoResultado.setText("Empate :|");
        }
        //System.out.println("Item clicado: " + escolhaUsuario);
    }
}
