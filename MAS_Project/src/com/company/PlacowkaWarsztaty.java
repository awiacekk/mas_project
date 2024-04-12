package com.company;

import java.time.DayOfWeek;
import java.util.List;

public class PlacowkaWarsztaty extends ObjectPlus4{
    private List<DayOfWeek> dni;
    private Placowka placowka;
    private Warsztaty warsztaty;

    public PlacowkaWarsztaty(List<DayOfWeek> dni, Placowka placowka, Warsztaty warsztaty, int id) {
        this.dni = dni;
        this.placowka=placowka;
        this.warsztaty=warsztaty;
        Placowka.dodajPlacowkaWarsztaty(this);
        Warsztaty.dodajPlacowkaWarsztaty(this);
        this.addLink("placowka", "warsztatyplacowka",placowka,id);
        this.addLink("warsztaty", "warsztatyplacowka",warsztaty,id);
    }

    public List<DayOfWeek> getDni() {
        return dni;
    }

    public Placowka getPlacowka() {
        return placowka;
    }

    public Warsztaty getWarsztaty() {
        return warsztaty;
    }

    public void setDni(List<DayOfWeek> dni) {
        this.dni = dni;
    }

    public void setPlacowka(Placowka placowka) {
        this.placowka = placowka;
    }

    public void setWarsztaty(Warsztaty warsztaty) {
        this.warsztaty = warsztaty;
    }
}
