package com.company;

import java.time.LocalDate;

public class Eksponat extends ObjectPlus4{
    enum Rodzaj{
        RZEZBA,
        OBRAZ,
        PAPIER,
        BRON
    }
    private String nazwa;
    private String opis;
    private LocalDate dataPowstania;
    private String styl;
    private Rodzaj rodzaj;
    public void wyswietlListeEksponatow(){
        System.out.println(this);
    }

    public Eksponat(String nazwa, String opis, LocalDate dataPowstania, String styl, Rodzaj rodzaj) {
        this.nazwa = nazwa;
        this.opis = opis;
        this.dataPowstania = dataPowstania;
        this.styl = styl;
        this.rodzaj = rodzaj;
    }

    public Eksponat dodajEksponat(String nazwa, String opis, LocalDate dataPowstania, String styl, Rodzaj rodzaj){
        return new Eksponat(nazwa,opis,dataPowstania,styl,rodzaj);
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public LocalDate getDataPowstania() {
        return dataPowstania;
    }

    public String getStyl() {
        return styl;
    }

    public Rodzaj getRodzaj() {
        return rodzaj;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void setDataPowstania(LocalDate dataPowstania) {
        this.dataPowstania = dataPowstania;
    }

    public void setStyl(String styl) {
        this.styl = styl;
    }

    public void setRodzaj(Rodzaj rodzaj) {
        this.rodzaj = rodzaj;
    }

    @Override
    public String toString() {
        return "Eksponat{" +
                "nazwa='" + nazwa + '\'' +
                ", opis='" + opis + '\'' +
                ", dataPowstania=" + dataPowstania +
                ", styl='" + styl + '\'' +
                ", rodzaj=" + rodzaj +
                '}';
    }
}
