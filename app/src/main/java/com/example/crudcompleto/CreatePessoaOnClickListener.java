package com.example.crudcompleto;
/*
Author: Biote Ngovene
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreatePessoaOnClickListener implements View.OnClickListener{

    @Override
    public void onClick(View v) {

        final Context context = v.getContext();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.activity_form, null, false);

        final EditText editTextPessoaNome = (EditText) formElementsView.findViewById(R.id.editTextPessoaNome);
        final EditText editTextPessoaEmail = (EditText) formElementsView.findViewById(R.id.editTextPessoaEmail);

        new AlertDialog.Builder(context).setView(formElementsView).setTitle("Criar Pessoa").setPositiveButton("Incluir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

                //Regra do negocio para incluir pessoas
                String pessoaNome = editTextPessoaNome.getText().toString();
                String pessoaEmail = editTextPessoaEmail.getText().toString();

                Pessoa pessoa = new Pessoa();
                pessoa.setNome(pessoaNome);
                pessoa.setEmail(pessoaEmail);

                boolean criadoComSucesso = new PessoaController(context).create(pessoa);

                if(criadoComSucesso){
                    Toast.makeText(context, "Pessoa incluida com sucesso.", Toast.LENGTH_SHORT).show();

                    ((MainActivity) context).contadorDeRegistros();
                    ((MainActivity) context).atualizarListaDePessoas();

                }else{
                    Toast.makeText(context, "Nao foi possuir criar.",Toast.LENGTH_SHORT).show();
                }

                dialog.cancel();
            }
        }).show();

    }
}
