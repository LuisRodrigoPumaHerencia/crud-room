package com.rodrigo.crud_orm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.rodrigo.crud_orm.database.AppDatabase;
import com.rodrigo.crud_orm.database.Usuario;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //OBTENIENDO COMPONENTES
        TextInputEditText input_usuario = findViewById(R.id.input_usuario);
        TextInputEditText input_contrasena = findViewById(R.id.input_contrasena);
        MaterialButton btn_inicio_sesion = findViewById(R.id.btn_inicio_sesion);
        MaterialButton btn_registro_usuario = findViewById(R.id.btn_registro_usuario);

        btn_inicio_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarUsuario(String.valueOf(input_usuario.getText()), String.valueOf(input_contrasena.getText()));
            }
        });

        btn_registro_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, RegistroUsuarioActivity.class);
                startActivity(i);
            }
        });

    }

    public void validarUsuario(String usuario, String contrasena){
        AppDatabase bd = AppDatabase.getInstance(this.getApplicationContext());
        Usuario obj_usuario = bd.usuarioDAO().iniciarSesion(usuario, contrasena);
        if(obj_usuario == null){
            Toast.makeText(this, "Credenciales Incorrectas", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(MainActivity.this, ListaUsuariosActivity.class);
            startActivity(intent);
        }
    }
}











