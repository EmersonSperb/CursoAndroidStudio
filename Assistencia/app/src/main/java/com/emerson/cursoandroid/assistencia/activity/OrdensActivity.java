package com.emerson.cursoandroid.assistencia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.emerson.cursoandroid.assistencia.R;

import java.io.Serializable;

public class OrdensActivity extends AppCompatActivity implements Serializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordens);
    }
}