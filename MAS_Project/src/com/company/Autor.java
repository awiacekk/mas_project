package com.company;

import java.time.LocalDate;
import java.util.Optional;

public class Autor extends ObjectPlus4 {
    private String imie;
    private String nazwisko;
    private LocalDate dataUrodzenia;
    private Optional<LocalDate> dataSmierci = Optional.empty();

    public Autor(String imie, String nazwisko, LocalDate dataUrodzenia, Optional<LocalDate> dataSmierci) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataUrodzenia = dataUrodzenia;
        this.dataSmierci = dataSmierci;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public LocalDate getDataUrodzenia() {
        return dataUrodzenia;
    }

    public LocalDate getDataSmierci() {
        return dataSmierci.orElse(null);
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void setDataUrodzenia(LocalDate dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }

    public void setDataSmierci(Optional<LocalDate> dataSmierci) {
        this.dataSmierci = dataSmierci;
    }
}
