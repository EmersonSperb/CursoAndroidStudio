package com.example.aprendaingles.Fragment;


import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Switch;

import com.example.aprendaingles.MainActivity;
import com.example.aprendaingles.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BichosFragment extends Fragment  implements View.OnClickListener{
    private ImageButton buttonCao;
    private ImageButton buttonGato;
    private ImageButton buttonLeao;
    private ImageButton buttonMacaco;
    private ImageButton buttonOvelha;
    private ImageButton buttonVaca;

    private MediaPlayer mediaPlayer;


    public BichosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bichos, container, false);

        buttonCao = view.findViewById(R.id.buttonCao);
        buttonGato = view.findViewById(R.id.buttonGato);
        buttonLeao = view.findViewById(R.id.buttonLeao);
        buttonMacaco = view.findViewById(R.id.buttonMacaco);
        buttonOvelha = view.findViewById(R.id.buttonOvelha);
        buttonVaca = view.findViewById(R.id.buttonVaca);

        buttonCao.setOnClickListener(this);
        buttonGato.setOnClickListener(this);
        buttonLeao.setOnClickListener(this);
        buttonMacaco.setOnClickListener(this);
        buttonOvelha.setOnClickListener(this);
        buttonVaca.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {

        //Log.i("Elemento clicado","Item: " + view.getId());
        switch (view.getId()){
            case R.id.buttonCao :
                mediaPlayer = MediaPlayer.create(getActivity(),R.raw.dog);
                tocarSom();
                break;

                case R.id.buttonGato :
                mediaPlayer = MediaPlayer.create(getActivity(),R.raw.cat);
                tocarSom();
                break;

            case R.id.buttonLeao :
                mediaPlayer = MediaPlayer.create(getActivity(),R.raw.lion);
                tocarSom();
                break;

            case R.id.buttonMacaco :
                mediaPlayer = MediaPlayer.create(getActivity(),R.raw.monkey);
                tocarSom();
                break;

            case R.id.buttonOvelha :
                mediaPlayer = MediaPlayer.create(getActivity(),R.raw.sheep);
                tocarSom();
                break;

            case R.id.buttonVaca :
                mediaPlayer = MediaPlayer.create(getActivity(),R.raw.cow);
                tocarSom();
                break;

        }
    }

    public void tocarSom(){
        if(mediaPlayer != null) {
          mediaPlayer.start();

          mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
              @Override
              public void onCompletion(MediaPlayer mp) {
                  mediaPlayer.release();
              }
          });
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
