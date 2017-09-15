package com.example.konrad.organizer;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.konrad.organizer.NaszeMEtody.dutiesList;
import static com.example.konrad.organizer.NaszeMEtody.dutiesList;
//import static com.example.konrad.applicationsecond.R.id.terminWykonania;

public class AddDuty extends AppCompatActivity {
    private static  MediaPlayer mySound;
    SQLiteDatabase sqLiteDatabase;
    Context context;
    final String TAG = "KonradApp";
    @BindView(R.id.wydarzenie)
    EditText opisWydarzenia;
    @BindView(R.id.btnAddToList)
    Button btnDodajDoListy;
    @BindView(R.id.date2)
    EditText dateView;
    @BindView(R.id.btnGoToList)
    Button btnGoToList;
    @BindView(R.id.clear)
    Button clearListBtn;
    @BindView(R.id.opisWydarzenia)
    EditText dutyDescription;
    Calendar calendar = Calendar.getInstance();
    DataBaseQueries2 dataBaseQueries2;
    DataBaseQueries2 polaczenieBazy;
    final Date currentTime = java.util.Calendar.getInstance().getTime();
    MediaPlayer myThirdSound;
    List<Duties> listForCounter;
    // ikona aplikacji pobrana z źródła https://icons8.com/icon/43198/stopwatch w dniu 15.09.2017 godz. 19:51
    @OnClick(R.id.btnAddToList)
    public void dodajDoBazy2(View view) {
        myThirdSound.start();
        java.util.Calendar c = java.util.Calendar.getInstance();
        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("dd-MM-yyyy");
        String datetime = df.format(c.getTime());
        int size = 0;
        size = polaczenieBazy.zwrocListeFilmow().size();
        Duties dutyOne;
        dutiesList.add(new Duties(size, opisWydarzenia.getText().toString(), dateView.getText().toString(), dutyDescription.getText().toString(), String.valueOf("transparent")));
        dutyOne = new Duties(size, opisWydarzenia.getText().toString(), dateView.getText().toString(), dutyDescription.getText().toString(), String.valueOf("transparent"));
        ClassHelper2 classHelper2 = new ClassHelper2(this);
        sqLiteDatabase = classHelper2.getWritableDatabase();
        if (dateView.getText().toString().equals(datetime)) {
            polaczenieBazy.dodajDoBazy2(dutyOne.getId(), dutyOne.getOpisZdarzenia(), dutyOne.getTerminWykonania(), dutyOne.getDescription(), String.valueOf("red"), sqLiteDatabase);
        } else {
            polaczenieBazy.dodajDoBazy2(dutyOne.getId(), dutyOne.getOpisZdarzenia(), dutyOne.getTerminWykonania(), dutyOne.getDescription(), String.valueOf("transparent"), sqLiteDatabase);
        }
        Toast.makeText(getBaseContext(), "Dane Zapisane", Toast.LENGTH_LONG).show();
        classHelper2.close();

    }

    @OnClick(R.id.btnGoToList)
    public void wyswietlDane() {
        myThirdSound.start();
        Intent intent = new Intent(AddDuty.this, DutiesRecyclerView.class);
        startActivity(intent);
    }

    @OnClick(R.id.clear)
    public void wyczyscListe() {
        myThirdSound.start();
        polaczenieBazy.czyscicielBazy();

    }

    public void updateDateLabel(EditText editText) {
        String dataFormat = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dataFormat, Locale.UK);
        editText.setText(simpleDateFormat.format(calendar.getTime()));

    }

    @OnClick(R.id.date2)
    public void addToDatabase() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_duty);
        polaczenieBazy = new DataBaseQueries2(this);
        polaczenieBazy.otworzZapis();
        ButterKnife.bind(this);
        dateView.setFocusable(false);
        // wszystkie grafiki wykorzystane w aplikcaji zostały pobrane z darmowego banku https://pixabay.com/pl/

        myThirdSound = MediaPlayer.create(this, R.raw.klikanie);
        // Utwór w formacie mp3 o nazwie Click On na licencji Attribution 3.0 pobrany dnia 04.09.2017 godz. 20:00 ze strony soundbible.com

        dateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddDuty.this, date, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();

            }

            DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, month);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateDateLabel(dateView);
                }
            };
        });
    }

    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        updateDateLabel(dateView);

    }
}
