package com.example.mrlizard.myagenda.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mrlizard.myagenda.Helper.LembreteDao;
import com.example.mrlizard.myagenda.Model.Lembrete;
import com.example.mrlizard.myagenda.R;

import java.util.Calendar;

public class AdicionarLembrete extends AppCompatActivity {
    private TextInputEditText titulo;
    private TextInputEditText desc;
    private CalendarView calendarView;
    private SeekBar seek;
    private Lembrete lembrete;
    private SeekBar minutos;
    private TextView hora;
    private TextView minuto;
    private String min;
    private String hour;
    private Lembrete lembretes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_lembrete);
        titulo = findViewById(R.id.titul3);
        desc = findViewById(R.id.desc3);
        calendarView = findViewById(R.id.calendarView);
        seek = findViewById(R.id.seekBar);
        minutos = findViewById(R.id.seekBar2);
        hora = findViewById(R.id.hora);
        minuto = findViewById(R.id.minuto);
        lembrete = new Lembrete();
        lembrete.setData("");
        lembrete.setHora("");
        min = "";
        hour = "";
        lembretes = (Lembrete) getIntent().getSerializableExtra("lembreteselecionado");

        try{
            if (lembretes!=null){
                titulo.setText(lembretes.getTitulo());
                desc.setText(lembretes.getDescricao());
                String date = lembretes.getData();
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
                calendarView.setDate(milliTime, true, true);
                String hora2 = lembretes.getHora();
                String[] partes = hora2.split(":");
                int hour2 = Integer.parseInt(partes[0]);
                seek.setProgress(hour2);
                hour2 = Integer.parseInt(partes[1]);
                minutos.setProgress(hour2);
                hour = partes[0];
                min=partes[1];
                lembrete.setData(lembretes.getData());
                hora.setText(partes[0] + " horas");
                minuto.setText(partes[1] + " minutos");

            }

        }catch(Exception e){
            Log.i("Info", e.getMessage());
        }

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                lembrete.setData(i2 + "/" + (i1 + 1) + "/" + i);
            }
        });
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                hora.setText(seek.getProgress() + " horas");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                hour = Integer.toString(seek.getProgress());
            }
        });
        minutos.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                minuto.setText(minutos.getProgress() + " minutos");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                min = Integer.toString(minutos.getProgress());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu3, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.salvar3){
            if (!titulo.getText().toString().isEmpty()&& !titulo.getText().toString().equals("") && titulo.getText().toString().length() < 21) {
                LembreteDao lembreteDao = new LembreteDao(getApplicationContext());
                lembrete.setTitulo(titulo.getText().toString());
                lembrete.setDescricao(desc.getText().toString());

                if (!hour.equals("")) {
                    if (min.equals("")) {
                        min = "00";
                    }
                    String total = hour + ":" + min;
                    lembrete.setHora(total);
                    Log.i("Info", "Hora: " + lembrete.getHora());
                }
                if (hour.equals("") && !min.equals("")) {
                    Toast.makeText(getApplicationContext(), "Não é possível salvar a hora deste modo, o lembrete ficará sem hora marcada!", Toast.LENGTH_LONG);
                }
                if (lembretes != null) {
                    lembrete.setId(lembretes.getId());
                    lembreteDao.atualizar(lembrete);
                    Toast.makeText(getApplicationContext(), "Sucesso ao atualizar lembrete!", Toast.LENGTH_LONG).show();
                } else {
                    lembreteDao.salvar(lembrete);
                    Toast.makeText(getApplicationContext(), "Sucesso ao salvar lembrete!", Toast.LENGTH_LONG).show();
                }

                finish();
            }
            else{
                Toast.makeText(getApplicationContext(), "Não é possível salvar lembrete sem um título apropriado!", Toast.LENGTH_LONG).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}