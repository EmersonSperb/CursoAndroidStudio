package com.example.togglebuttoneswitch;

import androidx.appcompat.app.AppCompatActivity;

import android.net.sip.SipSession;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    private ToggleButton toggleSenha;
    private Switch switchSenha;
    private TextView textResultado;
    private Button buttonEnviar;
    private CheckBox checkSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toggleSenha = findViewById(R.id.toggleSenha);
        switchSenha = findViewById(R.id.switchSenha);
        textResultado = findViewById(R.id.textResultado);
        checkSenha = findViewById(R.id.checkSenha);

        adicionarListener();

    }

    public void enviar(View view){
        /*if (switchSenha.isChecked() ){
            textResultado.setText("Switch ligado");

        }else{textResultado.setText("Switch desligado");

        }*/

        /*if (toggleSenha.isChecked() ){
            textResultado.setText("Toggle ligado");

        }else{textResultado.setText("Toggle desligado");

        }*/

        /*if (checkSenha.isChecked() ){
            textResultado.setText("CheckBox marcado");

        }else{textResultado.setText("CheckBox marcado");

        }*/


    }

    public void adicionarListener (){
        switchSenha.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if ( isChecked ){
                    textResultado.setText("Switch Ligado");

                }else{textResultado.setText("Switch Desligado");

                }
            }
        });

        toggleSenha.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if ( isChecked ){
                    textResultado.setText("Toggle Ligado");

                }else{textResultado.setText("Toggle Desligado");

                }
            }
        });

        checkSenha.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if ( isChecked ){
                    textResultado.setText("CheckBox Ligado");

                }else{textResultado.setText("Check Desligado");

                }
            }
        });
    }


}
