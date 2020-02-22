package com.example.mrlizard.myagenda.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mrlizard.myagenda.Model.Evento;
import com.example.mrlizard.myagenda.R;

public class Meio_evento extends AppCompatActivity {
private TextView titulo;
    private TextView data;
    private TextView hora;
    private TextView end;
    private TextView contato;
    private TextView desc;
    private Evento evento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meio_evento);
        titulo = findViewById(R.id.textView15);
        data = findViewById(R.id.textView17);
        hora = findViewById(R.id.textView18);
        end = findViewById(R.id.textView19);
        contato = findViewById(R.id.textView20);
        desc = findViewById(R.id.textView21);
        evento = (Evento) getIntent().getSerializableExtra("eventoselecionado");
        titulo.setText(evento.getTitulo());
        data.setText("Data: "+ evento.getData());
        hora.setText("Horário: "+ evento.getHora());
        end.setText("Endereço: " +evento.getLugar());
        contato.setText("Contatos: " +evento.getContatos());
        desc.setText("Descrição: \n\n\n" + evento.getDescricao());
        titulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Meio_evento.this, AdicionarEvento.class);
                intent.putExtra("eventoselecionado", evento);
                startActivity(intent);
                finish();
            }
        });
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Meio_evento.this, AdicionarEvento.class);
                intent.putExtra("eventoselecionado", evento);
                startActivity(intent);
                finish();
            }
        });
        hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Meio_evento.this, AdicionarEvento.class);
                intent.putExtra("eventoselecionado", evento);
                startActivity(intent);
                finish();
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Meio_evento.this, AdicionarEvento.class);
                intent.putExtra("eventoselecionado", evento);
                startActivity(intent);
                finish();
            }
        });
        desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Meio_evento.this, AdicionarEvento.class);
                intent.putExtra("eventoselecionado", evento);
                startActivity(intent);
                finish();
            }
        });
        contato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Meio_evento.this, AdicionarEvento.class);
                intent.putExtra("eventoselecionado", evento);
                startActivity(intent);
                finish();
            }
        });




    }
}
