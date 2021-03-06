package com.example.crudcompleto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseAdapter extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    protected static final String DATABASE_NAME = "CrudCompleto.DB";

    public DataBaseAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE pessoa " + "( id INTEGER PRIMARY KEY AUTOINCREMENT," + " nome TEXT," + " email TEXT )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        //Observação
        /*Metodo Responsavel por criar os procedimentos
        de backup na base de dados
         */
        String sql = "DROP TABLE IF EXISTS pessoa";
        db.execSQL(sql);
        onCreate(db);
    }
}
