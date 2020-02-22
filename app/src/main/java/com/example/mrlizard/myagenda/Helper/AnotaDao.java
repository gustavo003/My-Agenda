package com.example.mrlizard.myagenda.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mrlizard.myagenda.Model.Anotacao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrlizard on 07/11/19.
 */

public class AnotaDao implements AnotaDaoIn {
private SQLiteDatabase escrever;
private SQLiteDatabase ler;
private DBHelper db;


    public AnotaDao(Context context) {
        db  =new DBHelper(context);
        escrever = db.getWritableDatabase();
        ler = db.getReadableDatabase();
    }

    @Override
    public void salvar(Anotacao objeto) {
        ContentValues cv = new ContentValues();
        cv.put("Titulo", objeto.getTitulo());
        cv.put("Descricao", objeto.getDescricao());
        try {
            escrever.insert(DBHelper.TABELA_ANOTACAO, null, cv);
            Log.i("Info", "Sucesso ao inserir na tabela");
        }catch(Exception e){
            Log.i("Info", "Erro: " + e.getMessage());
        }


    }

    @Override
    public void deletar(Anotacao objeto) {
try{
    String [] args = {objeto.getId().toString()};
    escrever.delete(DBHelper.TABELA_ANOTACAO, "IDAnotacao=?", args);
    Log.i("Info", "Sucesso ao deletar da tabela!");
}catch(Exception e){
    Log.i("Info", e.getMessage());
}


    }

    @Override
    public void atualizar(Anotacao objeto) {
        ContentValues cv = new ContentValues();
        cv.put("Titulo", objeto.getTitulo());
        cv.put("Descricao", objeto.getDescricao());
        try {
            String []args= {objeto.getId().toString()};

            escrever.update(DBHelper.TABELA_ANOTACAO, cv, "IDAnotacao=?", args);
            Log.i("Info", "Sucesso ao atualizar tabela");
        }catch(Exception e){
            Log.i("Info", e.getMessage());
        }
    }
    @Override
    public List<Anotacao> listar() {
        List<Anotacao> anota = new ArrayList<>();
        try {
            Cursor c = ler.rawQuery("SELECT * FROM " + DBHelper.TABELA_ANOTACAO, null);
            while (c.moveToNext()) {
                Anotacao anotacao = new Anotacao();
                Long id = c.getLong(c.getColumnIndex("IDAnotacao"));
                String titulo = c.getString(c.getColumnIndex("Titulo"));
                String descricao = c.getString(c.getColumnIndex("Descricao"));
                anotacao.setId(id);
                anotacao.setTitulo(titulo);
                anotacao.setDescricao(descricao);
                anota.add(anotacao);
            }
        }catch(Exception e){
            Log.i("Info", "Erro: " +e.getMessage());
        }

        return anota;
    }
}
