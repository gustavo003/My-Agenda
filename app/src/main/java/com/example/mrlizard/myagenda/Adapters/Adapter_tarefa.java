package com.example.mrlizard.myagenda.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mrlizard.myagenda.Model.Tarefa;
import com.example.mrlizard.myagenda.R;

import java.util.List;

/**
 * Created by mrlizard on 07/11/19.
 */

public class Adapter_tarefa extends RecyclerView.Adapter<Adapter_tarefa.MyHolder> {
    List<Tarefa> tarefa;

    public Adapter_tarefa(List<Tarefa> tarefa) {

        this.tarefa=tarefa;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.linear_tarefa, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
    Tarefa tarefa2 = tarefa.get(position);
    holder.titulo.setText(tarefa2.getTitulo());
    holder.data.setText(tarefa2.getData());


    }

    @Override
    public int getItemCount() {
        return tarefa.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
TextView titulo;
TextView data;


        public MyHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.titulo);
            data = itemView.findViewById(R.id.data);
        }
    }

}
