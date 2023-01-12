package com.example.appprojetocurso;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private ViewHolder mViewHolder = new ViewHolder();
    private String passwordCadastro;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mViewHolder.et_username = findViewById(R.id.et_login_username);
        mViewHolder.et_password = findViewById(R.id.et_login_password);
        mViewHolder.bt_entrar = findViewById(R.id.bt_login_entrar);
        mViewHolder.bt_cadastrar = findViewById(R.id.bt_login_cadastrar);

        db = new DBHelper(this);

        mViewHolder.bt_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mViewHolder.et_username.getText().toString().trim();
                String password = mViewHolder.et_password.getText().toString().trim();

                if (!username.isEmpty()){
                    if (!password.isEmpty()){

                        int res = db.Utilizador_Login(username, password);
                        if (res > 0){

                            Intent i = new Intent(LoginActivity.this, MinhaContaActivity.class);
                            i.putExtra("username", username);
                            i.putExtra("id", res);
                            startActivity(i);

                        }else{
                            Toast.makeText(LoginActivity.this,
                                    "Errado",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(LoginActivity.this,
                                "Digite a senha",
                                Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(LoginActivity.this,
                            "Digite username",
                            Toast.LENGTH_SHORT).show();
                }


            }
        });

        mViewHolder.bt_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, CadastrarActivity.class);
                startActivityForResult(i, 1 );
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == 1 && data != null){

            mViewHolder.et_username.setText(data.getExtras().getString("username"));
            //passwordCadastro = data.getExtras().getString("password");

        }
    }

    private class ViewHolder{

        EditText et_username, et_password;
        Button bt_entrar, bt_cadastrar;
    }
}