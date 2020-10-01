package com.example.progressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressHorizontal;
    private ProgressBar progressCircular;
    private int progresso = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressCircular   = findViewById(R.id.progressBarCircular);
        progressHorizontal = findViewById(R.id.progressBarHorizontal);

        progressCircular.setVisibility( View.GONE);
    }

    public void carregarProgressBar(View view){
      this.progresso = this.progresso  + 1;
      //Horizontal
        progressHorizontal.setProgress(this.progresso);
      //Circular
        progressCircular.setVisibility(View.VISIBLE);

        if (this.progresso == 20 ){
            progressCircular.setVisibility(View.GONE);
        }

    }

}
