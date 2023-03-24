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

public class EliminacionUsuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminacion_usuario);

        //OBTENIENDO COMPONENTES
        TextInputEditText input_nombre = findViewById(R.id.input_nombre);
        TextInputEditText input_usuario = findViewById(R.id.input_usuario);
        TextInputEditText input_contrasena1 = findViewById(R.id.input_contrasena1);

        MaterialButton btn_eliminar = findViewById(R.id.btn_eliminar_usuario);

        Intent intent2 = getIntent();
        input_nombre.setText(intent2.getExtras().getString("nombre"));
        input_usuario.setText(intent2.getExtras().getString("usuario"));
        input_contrasena1.setText(intent2.getExtras().getString("contrasena"));

        AppDatabase bd = AppDatabase.getInstance(this.getApplicationContext());
        Usuario usuario = bd.usuarioDAO().obtenerUsuarioPorNombre(intent2.getExtras().getString("nombre"));

        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarUsuario(usuario);
            }
        });

    }
    public void eliminarUsuario (Usuario usuario){
        AppDatabase bd = AppDatabase.getInstance(this.getApplicationContext());
        bd.usuarioDAO().borrar(usuario);
        Toast.makeText(EliminacionUsuarioActivity.this, "Usuario Eliminado", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(EliminacionUsuarioActivity.this, MainActivity.class);
        startActivity(intent);
    }
}