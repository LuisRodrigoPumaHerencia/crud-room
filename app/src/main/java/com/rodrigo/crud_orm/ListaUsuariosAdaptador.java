package com.rodrigo.crud_orm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.rodrigo.crud_orm.database.Usuario;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ListaUsuariosAdaptador extends RecyclerView.Adapter<ListaUsuariosAdaptador.MyViewHolder> {

    private List<Usuario> listaUsuarios;

    private Context context;

    public ListaUsuariosAdaptador(Context context){
        this.context=context;
    }

    public void setUsuarioLista(List<Usuario> lista){
        this.listaUsuarios=lista;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recicler_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.nombre.setText(this.listaUsuarios.get(position).getNombre());
        holder.usuario.setText(this.listaUsuarios.get(position).getUsuario());
        holder.contrasena.setText(this.listaUsuarios.get(position).getContrasena());
        holder.btn_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActualizacionUsuarioActivity.class);
                intent.putExtra("nombre",listaUsuarios.get(position).getNombre());
                intent.putExtra("usuario",listaUsuarios.get(position).getUsuario());
                intent.putExtra("contrasena",listaUsuarios.get(position).getContrasena());
                context.startActivity(intent);
            }
        });
        holder.btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EliminacionUsuarioActivity.class);
                intent.putExtra("nombre",listaUsuarios.get(position).getNombre());
                intent.putExtra("usuario",listaUsuarios.get(position).getUsuario());
                intent.putExtra("contrasena",listaUsuarios.get(position).getContrasena());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.listaUsuarios.size();
    }


    public class MyViewHolder extends  RecyclerView.ViewHolder{

        //OBTENIENDO COMPONENTES
        TextView nombre, usuario, contrasena;
        MaterialButton btn_editar, btn_eliminar;
        public MyViewHolder(View view){
            super(view);

            nombre = view.findViewById(R.id.nombre);
            usuario = view.findViewById(R.id.usuario);
            contrasena = view.findViewById(R.id.contrasena);
            btn_editar = view.findViewById(R.id.btn_editar);
            btn_eliminar = view.findViewById(R.id.btn_eliminar);

        }

    }

}
