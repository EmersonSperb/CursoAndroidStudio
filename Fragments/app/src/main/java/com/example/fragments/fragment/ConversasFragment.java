package com.example.fragments.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fragments.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConversasFragment extends Fragment {
    private TextView textConversa;

    public ConversasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_conversas, container, false);

        textConversa = view.findViewById(R.id.textConversa);
        textConversa.setText("Conversas alterado");
    }

}
