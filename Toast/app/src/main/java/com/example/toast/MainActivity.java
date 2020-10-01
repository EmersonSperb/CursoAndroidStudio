package com.example.toast;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.fonts.Font;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void abrirToast( View view ){
        ImageView imagem = new ImageView(getApplicationContext());
        imagem.setImageResource(android.R.drawable.star_big_on);

        TextView textView = new TextView(getApplicationContext());
        textView.setText("Teste do toast");
        textView.setBackgroundResource(R.color.colorPrimaryDark);
        /*Toast.makeText(
                getApplicationContext(),
                "Teste do toast",
                Toast.LENGTH_SHORT


        ).show();*/

        Toast toast = new Toast(this);
           toast.setDuration(Toast.LENGTH_LONG);
           //toast.setView(imagem);
           toast.setView(textView);
           toast.setGravity(200,250,200);
           toast.show();

    };
}
