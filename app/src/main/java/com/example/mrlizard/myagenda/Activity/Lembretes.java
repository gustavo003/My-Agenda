package com.example.mrlizard.myagenda.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.mrlizard.myagenda.Adapters.Adapter_lembrete;
import com.example.mrlizard.myagenda.Helper.LembreteDao;
import com.example.mrlizard.myagenda.Helper.RecyclerItemClickListener;
import com.example.mrlizard.myagenda.Model.Lembrete;
import com.example.mrlizard.myagenda.R;

import java.util.List;

public class Lembretes extends AppCompatActivity {
private List<Lembrete> lembretes;
private RecyclerView rec;
private Adapter_lembrete adapter;
private Lembrete lembrete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lembretes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rec= findViewById(R.id.recyclerView3);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        rec.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), rec, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Lembrete lembrete = lembretes.get(position);
                Intent intent = new Intent(Lembretes.this, Meio_Lembrete.class);
                intent.putExtra("lembreteselecionado", lembrete);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {
     lembrete = lembretes.get(position);
                AlertDialog.Builder dialog = new AlertDialog.Builder(Lembretes.this);
                dialog.setTitle("Confirmar exclusão");
                dialog.setMessage("Deseja realmente excluir o lembrete: " + lembrete.getTitulo() + "?");
                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        LembreteDao lembreteDao = new LembreteDao(getApplicationContext());
                        lembreteDao.deletar(lembrete);
                        listar();
                        Toast.makeText(getApplicationContext(), "Lembrete deletado com sucesso!", Toast.LENGTH_LONG).show();

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
                startActivity(new Intent(getApplicationContext(), AdicionarLembrete.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        listar();
    }

    public void listar(){
LembreteDao lembreteDao = new LembreteDao(getApplicationContext());
lembretes = lembreteDao.listar();
adapter = new Adapter_lembrete(lembretes);
RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
rec.setLayoutManager(layoutManager);
rec.setHasFixedSize(true);
rec.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
rec.setAdapter(adapter);



    }

}
