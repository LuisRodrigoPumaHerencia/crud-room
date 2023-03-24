package com.rodrigo.crud_orm.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UsuarioDAO {
    @Query("SELECT * FROM Usuario WHERE Usuario.usuario = (:usuario) AND Usuario.contrasena = (:contrasena)")
    Usuario iniciarSesion(String usuario, String contrasena);

    @Query("SELECT * FROM Usuario WHERE Usuario.nombre = (:nombre)")
    Usuario obtenerUsuarioPorNombre(String nombre);

    @Query("SELECT * FROM Usuario")
    List<Usuario> getUsuarios();

    @Insert
    void insertar(Usuario usuario);

    @Delete
    void borrar(Usuario usuario);

    @Update
    void actualizar(Usuario usuario);
}
