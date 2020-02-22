package com.example.mrlizard.myagenda.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mrlizard.myagenda.Adapters.Adapter_evento;
import com.example.mrlizard.myagenda.Helper.EventoDao;
import com.example.mrlizard.myagenda.Helper.RecyclerItemClickListener;
import com.example.mrlizard.myagenda.Model.Evento;
import com.example.mrlizard.myagenda.R;

import java.util.ArrayList;
import java.util.List;

public class Eventos extends AppCompatActivity {
private List<Evento> eventos;
private RecyclerView rec;
private Adapter_evento adapter;
private Evento evento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
rec = findViewById(R.id.rec4);
        rec.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), rec, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Evento evento = eventos.get(position);
                Intent intent = new Intent(Eventos.this, Meio_evento.class);
                intent.putExtra("eventoselecionado", evento);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {
            evento= eventos.get(position);
                AlertDialog.Builder dialog = new AlertDialog.Builder(Eventos.this);
                dialog.setTitle("Confirmar exclusão");
                dialog.setMessage("Deseja realmente excluir o evento: " + evento.getTitulo() + "?");
                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EventoDao eventoDao = new EventoDao(getApplicationContext());
                        eventoDao.deletar(evento);
                        listar();
                        Toast.makeText(getApplicationContext(), "Sucesso ao deletar evento!", Toast.LENGTH_LONG).show();
                    }
                });
                dialog.setNegativeButton("Não", null);
                dialog.create();
                dialog.show();
            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        }));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AdicionarEvento.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        listar();
    }

    public void listar(){
    EventoDao eventoDao = new EventoDao(getApplicationContext());
    eventos = eventoDao.listar();
    adapter = new Adapter_evento(eventos);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
    rec.setLayoutManager(layoutManager);rec.setHasFixedSize(true);
    rec.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
    rec.setAdapter(adapter);
}


}
