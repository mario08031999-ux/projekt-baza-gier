package com.example.bazagierprojekt;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText nazwaText, rokWydaniaText;
    Button dodaj;
    ListView listView;
    List<Gry> gryList;
    ArrayAdapter<Gry> adapter;
    Spinner spinner;

    Aplikacje aplikacjeDataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        aplikacjeDataBase = Room.databaseBuilder(
                getApplicationContext(), Aplikacje.class,"Aplikacje")
                .allowMainThreadQueries()
                .build();

        //aplikacjeDataBase.gryDAO().wstawGreDoBazy(new Gry("Minecraft", true, "sandbox", 2006));

    }
}