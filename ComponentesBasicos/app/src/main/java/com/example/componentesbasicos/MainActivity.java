package com.example.componentesbasicos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    //Deixa disponível para todos os métodos
    private EditText campoNome;
    private TextInputEditText campoEmail;
    private TextView textoResultado;
    //CheckBox
    /*private CheckBox checkVerde;
    private CheckBox checkBranco;
    private CheckBox checkVermelho;*/
    private CheckBox checkVerde,checkBranco,checkVermelho;
    //RadioButton
    private RadioButton sexoMasculino,sexoFeminino;
    //RadioGroup
    private RadioGroup opcaoSexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        campoNome = findViewById(R.id.editNome);
        campoEmail = findViewById(R.id.editEmail);
        textoResultado = findViewById(R.id.textResultado);
        //Checkbox
        checkBranco   = findViewById(R.id.checkBranco);
        checkVerde    = findViewById(R.id.checkVerde);
        checkVermelho = findViewById(R.id.checkVermelho);
        //Radio Button
        sexoMasculino = findViewById(R.id.radioMasculino);
        sexoFeminino  = findViewById(R.id.radioFeminino);
        //Radio Group
        opcaoSexo = findViewById(R.id.radioSexo);
        radiobutton();
    }

   public void radiobutton(){

      /*if(sexoMasculino.isChecked()){
         textoResultado.setText("Masculino");

      }else if(sexoFeminino.isChecked()){
        textoResultado.setText("Feminino");
    }*/
      opcaoSexo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(RadioGroup group, int checkedId) {
          if ( checkedId == R.id.radioMasculino ){
             textoResultado.setText("Masculino");
          }else if( checkedId == R.id.radioFeminino ){
              textoResultado.setText("Feminino");
          }
          }
      });
   }


    public void enviar(View view){
        /*String nome = campoNome.getText().toString();
        String email = campoEmail.getText().toString();
        String resultado = "Nome: " + nome + " E-mail: " + email;
        textoResultado.setText(resultado);*/

        //checkbox();
        //radiobutton();
    }

    public void checkbox() {
       /*boolean resultadoVerde = checkVerde.isChecked();
       textoResultado.setText("Verde: " + resultadoVerde);
       boolean resultadoBranco = checkBranco.isChecked();
       textoResultado.setText("Branco: " + resultadoBranco);
       boolean resultadoVermelho = checkVermelho.isChecked();
       textoResultado.setText("Vermelho: " + resultadoVermelho);*/

        String texto = "";

        if (checkVerde.isChecked()) {
            String corSelecionada = checkVerde.getText().toString();
            texto = corSelecionada;
            //texto = texto + "Verde selecioando ";
        }
        if (checkBranco.isChecked()) {
            texto = texto + "Branco selecionado ";
        }
        if (checkVermelho.isChecked()) {
            texto = texto + "Vermelho selecionado ";
        }

        textoResultado.setText( texto );
    }

    public void limpar(View view){
       campoNome.setText("");
       campoEmail.setText("");
       textoResultado.setText("");
    }
}
