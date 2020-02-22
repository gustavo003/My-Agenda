package com.example.mrlizard.myagenda.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mrlizard.myagenda.Model.Lembrete;
import com.example.mrlizard.myagenda.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by mrlizard on 10/11/19.
 */

public class Adapter_lembrete extends RecyclerView.Adapter<Adapter_lembrete.MyView> {
private List <Lembrete> lembrete;

    public Adapter_lembrete(List<Lembrete> lembrete) {
        this.lembrete = lembrete;
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.linear_lembrete, null);
        return new MyView(view);

    }

    @Override
    public void onBindViewHolder(MyView holder, int position) {
Lembrete lembretes =lembrete.get(position);
holder.titulo.setText(lembretes.getTitulo());
holder.data.setText(lembretes.getData());
holder.hora.setText(lembretes.getHora());


    }

    @Override
    public int getItemCount() {

        return lembrete.size();
    }

    public class MyView extends RecyclerView.ViewHolder{

TextView titulo;
TextView data;
TextView hora;
        public MyView(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.tituloL);
            data = itemView.findViewById(R.id.dataL);
            hora = itemView.findViewById(R.id.horaL);
        }
    }
}
