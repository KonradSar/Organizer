package com.example.konrad.organizer;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.konrad.organizer.NaszeMEtody.position;

/**
 * Created by Konrad on 06.07.2017.
 */

public class DutiesAdapter extends RecyclerView.Adapter<DutiesAdapter.MyViewHolder> {

    private static MediaPlayer mySound;
    private List<Duties> dutiesList = NaszeMEtody.dutiesList;
    private Context context1;
    private RecyclerView mRecyclerView;
    DataBaseQueries2 polaczenieBazy;
    final Date currentTime = Calendar.getInstance().getTime();
    DutiesAdapter dutiesAdapter;
    int THRESHOLD = 15;
    private SQLiteDatabase baza;
    SQLiteDatabase sqLiteDatabase;
    private ClassHelper2 pomocnikBazy;

    public void otworzZapis() throws SQLException {
        baza = pomocnikBazy.getWritableDatabase();
    }

    public static int positionForRecycler(int condition) {
        return condition;
    }

    private int position;

    public int getPosition() {
        return position;
    }

    public int setPosition(int position) {
        this.position = position;
        return position;
    }


    public DutiesAdapter(List<Duties> dutiesList, Context context) {
        this.dutiesList = dutiesList;
        this.context1 = context;
        polaczenieBazy = new DataBaseQueries2(context1);
        polaczenieBazy.otworzZapis();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.singledutyline, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        NaszeMEtody.counter = 0;
        final Duties duty = dutiesList.get(holder.getAdapterPosition());
        Duties duties = dutiesList.get(position);
        holder.name.setText((duty.opisZdarzenia.length() > THRESHOLD) ? duty.opisZdarzenia.substring(0, THRESHOLD) + ".." : duty.opisZdarzenia);
        holder.time.setText(duty.terminWykonania);
        if (duty.getBackgroundColor().equals("red")) {
            holder.relativeLayout.setBackgroundResource(R.drawable.but2);
        } else if (duty.getBackgroundColor().equals("green")) {
            holder.relativeLayout.setBackgroundResource(R.drawable.but3);
        } else {
            holder.relativeLayout.setBackgroundResource(R.drawable.yellowone);
        }
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySound = MediaPlayer.create(context1, R.raw.klikanie);
                mySound.start();
                polaczenieBazy.usunWiersz(String.valueOf(duty.getId()));
                dutiesList = polaczenieBazy.zwrocListeFilmow();
                notifyItemRemoved(position);
            }
        });
        holder.doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySound = MediaPlayer.create(context1, R.raw.klikanie);
                mySound.start();
                ClassHelper2 classHelper2 = new ClassHelper2(context1);
                sqLiteDatabase = classHelper2.getWritableDatabase();
                duty.setBackgroundColor("green");
                NaszeMEtody.position = duty.getId();
                polaczenieBazy.edytujWiersz(String.valueOf(duty.getId()), "green");
                polaczenieBazy.zwrocListeFilmow();
                notifyDataSetChanged();
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySound = MediaPlayer.create(context1, R.raw.klikanie);
                mySound.start();
                if (!duty.getDescription().equals("")) {
                    LinearLayout linearLayout = new LinearLayout(context1);
                    linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(context1);
                    alertDialog.setView(linearLayout);
                    alertDialog.setTitle("Opis Wydarzenia");
                    String message = duty.getDescription();
                    alertDialog.setMessage(message);
                    alertDialog.create();
                    alertDialog.show();
                } else if (duty.getDescription().equals("")) {
                    Toast.makeText(context1, "Wybrane Wydarzenie Nie Posiada Opisu", Toast.LENGTH_SHORT).show();
                }
            }
        });}

    @Override
    public int getItemCount() {
        return dutiesList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView time;
        public ImageButton deleteBtn;
        public RelativeLayout relativeLayout;
        public ImageButton doneBtn;


        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.nazwaWydarzenia);
            time = (TextView) itemView.findViewById(R.id.dataWydarzenia);
            deleteBtn = (ImageButton) itemView.findViewById(R.id.deletebtn);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.recyclerViewLay);
            doneBtn = (ImageButton) itemView.findViewById(R.id.doneBtn);
        }

    }

}
