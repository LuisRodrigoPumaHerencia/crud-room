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

public class RegistroUsuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        //OBTENIENDO COMPONENTES
        TextInputEditText input_nombre = findViewById(R.id.input_nombre);
        TextInputEditText input_usuario = findViewById(R.id.input_usuario);
        TextInputEditText input_contrasena1 = findViewById(R.id.input_contrasena1);
        TextInputEditText input_contrasena2 = findViewById(R.id.input_contrasena2);

        MaterialButton btn_registrar = findViewById(R.id.btn_registrar_usuario);

        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( String.valueOf(input_contrasena1.getText()).equals(String.valueOf(input_contrasena2.getText()))){
                    registrarUsuario(String.valueOf(input_nombre.getText()), String.valueOf(input_usuario.getText()), String.valueOf(input_contrasena1.getText()));
                }else{
                    Toast.makeText(RegistroUsuarioActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void registrarUsuario(String nombre, String usuario, String contrasena){
        Usuario obj_usuario = new Usuario(nombre, usuario, contrasena);
        AppDatabase bd = AppDatabase.getInstance(this.getApplicationContext());
        bd.usuarioDAO().insertar(obj_usuario);
        Toast.makeText(this, "Usuario Registrado", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(RegistroUsuarioActivity.this, MainActivity.class);
        startActivity(i);
    }
}








