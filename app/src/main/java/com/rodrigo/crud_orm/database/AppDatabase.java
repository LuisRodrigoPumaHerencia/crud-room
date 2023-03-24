package com.rodrigo.crud_orm.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Usuario.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UsuarioDAO usuarioDAO();

    private static AppDatabase instancia;

    public static AppDatabase getInstance(Context context){
        if(instancia==null){
            instancia = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "BD_USUARIO").allowMainThreadQueries().build();
        }
        return instancia;
    }

}
