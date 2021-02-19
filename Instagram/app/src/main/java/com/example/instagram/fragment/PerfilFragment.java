package com.example.instagram.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.instagram.R;
import com.example.instagram.activity.EditarPerfilActivity;

import de.hdodenhof.circleimageview.CircleImageView;


public class PerfilFragment extends Fragment {
    private ProgressBar progressBarPerfil;
    private CircleImageView imagePerfil;
    private TextView textPublicacoes,textSeguidores,textSeguindo;
    private Button buttonAcaoPerfil;
    private GridView gridViewPerfil;

    public PerfilFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);


        progressBarPerfil = view.findViewById(R.id.progressBarPerfil);
        gridViewPerfil = view.findViewById(R.id.gridViewPerfil);
        buttonAcaoPerfil = view.findViewById(R.id.buttonAcaoPerfil);
        textPublicacoes = view.findViewById(R.id.textPublicacoes);
        textSeguidores = view.findViewById(R.id.textSeguidores);
        textSeguindo = view.findViewById(R.id.textSeguindo);
        imagePerfil = view.findViewById(R.id.imageEditarPerfil);

        buttonAcaoPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), EditarPerfilActivity.class);
                startActivity(i);
            }
        });

        return view;


    }
}