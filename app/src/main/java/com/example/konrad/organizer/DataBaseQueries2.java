package com.example.konrad.organizer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Konrad on 06.07.2017.
 */

public class DataBaseQueries2 {
    private SQLiteDatabase baza;
    private ClassHelper2 pomocnikBazy;
    public DataBaseQueries2(Context context){
        pomocnikBazy = new ClassHelper2(context);
    }
    public void otworzZapis() throws SQLException {
        baza = pomocnikBazy.getWritableDatabase();
    }
    public void otworzOdczyt(){
        baza = pomocnikBazy.getReadableDatabase();
    }
    public void zamknij(){
        baza.close();
    }
    public void dodajDoBazy2(int index, String opisWydarzenia, String terminWykonania, String longDescription, String color, SQLiteDatabase baza){
        ContentValues wartosci = new ContentValues();
        wartosci.put(DutiesContract.DutiesTable._ID, index);
        wartosci.put(DutiesContract.DutiesTable.KOLUMNA_OPIS_WYDARZENIA, opisWydarzenia);
        wartosci.put(DutiesContract.DutiesTable.KOLUMNA_TERMIN_WYKONANIA, terminWykonania);
        wartosci.put(DutiesContract.DutiesTable.KOLUMNA_SHORT_DESCRIPTION, longDescription);
        wartosci.put(DutiesContract.DutiesTable.KOLUMNA_COLOR, color);
        baza.insert(DutiesContract.DutiesTable.NAZWA_TABELI, null, wartosci);
        baza.close();
    }

    public void usun(String id){
        String selection = DutiesContract.DutiesTable._ID + " = ?";
        String selectionArgumenty[] = { id };
        baza.delete(DutiesContract.DutiesTable.NAZWA_TABELI, selection, selectionArgumenty);
    }
    public void usunWiersz(String id){
        String selection = DutiesContract.DutiesTable._ID + " = ?";
        String selectionArgumenty[] = { id };
        baza.delete(DutiesContract.DutiesTable.NAZWA_TABELI, selection, selectionArgumenty);
    }
    public ArrayList<Duties> zwrocListeFilmow(){
        ArrayList<Duties> listaCzynnosci = new ArrayList<Duties>();
        Cursor cursor = baza.query(DutiesContract.DutiesTable.NAZWA_TABELI, null, null, null, null, null, null);
        Duties duties;
        if(cursor.getCount() > 0){
            for (int i = 0; i < cursor.getCount(); i++){
                cursor.moveToNext();
                duties = new Duties();
                duties.setId(cursor.getInt(0));
                duties.setOpisZdarzenia(cursor.getString(1));
                duties.setTerminWykonania(cursor.getString(2));
                duties.setDescription(cursor.getString(3));
                duties.setBackgroundColor(cursor.getString(4));
                listaCzynnosci.add(duties);
            }
        }
        return listaCzynnosci;}

    public void edytujWiersz(String id, String color){
        ContentValues wartosci = new ContentValues();
        wartosci.put(DutiesContract.DutiesTable.KOLUMNA_COLOR, color);
        String selection = DutiesContract.DutiesTable._ID + " = ?";
        String selectionArgumenty[] = { id };
        baza.update(DutiesContract.DutiesTable.NAZWA_TABELI, wartosci, selection, selectionArgumenty);
    }
    public void czyscicielBazy(){
        baza.execSQL("DELETE FROM " + DutiesContract.DutiesTable.NAZWA_TABELI);
    }
    public int counter(){
        int value = 0;
        baza.execSQL("SELECT COUNT(*) FROM "+DutiesContract.DutiesTable.NAZWA_TABELI);
        return value;
    }

}
