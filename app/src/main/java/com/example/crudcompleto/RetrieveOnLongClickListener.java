package com.example.crudcompleto;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RetrieveOnLongClickListener implements View.OnLongClickListener {

    Context context;
    String id;

    @Override
    public boolean onLongClick(View view) {

        context = view.getContext();
        id = view.getTag().toString();

        final CharSequence[] itens = {"Editar", "Eliminar"};

        new AlertDialog.Builder(context).setTitle("Detalhes da Pessoa").setItems(itens, new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int item){

                if (item == 0){
                    //editar
                    editarPessoaPeloID(Integer.parseInt(id));
                } else if (item == 1){
                    //eliminar
                    boolean isDeletouComSucesso = new PessoaController(context).delete(Integer.parseInt(id));

                    if(isDeletouComSucesso){
                        Toast.makeText(context, "Pessoa eliminada.", Toast.LENGTH_SHORT).show();

                        ((MainActivity) context).contadorDeRegistros();
                        ((MainActivity) context).atualizarListaDePessoas();
                    } else {
                        Toast.makeText(context, "Erro ao eliminar.", Toast.LENGTH_SHORT).show();
                    }
                }

                dialog.dismiss();
            }

        }).show();
        Toast.makeText(view.getContext(), "Clicado em um item da lista", Toast.LENGTH_SHORT).show();

        return false;
    }

    public void editarPessoaPeloID(final int pessoaID){

        Toast.makeText(context, "Editando "+pessoaID, Toast.LENGTH_SHORT).show();

        //Montar formulario com dados acoplados

        //Comunicando com PessoaController
        final PessoaController pessoaController = new PessoaController(context);

        //Comunicando com a BD pela chave primaria pessoaID
        final Pessoa pessoa = pessoaController.buscarPeloID(pessoaID);

        //Injectar o layout activity_form
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View formPessoa = inflater.inflate(R.layout.activity_form, null, false);

        //Popular noome e email com dados da lista
        final EditText editTextNome = (EditText) formPessoa.findViewById(R.id.editTextPessoaNome);
        final EditText editTextEmail = (EditText) formPessoa.findViewById(R.id.editTextPessoaEmail);

        editTextNome.setText(pessoa.getNome());
        editTextEmail.setText(pessoa.getEmail());

        //Show dados populados

        new AlertDialog.Builder(context).setView(formPessoa).setTitle("Editar").setPositiveButton("Actualizar dados", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //executar o update
                Pessoa novaPessoa = new Pessoa();
                novaPessoa.setId(pessoaID);
                novaPessoa.setNome(editTextNome.getText().toString());
                novaPessoa.setEmail(editTextEmail.getText().toString());

                boolean isUpdate = pessoaController.update(novaPessoa);

                if(isUpdate){
                    Toast.makeText(context, "Dados actualizados com sucesso", Toast.LENGTH_SHORT).show();

                    ((MainActivity) context).atualizarListaDePessoas();

                }else{
                    Toast.makeText(context, "Falha ao Salvar Pessoa", Toast.LENGTH_SHORT).show();
                }

                dialog.cancel();

            }
        }).show();



    }
}