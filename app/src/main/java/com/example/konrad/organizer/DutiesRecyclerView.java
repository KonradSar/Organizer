package com.example.konrad.organizer;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class DutiesRecyclerView extends AppCompatActivity {
    DataBaseQueries2 polaczenieBazy;
    RecyclerView mojRecycler;
    Context context1;
    DutiesAdapter dutiesAdapter;
    SQLiteDatabase sqLiteDatabase;
    MediaPlayer mySecondSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duties_recycler_view);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingbtn);
        mojRecycler = (RecyclerView) findViewById(R.id.dutiesRecycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mojRecycler.setLayoutManager(linearLayoutManager);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);
        polaczenieBazy = new DataBaseQueries2(DutiesRecyclerView.this);
        polaczenieBazy.otworzZapis();
        final List<Duties> lista = polaczenieBazy.zwrocListeFilmow();
        dutiesAdapter = new DutiesAdapter(lista, DutiesRecyclerView.this);
        mySecondSound = MediaPlayer.create(this, R.raw.klikanie);
        // Utwór w formacie mp3 o nazwie Click On na licencji Attribution 3.0 pobrany dnia 04.09.2017 godz. 20:00 ze strony https://soundbible.com/
        mojRecycler.setAdapter(dutiesAdapter);
        // wszystkie grafiki wykorzystane w aplikcaji zostały pobrane z darmowego banku https://pixabay.com/pl/

        final ImageButton downloadData = (ImageButton) findViewById(R.id.readListFromSQL);


        final ImageButton clearSQLList = (ImageButton) findViewById(R.id.clearWholeSQL);


        final Date currentTime = Calendar.getInstance().getTime();


        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mySecondSound.start();
                // ikona kalendarza użyta w FAB została pobrana ze źródła https://icons8.com/icon/23/calendar#filled
                Toast.makeText(getApplicationContext(), "Dodawanie Nowego Wydarzenia", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DutiesRecyclerView.this, AddDuty.class);
                startActivity(intent);
            }
        });
        downloadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySecondSound.start();
                //ikona folderu użyta w ImageButton została pobrana ze źródła https://icons8.com/icon/56384/downloads-folder
                List<Duties> lista = polaczenieBazy.zwrocListeFilmow();
                adjustDataForAdapter();
            }
        });
        clearSQLList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySecondSound.start();
                //ikona folderu użyta w ImageButton została pobrana ze źródła https://icons8.com/icon/56221/delete-folder
                polaczenieBazy.czyscicielBazy();
                lista.clear();
                dutiesAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Wyczyszczono Pamięć Urządzenia", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void adjustDataForAdapter() {
        NaszeMEtody.dutiesList = polaczenieBazy.zwrocListeFilmow();
        dutiesAdapter = new DutiesAdapter(NaszeMEtody.dutiesList, DutiesRecyclerView.this);
        mojRecycler.setAdapter(dutiesAdapter);
    }
}
