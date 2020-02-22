package com.example.mrlizard.myagenda.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mrlizard.myagenda.Model.Lembrete;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrlizard on 10/11/19.
 */

public class LembreteDao implements LembreteDaoIn {

    private SQLiteDatabase escrever;
    private SQLiteDatabase ler;
    private DBHelper db;


    public LembreteDao(Context context) {
        db = new DBHelper(context);
        escrever = db.getWritableDatabase();
        ler = db.getReadableDatabase();
    }

    @Override
    public void salvar(Lembrete objeto) {
        ContentValues cv = new ContentValues();
        cv.put("Titulo", objeto.getTitulo());
        cv.put("Descricao", objeto.getDescricao());
        cv.put("Data", objeto.getData());
        cv.put("Hora", objeto.getHora());
        try {
            escrever.insert(DBHelper.TABELA_LEMBRETES, null, cv);
            Log.i("Info","Sucesso ao inserie na tabela!");
        }catch (Exception e){
            Log.i("Info", e.getMessage());
        }


    }

    @Override
    public void atualizar(Lembrete objeto) {
        ContentValues cv = new ContentValues();
        cv.put("Titulo", objeto.getTitulo());
        cv.put("Descricao", objeto.getDescricao());
        cv.put("Data", objeto.getData());
        cv.put("Hora", objeto.getHora());
        try {
            String [] args = {objeto.getId().toString()};
            escrever.update(DBHelper.TABELA_LEMBRETES, cv, "IDLembrete=?", args);
            Log.i("Info", "Sucesso ao atualizar a tabela");
        }catch (Exception e){
            Log.i("Info", e.getMessage());
        }
    }

    @Override
    public void deletar(Lembrete objeto) {
        try {
            String [] args = {objeto.getId().toString()};
            escrever.delete(DBHelper.TABELA_LEMBRETES, "IDLembrete=?", args);
            Log.i("Info", "Sucesso ao deletar da tabela!");
        }catch(Exception e){
            Log.i("Info", "Erro: " + e.getMessage());
        }
    }

    @Override
    public List<Lembrete> listar() {
        List<Lembrete> lembretes = new ArrayList<>();
        try{
            Cursor c = ler.rawQuery("SELECT * FROM " + DBHelper.TABELA_LEMBRETES, null);
            while (c.moveToNext()){
                Lembrete lembrete = new Lembrete();
                Long id = c.getLong(c.getColumnIndex("IDLembrete"));
                String titulo = c.getString(c.getColumnIndex("Titulo"));
                String desc = c.getString(c.getColumnIndex("Descricao"));
                String data = c.getString(c.getColumnIndex("Data"));
                String hora = c.getString(c.getColumnIndex("Hora"));
                lembrete.setHora(hora);
                lembrete.setTitulo(titulo);
                lembrete.setId(id);
                lembrete.setData(data);
                lembrete.setDescricao(desc);
                lembretes.add(lembrete);
            }

        }catch (Exception e){
            Log.i("Info", e.getMessage());
        }
        return lembretes;
    }
}
