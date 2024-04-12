package com.company;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Pracownik extends Osoba{
    private LocalDate dataZatrudnienia;
    private int stazPracy;
    private BigDecimal pensja;
    public Pracownik(String imie, String nazwisko, LocalDate dataUrodzenia, String nrTelefonu, String email,
                     LocalDate data_zatrudnienia, BigDecimal pensja) throws Exception {
        super(imie, nazwisko, dataUrodzenia, nrTelefonu, email);
        this.dataZatrudnienia = data_zatrudnienia;
        this.pensja = pensja;
    }

}
