package com.company;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Kierownik extends Pracownik{
    public Kierownik(String imie, String nazwisko, LocalDate dataUrodzenia, String nrTelefonu, String email, LocalDate data_zatrudnienia, BigDecimal pensja) throws Exception {
        super(imie, nazwisko, dataUrodzenia, nrTelefonu, email, data_zatrudnienia, pensja);
    }

}
