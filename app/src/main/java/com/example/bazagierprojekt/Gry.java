package com.example.bazagierprojekt;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Query;

@Entity(tableName = "gry")
public class Gry {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public Gry(String nazwa, Boolean czyDalejDziala, String rodzajGry, int rokWydania) {
        this.nazwa = nazwa;
        this.czyDalejDziala = czyDalejDziala;
        this.rodzajGry = rodzajGry;
        this.rokWydania = rokWydania;
    }

    public String nazwa;
    public Boolean czyDalejDziala;
    public String rodzajGry;
    public int rokWydania;

    @Override
    public String toString() {
        return "nazwa " + nazwa + "\n" + "czy Dalej Dziala " + czyDalejDziala + "\n" + "rodzajGry " + rodzajGry + '\n' + "rokWydania " + rokWydania;
    }


}
