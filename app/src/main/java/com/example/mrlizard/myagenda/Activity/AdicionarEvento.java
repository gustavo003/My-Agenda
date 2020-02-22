package com.example.mrlizard.myagenda.Activity;

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

import com.example.mrlizard.myagenda.Helper.EventoDao;
import com.example.mrlizard.myagenda.Model.Evento;
import com.example.mrlizard.myagenda.R;

import java.util.Calendar;

public class AdicionarEvento extends AppCompatActivity {
private TextInputEditText titulo;
private TextInputEditText desc;
private TextInputEditText contatos;
private CalendarView calendarView;
private TextView horas;
private TextView minutos;
private SeekBar hora;
private SeekBar minuto;
private Evento evento;
private String min;
private String hour;
private TextInputEditText endereco;
private Evento eventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_evento);
        titulo = findViewById(R.id.titul4);
        desc = findViewById(R.id.descricao4);
        contatos= findViewById(R.id.contatos);
        calendarView = findViewById(R.id.calendarView2);
        hora = findViewById(R.id.seekBar3);
        minuto = findViewById(R.id.seekBar4);
        horas  = findViewById(R.id.horass);
        minutos = findViewById(R.id.minutoss);
        endereco = findViewById(R.id.endereco);
        evento = new Evento();
        evento.setData("");
        evento.setHora("");
        min="";
        hour = "";

        eventos =(Evento) getIntent().getSerializableExtra("eventoselecionado");
        try{
            if (eventos!=null){
                titulo.setText(eventos.getTitulo());
                desc.setText(eventos.getDescricao());
                contatos.setText(eventos.getContatos());
                endereco.setText(eventos.getLugar());
                String date = eventos.getData();
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
                String hora2 = eventos.getHora();
                String[] partes = hora2.split(":");
                int hour2 = Integer.parseInt(partes[0]);
                hora.setProgress(hour2);
                hour2 = Integer.parseInt(partes[1]);
                minuto.setProgress(hour2);
                hour = partes[0];
                min=partes[1];
                evento.setData(eventos.getData());
                horas.setText(partes[0] + " horas");
                minutos.setText(partes[1] + " minutos");


            }
        }catch(Exception e){
            Log.i("Info", e.getMessage());
        }

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                evento.setData(i2 + "/" + (i1+1) + "/" + i);
            }
        });

        hora.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                horas.setText(hora.getProgress() + " horas");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            hour = Integer.toString(hora.getProgress());
            }
        });

        minuto.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                minutos.setText(minuto.getProgress()+ " minutos");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
        min =Integer.toString( minuto.getProgress());
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu4, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.salvar4) {
            if (!titulo.getText().toString().isEmpty() && !titulo.getText().toString().equals("") && titulo.getText().toString().length() < 21) {
                EventoDao eventoDao = new EventoDao(getApplicationContext());
                evento.setTitulo(titulo.getText().toString());
                evento.setDescricao(desc.getText().toString());
                evento.setLugar(endereco.getText().toString());
                evento.setContatos(contatos.getText().toString());
                if (!hour.equals("")) {
                    if (min.equals("")) {
                        min = "00";
                    }
                    String total = hour + ":" + min;
                    evento.setHora(total);
                }
                if (hour.equals("") && !min.equals("")) {
                    Toast.makeText(getApplicationContext(), "Não é possível salvar a hora deste modo, o evento ficará sem hora marcada!", Toast.LENGTH_LONG).show();
                }
                if (eventos != null) {
                    evento.setId(eventos.getId());
                    eventoDao.atualizar(evento);
                    Toast.makeText(getApplicationContext(), "Sucesso ao atualizar evento!", Toast.LENGTH_LONG).show();
                } else {
                    eventoDao.salvar(evento);
                    Toast.makeText(getApplicationContext(), "Sucesso ao salvar evento!", Toast.LENGTH_LONG).show();
                }
                finish();
            }
            else{
                Toast.makeText(getApplicationContext(), "Não é possível salvar o evento sem um título apropriado", Toast.LENGTH_LONG).show();
            }
        }


        return super.onOptionsItemSelected(item);
    }
}
