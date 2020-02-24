package com.example.crudcompleto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class PessoaController extends DataBaseAdapter{

    public PessoaController(Context context){
        super(context);
    }

    public boolean create(Pessoa pessoa){

        ContentValues values = new ContentValues();

        values.put("nome", pessoa.getNome());
        values.put("email", pessoa.getEmail());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean isCreate = db.insert("pessoa", null, values) > 0;
        db.close();

        return isCreate;
    }

    public int totalDePessoas(){
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM pessoa";

        int contador = db.rawQuery(sql, null).getCount();

        return contador;
    }

    public List<Pessoa> listarPessoas(){

        List<Pessoa> pessoas = new ArrayList<>();

        String sql = "SELECT * FROM pessoa ORDER by id DESC";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                String nome = cursor.getString(cursor.getColumnIndex("nome"));
                String email = cursor.getString(cursor.getColumnIndex("email"));

                Pessoa pessoa = new Pessoa();

                pessoa.setId(id);
                pessoa.setNome(nome);
                pessoa.setEmail(email);

                pessoas.add(pessoa);

            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return pessoas;
    }

    public Pessoa buscarPeloID(int pessoaID){

        Pessoa pessoa = null;

        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM pessoa WHERE id = "+pessoaID;

        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){

            String nome=cursor.getString(cursor.getColumnIndex("nome"));
            String email=cursor.getString(cursor.getColumnIndex("email"));

            pessoa = new Pessoa();

            pessoa.setId(pessoaID);
            pessoa.setNome(nome);
            pessoa.setEmail(email);

        }

        return pessoa;
     }

    public boolean update(Pessoa pessoa){

        ContentValues values = new ContentValues();

        values.put("nome", pessoa.getNome());
        values.put("email", pessoa.getEmail());

        String where = "id = ?";

        String[] whereArgs = { Integer.toString(pessoa.getId())};

        SQLiteDatabase db = this.getWritableDatabase();

        boolean isUpdate = db.update("pessoa", values, where, whereArgs) > 0;

        db.close();

        return isUpdate;
    }

    public boolean delete(int pessoaID){

        boolean isDeletado = false;

        SQLiteDatabase db = this.getWritableDatabase();
        isDeletado = db.delete("pessoa", "id ='" + pessoaID +"'", null) > 0;
        db.close();

        return isDeletado;
    }
}

