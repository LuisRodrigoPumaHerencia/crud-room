package com.rodrigo.crud_orm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.rodrigo.crud_orm.database.AppDatabase;
import com.rodrigo.crud_orm.database.Usuario;

import java.util.List;

public class ListaUsuariosActivity extends AppCompatActivity {

    ListaUsuariosAdaptador listaUsuariosAdaptador = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);

        //OBTENIENDO COMPONENTES
        MaterialButton btn_nuevo_usuario = findViewById(R.id.btn_new_user);

        btn_nuevo_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaUsuariosActivity.this, RegistroUsuarioActivity.class);
                startActivity(intent);
            }
        });
        initReciclerView();
        listarUsuarios();


    }

    public void initReciclerView(){
        RecyclerView recyclerView = findViewById(R.id.recicler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL);
        recyclerView.addItemDecoration(itemDecoration);
        listaUsuariosAdaptador = new ListaUsuariosAdaptador(this);
        recyclerView.setAdapter(listaUsuariosAdaptador);
    }

    public void listarUsuarios(){
        AppDatabase bd = AppDatabase.getInstance(this.getApplicationContext());
        List<Usuario> usuarios = bd.usuarioDAO().getUsuarios();
        listaUsuariosAdaptador.setUsuarioLista(usuarios);
    }
}