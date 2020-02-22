package com.example.mrlizard.myagenda.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mrlizard.myagenda.Model.Tarefa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrlizard on 07/11/19.
 */

public class TarefaDao implements TarefaDaoIn {

    private SQLiteDatabase escrever;
    private SQLiteDatabase ler;
    private DBHelper bd;


    public TarefaDao(Context context) {
        bd = new DBHelper(context);
        escrever = bd.getWritableDatabase();
        ler = bd.getReadableDatabase();
    }

    @Override
    public void salvar(Tarefa objeto) {
        ContentValues cv = new ContentValues();
        cv.put("Titulo", objeto.getTitulo());
        cv.put("Descricao", objeto.getDescricao());
        cv.put("Data", objeto.getData());
        try {
            escrever.insert(DBHelper.TABELA_TAREFAS, null, cv);
            Log.i("Info", "Sucesso ao inserir na tabela!");
        }catch(Exception e){
            Log.i("Info", "Erro: " + e.getMessage());
        }

    }

    @Override
    public void deletar(Tarefa objeto) {

        try {
            String [] args = {objeto.getId().toString()};
            escrever.delete(DBHelper.TABELA_TAREFAS, "IDTarefa=?", args);
            Log.i("Info", "Sucesso ao deletar da tabela!");
        }catch(Exception e){
            Log.i("Info", "Erro: " + e.getMessage());
        }



    }

    @Override
    public void atualizar(Tarefa objeto) {
        ContentValues cv = new ContentValues();
        cv.put("Titulo", objeto.getTitulo());
        cv.put("Descricao", objeto.getDescricao());
        cv.put("Data", objeto.getData());
        try {
            String [] args = {objeto.getId().toString()};
        escrever.update(DBHelper.TABELA_TAREFAS, cv, "IDTarefa=?", args);
Log.i("Info", "Sucesso ao atualizar a tabela");
        }catch (Exception e){
            Log.i("Info", e.getMessage());
        }
    }
    @Override
    public List<Tarefa> listar() {

        List<Tarefa> taref = new ArrayList<>();
        try{
        Cursor c = ler.rawQuery("SELECT * FROM " + DBHelper.TABELA_TAREFAS, null);
        while(c.moveToNext()) {
            Tarefa tarefa = new Tarefa();
            Long id = c.getLong(c.getColumnIndex("IDTarefa"));
            String titulo = c.getString(c.getColumnIndex("Titulo"));
            String descricao = c.getString(c.getColumnIndex("Descricao"));
            String data = c.getString(c.getColumnIndex("Data"));
            tarefa.setId(id);
            tarefa.setTitulo(titulo);
            tarefa.setDescricao(descricao);
            tarefa.setData(data);
            taref.add(tarefa);

        }
        }catch (Exception e){
            Log.i("Info", "Erro: " + e.getMessage());

        }
        return taref;
    }
}
