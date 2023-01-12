package com.example.appprojetocurso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.QuickContactBadge;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MinhaContaActivity extends AppCompatActivity {

    private ViewHolder mViewHolder = new ViewHolder();
    private Intent i;
    private DBHelper db;
    private List<Utilizador> listaUtilizadores;
    private ArrayAdapter<Utilizador> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minha_conta);

        listaUtilizadores = new ArrayList<>();

        i = getIntent();
        int id = i.getExtras().getInt("id");

        db = new DBHelper(this);

        mViewHolder.et_id = findViewById(R.id.et_minhaconta_id);
        mViewHolder.et_username = findViewById(R.id.et_minhaconta_username);
        mViewHolder.et_password = findViewById(R.id.et_minhaconta_password);
        mViewHolder.bt_editar = findViewById(R.id.bt_minhaconta_editar);
        mViewHolder.bt_remover = findViewById(R.id.bt_minhaconta_eliminar);
        mViewHolder.lv = findViewById(R.id.lv);

        mViewHolder.bt_remover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
        
        mViewHolder.bt_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        mViewHolder.lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                mViewHolder.et_id.setText(String.valueOf(listaUtilizadores.get(position).getId()));
                mViewHolder.et_username.setText(listaUtilizadores.get(position).getUsername());
                mViewHolder.et_password.setText(listaUtilizadores.get(position).getPassword());
            }
        });


        PreencherDadosUtilizador(id);
        PreencherListaUtilizadores();
    }

    private void PreencherListaUtilizadores() {
       listaUtilizadores =  db.Utilizador_SelectAll();
       adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaUtilizadores);
       mViewHolder.lv.setAdapter(adapter);

    }

    private void PreencherDadosUtilizador(int id) {

        Utilizador u = db.Utilizador_SelectById(id);
        mViewHolder.et_id.setText(String.valueOf(u.id));
        mViewHolder.et_username.setText(u.getUsername());
        mViewHolder.et_password.setText(u.getPassword());
    }

    private class ViewHolder{
        EditText et_id, et_username, et_password;
        Button bt_editar, bt_remover;
        ListView lv;
    }
}