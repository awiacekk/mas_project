package com.company;

import java.math.BigDecimal;
import java.time.LocalDate;

public class WystawaCzasowa extends Wystawa{
    private LocalDate poczatek;
    private LocalDate koniec;
    private BigDecimal cena;

    public WystawaCzasowa(String nazwa, String opis, LocalDate poczatek, BigDecimal cena) {
        super(nazwa, opis);
        this.poczatek = poczatek;
        this.cena = cena;
    }
    public WystawaCzasowa(String nazwa, String opis, LocalDate poczatek, LocalDate koniec, BigDecimal cena) {
        super(nazwa, opis);
        this.poczatek = poczatek;
        this.koniec = koniec;
        this.cena = cena;
    }

    public LocalDate getPoczatek() {
        return poczatek;
    }

    public LocalDate getKoniec() {
        return koniec;
    }

    public BigDecimal getCena() {
        return cena;
    }

    public void setPoczatek(LocalDate poczatek) {
        this.poczatek = poczatek;
    }

    public void setKoniec(LocalDate koniec) {
        this.koniec = koniec;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }

    public static void zorganizujNowaWystawe() {

    }
}
