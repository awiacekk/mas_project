package com.company;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Prowadzacy extends Pracownik{

    enum Specjalizacja{
        PEDAGOG,
        PSYCHOLOG,
        LOGOPEDA
    }
    private Specjalizacja specjalizacja;
    public Prowadzacy(String imie, String nazwisko, LocalDate dataUrodzenia, String nrTelefonu, String email,
                      LocalDate data_zatrudnienia, BigDecimal pensja, Specjalizacja specjalizacja) throws Exception {
        super(imie, nazwisko, dataUrodzenia, nrTelefonu, email, data_zatrudnienia, pensja);
        this.specjalizacja = specjalizacja;
    }

    public Specjalizacja getSpecjalizacja() {
        return specjalizacja;
    }

    public void setSpecjalizacja(Specjalizacja specjalizacja) {
        this.specjalizacja = specjalizacja;
    }
}
