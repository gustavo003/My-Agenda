package com.example.mrlizard.myagenda.Activity;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.mrlizard.myagenda.Helper.TarefaDao;
import com.example.mrlizard.myagenda.Model.Tarefa;
import com.example.mrlizard.myagenda.R;

import java.util.Calendar;

public class AdicionarTarefa extends AppCompatActivity {
private TextInputEditText titulo;
private TextInputEditText desc;
private CalendarView calendar;
private Tarefa tarefas;
private Tarefa tarefa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);
        titulo = findViewById(R.id.titull);
        desc = findViewById(R.id.desc);
        calendar=findViewById(R.id.calend);
        tarefas=(Tarefa)getIntent().getSerializableExtra("tarefaselecionada");
        try{
        if (tarefas!=null) {
            titulo.setText(tarefas.getTitulo());
            desc.setText(tarefas.getDescricao());
            String date = tarefas.getData();
            String[] parts = date.split("/");
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            month--;
            int year = Integer.parseInt(parts[2]);
            Calendar calendary = Calendar.getInstance();
            calendary.set(Calendar.YEAR, year);
            calendary.set(Calendar.MONTH, month);
            calendary.set(Calendar.DAY_OF_MONTH, day);
            long milliTime = calendary.getTimeInMillis();
            calendar.setDate(milliTime, true, true);

        }
        }catch(Exception e){
            Log.i("Info", e.getMessage());
        }
            tarefa = new Tarefa();
            tarefa.setData("");

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                  tarefa.setData(i2 + "/" + (i1+1) + "/" + i);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        TarefaDao tarefaDao = new TarefaDao(getApplicationContext());

        if(item.getItemId()==R.id.salvarTaf) {
            if(!titulo.getText().toString().isEmpty()&& !titulo.getText().toString().equals("") && titulo.getText().toString().length() < 21) {
                tarefa.setTitulo(titulo.getText().toString());
                tarefa.setDescricao(desc.getText().toString());
                if (tarefas != null) {
                    tarefa.setId(tarefas.getId());
                    tarefaDao.atualizar(tarefa);
                    Toast.makeText(getApplicationContext(), "Tarefa atualizada com sucesso!", Toast.LENGTH_LONG).show();

                } else {
                    tarefaDao.salvar(tarefa);
                    Toast.makeText(getApplicationContext(), "Tarefa salva com sucesso!", Toast.LENGTH_LONG).show();

                }
                finish();
            }
            else{
                Toast.makeText(getApplicationContext(), "Não é possivél salvar tarefa sem um título apropriado", Toast.LENGTH_LONG).show();
            }

        }
        return super.onOptionsItemSelected(item);
    }
}
