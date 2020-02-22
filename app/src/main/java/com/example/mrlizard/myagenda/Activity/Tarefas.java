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

import com.example.mrlizard.myagenda.Adapters.Adapter_tarefa;
import com.example.mrlizard.myagenda.Helper.DBHelper;
import com.example.mrlizard.myagenda.Helper.RecyclerItemClickListener;
import com.example.mrlizard.myagenda.Helper.TarefaDao;
import com.example.mrlizard.myagenda.Model.Tarefa;
import com.example.mrlizard.myagenda.R;

import java.util.List;

public class Tarefas extends AppCompatActivity {

    private Adapter_tarefa adapter;
    private RecyclerView rec;
    List<Tarefa> tarefas;
private Tarefa taref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
rec = findViewById(R.id.recyclerView2);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        rec.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), rec, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Tarefa tarefa =tarefas.get(position);
                Intent intent  = new Intent(Tarefas.this, Meio_Tarefa.class);
                intent.putExtra("tarefaselecionada", tarefa);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {
                taref = tarefas.get(position);

                    AlertDialog.Builder dialog = new AlertDialog.Builder(Tarefas.this);
                    dialog.setTitle("Confirmar a exclusão");
                    dialog.setMessage("Deseja realmente deletar a tarefa: '" + taref.getTitulo() + "'?");
                    dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            TarefaDao tarefaDao = new TarefaDao(getApplicationContext());
                            tarefaDao.deletar(taref);
                            listar();
                            Toast.makeText(getApplicationContext(), "Tarefa deletada com sucesso!", Toast.LENGTH_LONG).show();

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

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AdicionarTarefa.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        listar();
    }

    public void listar(){
        TarefaDao tarefaDao = new TarefaDao(getApplicationContext());
        tarefas = tarefaDao.listar();
        adapter = new Adapter_tarefa(tarefas);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( getApplicationContext());
        rec.setLayoutManager(layoutManager);
        rec.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        rec.setHasFixedSize(true);
        rec.setAdapter(adapter);
    }

}
