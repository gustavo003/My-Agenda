package com.example.mrlizard.myagenda.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mrlizard.myagenda.Model.Lembrete;
import com.example.mrlizard.myagenda.R;

public class Meio_Lembrete extends AppCompatActivity {
private TextView titulo;
private TextView data;
private TextView desc;
private TextView hora;
private Lembrete lembrete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meio__lembrete);
        titulo = findViewById(R.id.textView7);
        data = findViewById(R.id.textView9);
        hora = findViewById(R.id.textView11);
        desc = findViewById(R.id.textView13);
        lembrete = (Lembrete) getIntent().getSerializableExtra("lembreteselecionado");
        titulo.setText(lembrete.getTitulo());
        data.setText(lembrete.getData());
        hora.setText(lembrete.getHora());
        desc.setText(lembrete.getDescricao());
        titulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(Meio_Lembrete.this, AdicionarLembrete.class);
                intent.putExtra("lembreteselecionado", lembrete);
                startActivity(intent);
                finish();
            }
        });
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(Meio_Lembrete.this, AdicionarLembrete.class);
                intent.putExtra("lembreteselecionado", lembrete);
                startActivity(intent);
                finish();
            }
        });
        hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(Meio_Lembrete.this, AdicionarLembrete.class);
                intent.putExtra("lembreteselecionado", lembrete);
                startActivity(intent);
                finish();
            }
        });
        desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(Meio_Lembrete.this, AdicionarLembrete.class);
                intent.putExtra("lembreteselecionado", lembrete);
                startActivity(intent);
                finish();
            }
        });

    }
}
