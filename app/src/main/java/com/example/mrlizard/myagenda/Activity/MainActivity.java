package com.example.mrlizard.myagenda.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.IconMarginSpan;
import android.view.View;
import android.widget.ImageView;

import com.example.mrlizard.myagenda.R;

public class MainActivity extends AppCompatActivity {
private ImageView imagem;
private ImageView imagem2;
private ImageView imagem3;
private ImageView imagem4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imagem = (ImageView) findViewById(R.id.imageView);
        imagem2 = (ImageView) findViewById(R.id.imageView2);
        imagem3 = findViewById(R.id.imageView3);
        imagem4 = findViewById(R.id.eventor);

        imagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Anotacoes.class));
            }
        });
        imagem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Tarefas.class));
            }
        });
        imagem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Lembretes.class));
            }
        });
        imagem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent (getApplicationContext(), Eventos.class));
            }
        });
    }
}
