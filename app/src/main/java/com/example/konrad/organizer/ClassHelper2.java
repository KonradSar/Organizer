package com.example.konrad.organizer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Konrad on 06.07.2017.
 */

public class ClassHelper2 extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "Duties.db";
    public static final String DELETETABLE = "DROP TABLE IF EXISTS "+ DutiesContract.DutiesTable.NAZWA_TABELI;
    public static final String CREATESQLQUERY = "CREATE TABLE " + DutiesContract.DutiesTable.NAZWA_TABELI + " (" + DutiesContract.DutiesTable._ID +
            " INTEGER PRIMARY KEY," + DutiesContract.DutiesTable.KOLUMNA_OPIS_WYDARZENIA + " TEXT," + DutiesContract.DutiesTable.KOLUMNA_TERMIN_WYKONANIA +
            " TEXT," + DutiesContract.DutiesTable.KOLUMNA_SHORT_DESCRIPTION +
            " TEXT," + DutiesContract.DutiesTable.KOLUMNA_COLOR + " TEXT)";

    public ClassHelper2(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATESQLQUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETETABLE);
        this.onCreate(db);
    }
}
