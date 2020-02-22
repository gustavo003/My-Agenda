package com.example.mrlizard.myagenda.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mrlizard.myagenda.Model.Tarefa;
import com.example.mrlizard.myagenda.R;

public class Meio_Tarefa extends AppCompatActivity {
private TextView titulo;
private TextView data;
private TextView descricao;
private Tarefa tarefa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meio__tarefa);
        titulo = findViewById(R.id.textView3);
        data = findViewById(R.id.textView5);
        descricao = findViewById(R.id.textView6);
        tarefa = (Tarefa) getIntent().getSerializableExtra("tarefaselecionada");
        titulo.setText(tarefa.getTitulo());
        data.setText(tarefa.getData());
        descricao.setText(tarefa.getDescricao());
        titulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(Meio_Tarefa.this, AdicionarTarefa.class);
                intent.putExtra("tarefaselecionada", tarefa);
                startActivity(intent);
                finish();
            }
        });
data.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent  = new Intent(Meio_Tarefa.this, AdicionarTarefa.class);
        intent.putExtra("tarefaselecionada", tarefa);
        startActivity(intent);
        finish();
    }
});
descricao.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent  = new Intent(Meio_Tarefa.this, AdicionarTarefa.class);
        intent.putExtra("tarefaselecionada", tarefa);
        startActivity(intent);
        finish();
    }
});
    }
}
