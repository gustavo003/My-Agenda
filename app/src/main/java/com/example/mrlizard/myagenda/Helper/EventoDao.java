package com.example.mrlizard.myagenda.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mrlizard.myagenda.Model.Evento;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrlizard on 10/11/19.
 */

public class EventoDao implements  EventoDaoIn {

    private SQLiteDatabase escrever;
    private SQLiteDatabase ler;
    private DBHelper db;

    public EventoDao(Context context) {
        db = new DBHelper(context);
        escrever = db.getWritableDatabase();
        ler = db.getReadableDatabase();
    }

    @Override
    public void salvar(Evento objeto) {
        ContentValues cv = new ContentValues();
        cv.put("Titulo", objeto.getTitulo());
        cv.put("Descricao", objeto.getDescricao());
        cv.put("Hora", objeto.getHora());
        cv.put("Data", objeto.getData());
        cv.put("Endereco", objeto.getLugar());
        cv.put("Contato", objeto.getContatos());
        try{
            escrever.insert(DBHelper.TABELA_EVENTO, null, cv);
            Log.i("Info", "Sucesso ao inserir na tabela");
        }catch(Exception e){
            Log.i("Info", e.getMessage());

        }

    }

    @Override
    public void atualizar(Evento objeto) {
        ContentValues cv = new ContentValues();
        cv.put("Titulo", objeto.getTitulo());
        cv.put("Descricao", objeto.getDescricao());
        cv.put("Hora", objeto.getHora());
        cv.put("Data", objeto.getData());
        cv.put("Endereco", objeto.getLugar());
        cv.put("Contato", objeto.getContatos());
        try {
            String [] args = {objeto.getId().toString()};
            escrever.update(DBHelper.TABELA_EVENTO, cv, "IDEvento=?", args);
            Log.i("Info", "Sucesso ao atualizar a tabela");
        }catch (Exception e){
            Log.i("Info", e.getMessage());
        }


    }

    @Override
    public void deletar(Evento objeto) {

        try {
            String [] args = {objeto.getId().toString()};
            escrever.delete(DBHelper.TABELA_EVENTO, "IDEvento=?", args);
            Log.i("Info", "Sucesso ao deletar da tabela!");
        }catch(Exception e){
            Log.i("Info", "Erro: " + e.getMessage());
        }

    }

    @Override
    public List<Evento> listar() {
        List<Evento> eventos = new ArrayList<>();
        try{
            Cursor c = ler.rawQuery("SELECT * FROM " + DBHelper.TABELA_EVENTO, null);
            while(c.moveToNext()){
                Log.i("info", "kkk");
                Evento evento = new Evento();
                Long id  = c.getLong(c.getColumnIndex("IDEvento"));
                String titulo = c.getString(c.getColumnIndex("Titulo"));
                String desc = c.getString(c.getColumnIndex("Descricao"));
                String hora = c.getString(c.getColumnIndex("Hora"));
                String data = c.getString(c.getColumnIndex("Data"));
                String end = c.getString(c.getColumnIndex("Endereco"));
                String contato = c.getString(c.getColumnIndex("Contato"));
                evento.setTitulo(titulo);
                evento.setId(id);
                evento.setDescricao(desc);
                evento.setHora(hora);
                evento.setData(data);
                evento.setLugar(end);
                evento.setContatos(contato);
                eventos.add(evento);
            }

        }catch(Exception e){
            Log.i("Info", e.getMessage());

        }



        return eventos;
    }
}
