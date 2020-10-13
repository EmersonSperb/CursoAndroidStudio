package com.example.atmconsultoria.ui.sobre;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.atmconsultoria.R;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

/**
 * A simple {@link Fragment} subclass.
 */
public class SobreFragment extends Fragment {


    public SobreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_sobre, container, false);
        String descricao = "Essa é uma empresa de fachada usada entre outras coisa para lavagem de dinheiro.";
        Element versao = new Element();
        versao.setTitle("Versão 1.0.0.0");
       return new AboutPage(getActivity())
                .setImage(R.drawable.logo)
                .setDescription(descricao)
                .addGroup("Entre em contato")
                .addEmail("email@servidor.com","Envie um e-mail")
                .addGroup("Redes sociais")
                .addWebsite("https://www.pudim.com.br","Visite nosso site")
                .addFacebook("emersonsperb","Facebook")
                .addTwitter("emerson1983","Twitter")
                .addYoutube("emersonsperb","Youtube")
                .addPlayStore("com.facebook.katana")
                .addGitHub("EmersonSperb")
                .addInstagram("emersonsperb")
               .addItem(versao)
                .create();
    }

}
