package com.example.mrlizard.myagenda.Activity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mrlizard.myagenda.Adapters.Adpter_anotacao;
import com.example.mrlizard.myagenda.Helper.AnotaDao;
import com.example.mrlizard.myagenda.Helper.DBHelper;
import com.example.mrlizard.myagenda.Helper.RecyclerItemClickListener;
import com.example.mrlizard.myagenda.Model.Anotacao;
import com.example.mrlizard.myagenda.R;

import java.util.List;

public class Anotacoes extends AppCompatActivity {
private Adpter_anotacao adapter;
private RecyclerView recyclerView;
private List<Anotacao> anotacaos;
private Anotacao anot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anotacoes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
recyclerView = findViewById(R.id.recyclerView);
recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView,
        new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
Anotacao anotacao = anotacaos.get(position);
Intent intent  = new Intent(Anotacoes.this, Meio_Anotacao.class);
intent.putExtra("anotacaoselecionada", anotacao);
startActivity(intent);

            }

            @Override
            public void onLongItemClick(View view, int position) {

            anot=anotacaos.get(position);

                AlertDialog.Builder dialog = new AlertDialog.Builder(Anotacoes.this);

                dialog.setTitle("Confirmar exclusão");
                dialog.setMessage("Deseja realmente deletar a anotação: '" + anot.getTitulo() + "'?");
                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AnotaDao anotaDao = new AnotaDao(getApplicationContext());
                        anotaDao.deletar(anot);
                        listar();
                        Toast.makeText(getApplicationContext(), "Anotação deletada com sucesso!", Toast.LENGTH_LONG).show();
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
                startActivity(new Intent(getApplicationContext(), AdicionarAnotacao.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        listar();
    }

    public void listar(){
        AnotaDao anotaDao= new AnotaDao(getApplicationContext());
        anotacaos = anotaDao.listar();
        adapter = new Adpter_anotacao(anotacaos);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);


    }

}
