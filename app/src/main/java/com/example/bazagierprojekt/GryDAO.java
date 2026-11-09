package com.example.bazagierprojekt;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface GryDAO {
    @Insert
    void wstawGreDoBazy(Gry gry);

    @Insert
    void wstawListeGier(Gry ... gierki);

    @Delete
    void usunGreZBazy(Gry gry);

    @Update
    void zaktualizujaGryWBazie(Gry gry);

    @Query("SELECT * FROM gry")
    List<Gry>zwrocWszystkieGryZBazy();

    @Query("SELECT nazwa FROM gry ORDER BY :gryArg")
    List<String> zwrocNazweGry(String gryArg);
}
