package com.example.mrlizard.myagenda.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mrlizard.myagenda.Model.Anotacao;
import com.example.mrlizard.myagenda.R;

import java.util.List;

/**
 * Created by mrlizard on 07/11/19.
 */

public class Adpter_anotacao extends RecyclerView.Adapter<Adpter_anotacao.MyViewHolder>{

    List<Anotacao> anotacao;

    public Adpter_anotacao(List<Anotacao> anotacao) {
        this.anotacao=anotacao;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.linear_anotacao, null);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
    Anotacao anotacao2 = anotacao.get(position);
    holder.titulo2.setText(anotacao2.getTitulo().toString());

    }

    @Override
    public int getItemCount() {
        return anotacao.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
TextView titulo2;

        public MyViewHolder(View itemView) {
            super(itemView);
            titulo2= (TextView) itemView.findViewById(R.id.titulo2);
        }
    }
}
