    package com.example.bazagierprojekt;

    import static android.view.View.INVISIBLE;
    import static android.view.View.VISIBLE;

    import android.app.AlertDialog;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.AdapterView;
    import android.widget.ArrayAdapter;
    import android.widget.Button;
    import android.widget.CheckBox;
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

    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.List;

    public class MainActivity extends AppCompatActivity {

        EditText nazwaText, iloscGraczy;
        Button dodaj, aktualizuj;
        ListView listView;
        List<Gry> gryList;
        ArrayAdapter<Gry> adapter;
        Spinner rodzaj;
        CheckBox czyAktywna;
        Aplikacje aplikacjeDataBase;

        Gry wybranGra = null;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_main);
            aplikacjeDataBase = Room.databaseBuilder(
                    getApplicationContext(), Aplikacje.class,"Aplikacje")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();

            //aplikacjeDataBase.gryDAO().wstawGreDoBazy(new Gry("Minecraft", true, "sandbox", 10));

            nazwaText = findViewById(R.id.editTextText);
            czyAktywna = findViewById(R.id.checkBox);
            rodzaj = findViewById(R.id.spinner);
            iloscGraczy = findViewById(R.id.editTextNumber);
            dodaj = findViewById(R.id.button);
            listView = findViewById(R.id.listview);
            gryList = aplikacjeDataBase.gryDAO().zwrocWszystkieGryZBazy();

            adapter = new ArrayAdapter<>(
                    MainActivity.this,
                    android.R.layout.simple_list_item_1,
                    gryList
            );

            listView.setAdapter(adapter);

            dodaj.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            boolean czyDalejDziala = czyAktywna.isChecked();

                            int rok = Integer.parseInt(iloscGraczy.getText().toString());

                            Gry gra = new Gry(
                                    nazwaText.getText().toString(),
                                    czyDalejDziala,
                                    rodzaj.getSelectedItem().toString(),
                                    rok
                            );

                            aplikacjeDataBase.gryDAO().wstawGreDoBazy(gra);

                            gryList.clear();
                            gryList.addAll(aplikacjeDataBase.gryDAO().zwrocWszystkieGryZBazy());

                            adapter.notifyDataSetChanged();

                        }
                    }
            );

            listView.setOnItemLongClickListener(
                    new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                            new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Usuwanie gry")
                                .setMessage("Czy napewno chcesz usunąć tę gre")
                                        .setPositiveButton("Tak", (dialog, which) -> {
                                            aplikacjeDataBase.gryDAO().usunGreZBazy(gryList.get(position));
                                            gryList.remove(position);
                                            adapter.notifyDataSetChanged();
                                        })
                                .setNegativeButton("Nie", null)
                                .show();

                        return false;
                        }
                    }
            );

            aktualizuj = findViewById(R.id.aktualizuj);

            listView.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            aktualizuj.setVisibility(VISIBLE);
                            wybranGra = gryList.get(position);
                            nazwaText.setText(wybranGra.nazwa);
                            czyAktywna.setChecked(wybranGra.czyDalejDziala);
                            ArrayList<String> rodajeGry =new ArrayList<>(
                                    Arrays.asList("horror", "sandbox", "scify", "przygodowa", "shooter")
                            );
                            rodzaj.setSelection(rodajeGry.indexOf(wybranGra.rodzajGry));

                            iloscGraczy.setText(String.valueOf(wybranGra.liczbaGraczy));

                        }
                    }
            );

            aktualizuj.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            wybranGra.nazwa = nazwaText.getText().toString();
                            wybranGra.czyDalejDziala = czyAktywna.isChecked();
                            wybranGra.rodzajGry = rodzaj.getSelectedItem().toString();
                            wybranGra.liczbaGraczy = Integer.valueOf(iloscGraczy.getText().toString());
                            aplikacjeDataBase.gryDAO().zaktualizujaGryWBazie(wybranGra);
                            adapter.notifyDataSetChanged();
                            aktualizuj.setVisibility(INVISIBLE);
                        }
                    }
            );


        }
    }