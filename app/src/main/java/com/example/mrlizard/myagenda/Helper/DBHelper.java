package com.example.mrlizard.myagenda.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by mrlizard on 07/11/19.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String NOME_DB = "MyAgenda";
    public static String TABELA_TAREFAS = "tarefas";
    public static String TABELA_LEMBRETES = "lembretes";
    public static String TABELA_EVENTO = "evento";
    public static String TABELA_ANOTACAO = "anotacoes";

    public DBHelper(Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA_TAREFAS
                + " (IDTarefa  INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " Titulo VARCHAR(20) NOT NULL , Descricao TEXT, Data TEXT)";


        try {
            sqLiteDatabase.execSQL( sql );
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABELA_ANOTACAO + "(IDAnotacao INTEGER PRIMARY KEY AUTOINCREMENT, Titulo VARCHAR(20) NOT NULL, Descricao TEXT)");
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABELA_LEMBRETES + "(IDLembrete INTEGER PRIMARY KEY AUTOINCREMENT, Titulo VARCHAR(20) NOT NULL , Descricao TEXT, Hora TEXT, Data TEXT)");
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABELA_EVENTO + " (IDEvento  INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " Titulo VARCHAR(20) NOT NULL , Descricao TEXT, Hora TEXT, Data TEXT, Endereco TEXT, Contato TEXT)");
            Log.i("INFO DB", "Sucesso ao criar a tabela" );
        }catch (Exception e){
            Log.i("INFO DB", "Erro ao criar a tabela " + e.getMessage() );
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
