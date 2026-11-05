package com.example.bazagierprojekt;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Gry.class}, version = 1)
public abstract class Aplikacje extends RoomDatabase {
    public abstract GryDAO gryDAO();
}
