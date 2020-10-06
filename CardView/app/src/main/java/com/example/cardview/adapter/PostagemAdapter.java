package com.example.cardview.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardview.model.Postagem;
import com.example.cardview.R;

import java.util.List;

public class PostagemAdapter extends RecyclerView.Adapter<PostagemAdapter.MyViewHolder>{
    private List<Postagem> listaPostagens;

    public PostagemAdapter(List<Postagem> lista) {
        this.listaPostagens = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.postagem_detalhe,parent,false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Postagem postagem = listaPostagens.get( position );
        holder.textNome.setText(postagem.getNome());
        //holder.tempo.setText(postagem.getTempoPostagem());
        holder.textPostagem.setText(postagem.getPostagem());
        holder.imagePostagem.setImageResource(R.drawable.imagem1);


    }

    @Override
    public int getItemCount() {

        return listaPostagens.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textNome;
        //TextView tempo;
        TextView textPostagem;
        ImageView imagePostagem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textNome = itemView.findViewById(R.id.textNome);
            //tempo    = itemView.findViewById(R.id.textTempo);
            textPostagem = itemView.findViewById(R.id.textPostagem);
            imagePostagem = itemView.findViewById(R.id.imagePostagem);
        }
    }

}
