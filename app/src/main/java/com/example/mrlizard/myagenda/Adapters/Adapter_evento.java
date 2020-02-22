package com.example.mrlizard.myagenda.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mrlizard.myagenda.Model.Evento;
import com.example.mrlizard.myagenda.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by mrlizard on 10/11/19.
 */

public class Adapter_evento extends RecyclerView.Adapter<Adapter_evento.ViewHolder> {
private List<Evento> eventos;

    public Adapter_evento(List<Evento> eventos) {
        this.eventos = eventos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.linear_evento, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    Evento evento = eventos.get(position);
    holder.titulo.setText(evento.getTitulo());
    holder.data.setText(evento.getData());
    holder.hora.setText(evento.getHora());


    }

    @Override
    public int getItemCount() {

        return eventos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
TextView titulo;
TextView data;
TextView hora;
        public ViewHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.titulo4);
            data = itemView.findViewById(R.id.data4);
            hora = itemView.findViewById(R.id.hora4);
        }
    }

}
