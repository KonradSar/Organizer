package com.example.konrad.organizer;

/**
 * Created by Konrad on 06.07.2017.
 */

public class Duties {
    public int Id;
    public String opisZdarzenia;
    public String terminWykonania;
    public String description;
    public String backgroundColor;

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String zwrocOpisWydarzenia(){
        return opisZdarzenia;
    }
    public String zwrocTerminWykonania(){
        return terminWykonania;
    }


    public Duties(String opisZdarzenia, String terminWykonania) {
        this.opisZdarzenia = opisZdarzenia;
        this.terminWykonania = terminWykonania;
    }

    public Duties(int id, String opisZdarzenia, String terminWykonania, String description, String backgroundColor) {
        Id = id;
        this.opisZdarzenia = opisZdarzenia;
        this.terminWykonania = terminWykonania;
        this.description = description;
        this.backgroundColor = backgroundColor;
    }

    public Duties() {

    }

    public Duties(String opisZdarzenia) {
        this.opisZdarzenia = opisZdarzenia;
    }

    public String zwrocWiersz(){
        return Id + " ," + opisZdarzenia;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getOpisZdarzenia() {
        return opisZdarzenia;
    }

    public void setOpisZdarzenia(String opisZdarzenia) {
        this.opisZdarzenia = opisZdarzenia;
    }

    public String getTerminWykonania() {
        return terminWykonania;
    }

    public void setTerminWykonania(String terminWykonania) {
        this.terminWykonania = terminWykonania;
    }
    @Override
    public String toString() {
        return "Book [id=" + Id + ", description=" + opisZdarzenia + ", date=" + terminWykonania
                + "]";
    }
}
