package com.example.appprojetocurso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastrarActivity extends AppCompatActivity {

    private ViewHolder mViewHolder = new ViewHolder();
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        mViewHolder.et_username = findViewById(R.id.et_cad_username);
        mViewHolder.et_password1 = findViewById(R.id.et_cad_password);
        mViewHolder.et_password2 = findViewById(R.id.et_cad_password2);
        mViewHolder.bt_cadastrar = findViewById(R.id.bt_cad_cadastrar);

        db = new DBHelper(this);

        mViewHolder.bt_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = mViewHolder.et_username.getText().toString().trim();
                String password = mViewHolder.et_password1.getText().toString().trim();
                String password2 = mViewHolder.et_password2.getText().toString().trim();

                if (!username.isEmpty()){
                    if (!password.isEmpty()){
                        if (!password2.isEmpty()){

                            long res = db.Utilizador_Insert(username, password);
                            if (password.equals(password2) && res > 0){


                                Intent i = getIntent();
                                i.putExtra("username", username);
                                //i.putExtra("password", password);
                                setResult(1, i);
                                finish();

                                Toast.makeText(CadastrarActivity.this,
                                        "Registrado com sucesso!",
                                        Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(CadastrarActivity.this,
                                        "Senhas nao compat??veis",
                                        Toast.LENGTH_SHORT).show();
                            }

                        }else{
                            Toast.makeText(CadastrarActivity.this,
                                    "Digite repita senha",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(CadastrarActivity.this,
                                "Digite a senha",
                                Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(CadastrarActivity.this,
                            "Digite o username",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private class ViewHolder{
        EditText et_username, et_password1, et_password2;
        Button bt_cadastrar;
    }
}