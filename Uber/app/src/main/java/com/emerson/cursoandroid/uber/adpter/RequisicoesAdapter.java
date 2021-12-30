package com.emerson.cursoandroid.uber.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.emerson.cursoandroid.uber.R;
import com.emerson.cursoandroid.uber.model.Requisicao;
import com.google.android.gms.maps.model.LatLng;
import com.emerson.cursoandroid.uber.helper.Local;
import com.emerson.cursoandroid.uber.model.Usuario;

import java.util.List;

public class RequisicoesAdapter extends RecyclerView.Adapter<RequisicoesAdapter.MyViewHolder> {

    private final List<Requisicao> requisicoes;
    private final Context context;
    private final Usuario motorista;

    public RequisicoesAdapter(List<Requisicao> requisicoes, Context context, Usuario motorista) {
        this.requisicoes = requisicoes;
        this.context = context;
        this.motorista = motorista;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_requisicoes, parent, false);
        return new MyViewHolder( item ) ;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Requisicao requisicao = requisicoes.get( position );
        Usuario passageiro = requisicao.getPassageiro();

        holder.nome.setText( passageiro.getNome() );

        if(motorista!= null){

            LatLng localPassageiro = new LatLng(
                    Double.parseDouble(passageiro.getLatitude()),
                    Double.parseDouble(passageiro.getLongitude())
            );

            //Fui obrigado a fazer isso pois não estava retornando os dados do motorista
            String lat = motorista.getLatitude();
            String lon = motorista.getLongitude();

            String latitude;
            String longitude;

            if (lat != null){
               latitude = lat;}
            else{
                latitude = "-29.569874";
            }

            if (lon != null){
                longitude = lon;
            }
            else{
                longitude = "-51.9857458";
            }
            LatLng localMotorista = new LatLng(
                    /*Double.parseDouble(motorista.getLatitude()),
                    Double.parseDouble(motorista.getLongitude())*/
                    Double.parseDouble(latitude),
                    Double.parseDouble(longitude)
            );
            float distancia = Local.calcularDistancia(localPassageiro, localMotorista);
            String distanciaFormatada = Local.formatarDistancia(distancia);
            holder.distancia.setText(distanciaFormatada + "- aproximadamente");

        }

    }

    @Override
    public int getItemCount() {
        return requisicoes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nome, distancia;

        public MyViewHolder(View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.textRequisicaoNome);
            distancia = itemView.findViewById(R.id.textRequisicaoDistancia);

        }
    }

}
