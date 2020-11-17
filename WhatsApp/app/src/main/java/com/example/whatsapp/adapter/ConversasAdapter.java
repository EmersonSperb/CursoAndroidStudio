package com.example.whatsapp.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.whatsapp.R;
import com.example.whatsapp.model.Usuario;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ConversasAdapter extends RecyclerView.Adapter<ConversasAdapter.MyViewHolder>{

    private List<Usuario> Conversas;
    private Context context;

    public ConversasAdapter(List<Usuario> listaConversas, Context c) {
        this.Conversas = listaConversas;
        this.context = c;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.adapter_conversas,parent,false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Usuario usuario = Conversas.get( position );
        holder.nome.setText(usuario.getNome());
        holder.email.setText(usuario.getEmail());
        if (usuario.getFoto() != null){
            Uri uri = Uri.parse(usuario.getFoto());
            Glide.with(context).load(uri).into(holder.foto);
        }else{
            holder.foto.setImageResource(R.drawable.padrao);

        }

    }

    @Override
    public int getItemCount() {

        return Conversas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nome;
        TextView email;
        CircleImageView foto;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.textNomeContato);
            email = itemView.findViewById(R.id.textEmailContato);
            foto = itemView.findViewById(R.id.imageViewFotoContato);
        }
    }

}
