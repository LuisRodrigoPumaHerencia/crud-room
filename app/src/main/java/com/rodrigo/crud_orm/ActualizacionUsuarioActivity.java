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

public class ActualizacionUsuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizacion_usuario);

        //OBTENIENDO COMPONENTES
        TextInputEditText input_nombre = findViewById(R.id.input_nombre);
        TextInputEditText input_usuario = findViewById(R.id.input_usuario);
        TextInputEditText input_contrasena1 = findViewById(R.id.input_contrasena1);
        TextInputEditText input_contrasena2 = findViewById(R.id.input_contrasena2);

        MaterialButton btn_actualizar = findViewById(R.id.btn_actualizar_usuario);

        Intent intent2 = getIntent();
        input_nombre.setText(intent2.getExtras().getString("nombre"));
        input_usuario.setText(intent2.getExtras().getString("usuario"));
        input_contrasena1.setText(intent2.getExtras().getString("contrasena"));
        input_contrasena2.setText(intent2.getExtras().getString("contrasena"));

        AppDatabase bd = AppDatabase.getInstance(this.getApplicationContext());
        Usuario usuario = bd.usuarioDAO().obtenerUsuarioPorNombre(intent2.getExtras().getString("nombre"));

        btn_actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( String.valueOf(input_contrasena1.getText()).equals(String.valueOf(input_contrasena2.getText()))){
                    usuario.setNombre(String.valueOf(input_nombre.getText()));
                    usuario.setUsuario(String.valueOf(input_usuario.getText()));
                    usuario.setContrasena(String.valueOf(input_contrasena1.getText()));
                    actualizarUsuario(usuario);
                }else {
                    Toast.makeText(ActualizacionUsuarioActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void actualizarUsuario(Usuario usuario){
        AppDatabase bd = AppDatabase.getInstance(this.getApplicationContext());
        bd.usuarioDAO().actualizar(usuario);
        Toast.makeText(ActualizacionUsuarioActivity.this, "Usuario Actualizado", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ActualizacionUsuarioActivity.this, MainActivity.class);
        startActivity(intent);
    }
}