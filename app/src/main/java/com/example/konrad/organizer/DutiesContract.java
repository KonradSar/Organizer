package com.example.konrad.organizer;

import android.provider.BaseColumns;

/**
 * Created by Konrad on 06.07.2017.
 */

public final class DutiesContract {

    private DutiesContract(){}

    public static class DutiesTable implements BaseColumns{
        public static final String NAZWA_TABELI = "obowiazki";
        public static final String KOLUMNA_OPIS_WYDARZENIA = "opisZdarzenia";
        public static final String KOLUMNA_TERMIN_WYKONANIA = "terminWykonania";
        public static final String KOLUMNA_SHORT_DESCRIPTION = "description";
        public static final String KOLUMNA_COLOR = "color";

    }
}
