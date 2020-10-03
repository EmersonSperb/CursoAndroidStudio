package com.example.calculadoradegorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
   private SeekBar seekBarGorjeta;
   private TextInputEditText editValor;
   private TextView textPorcentagem;
   private TextView textGorjeta;
   private TextView textTotal;
   double porcentagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textGorjeta = findViewById(R.id.textGorjeta);
        textPorcentagem = findViewById(R.id.textPorcentagem);
        textTotal = findViewById(R.id.textTotal);
        editValor = findViewById(R.id.editValor);
        editValor.setText("");
        textGorjeta.setText("R$ 0.00");
        textTotal.setText("R$ 0.00");
        textPorcentagem.setText("0%");
        seekBarGorjeta = findViewById(R.id.seekBarGorjeta);
        seekBarGorjeta.setMax(100);
        seekBarGorjeta.setProgress(0);
        seekBarGorjeta.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                porcentagem = progress;
                textPorcentagem.setText(Math.round(porcentagem) + "%");
                calcular();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        };

    public void calcular(){

        String valorRecuperado = editValor.getText().toString();

        if (valorRecuperado.equals("")||valorRecuperado == null){
            Toast.makeText(getApplicationContext(),
                    "Informe o valor",
                    Toast.LENGTH_SHORT).show();
        }else{
            double valorConta = Double.parseDouble(valorRecuperado);
            double gorjeta    = valorConta * (porcentagem / 100);
            double valorTotal = (valorConta + gorjeta);

            textGorjeta.setText("R$ " + gorjeta);
            textTotal.setText("R$ " + valorTotal);
            }
    }
}
