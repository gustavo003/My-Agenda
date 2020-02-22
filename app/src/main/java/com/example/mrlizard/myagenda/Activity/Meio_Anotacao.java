package com.example.mrlizard.myagenda.Activity;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mrlizard.myagenda.Model.Anotacao;
import com.example.mrlizard.myagenda.R;

public class Meio_Anotacao extends AppCompatActivity {
private Anotacao anotacao;
private TextView titulo;
private TextView descricao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meio__anotacao);
        anotacao =(Anotacao) getIntent().getSerializableExtra("anotacaoselecionada");
        titulo = findViewById(R.id.textView2);
        descricao=findViewById(R.id.textView4);
        titulo.setText(anotacao.getTitulo());
        descricao.setText(anotacao.getDescricao());
        titulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(Meio_Anotacao.this, AdicionarAnotacao.class);
                intent.putExtra("anotacaoselecionada", anotacao);
                startActivity(intent);
                finish();
            }
        });
        descricao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(Meio_Anotacao.this, AdicionarAnotacao.class);
                intent.putExtra("anotacaoselecionada", anotacao);
                startActivity(intent);
                finish();
            }
        });

    }


}
