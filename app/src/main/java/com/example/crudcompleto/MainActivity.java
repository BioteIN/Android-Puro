package com.example.crudcompleto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnCriarPessoa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        //Teste
//        Pessoa pessoa = new Pessoa();
//        PessoaController controller = new PessoaController(getApplicationContext());
//
//        pessoa.setNome("Bia");
//        pessoa.setEmail("bioten11@gmail.com");
//
//        controller.create(pessoa);

        btnCriarPessoa = (Button) findViewById(R.id.btnCriarPessoa);
        btnCriarPessoa.setOnClickListener(new CreatePessoaOnClickListener());

        contadorDeRegistros();
        atualizarListaDePessoas();
        new PessoaController(this).buscarPeloID(10);

    }

    public void contadorDeRegistros(){
        String msg ="";

        int contador = new PessoaController(this).totalDePessoas();

        TextView txtContadorPessoas = (TextView) findViewById(R.id.txtContadorPessoas);

        if(contador == 0){

            msg = "Nenhuma pessoa cadastrada.";

        }else if(contador == 1){

            msg = contador + " pessoa cadastrada.";

        }else{

            msg = contador + " pessoas cadastradas.";

        }
        txtContadorPessoas.setText(msg);
    }

    public void atualizarListaDePessoas(){

        LinearLayout linearLayoutRecords = (LinearLayout) findViewById(R.id.objectosPessoas);
        linearLayoutRecords.removeAllViews();

        List<Pessoa> pessoas = new PessoaController(this).listarPessoas();

        if (pessoas.size()>=0){

            for (Pessoa obj : pessoas){

                int id = obj.getId();
                String nome = obj.getNome();
                String email = obj.getEmail();

                String txtViewPessoas = nome + " - " + email;

                TextView textViewPessoaItem = new TextView(this);
                textViewPessoaItem.setPadding(0, 10, 0, 10);
                textViewPessoaItem.setText(txtViewPessoas);
                textViewPessoaItem.setTag(Integer.toString(id));

                linearLayoutRecords.addView(textViewPessoaItem);
                textViewPessoaItem.setOnLongClickListener(new RetrieveOnLongClickListener());


            }
        } else {

        }

    }
}
