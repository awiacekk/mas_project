package com.company;
public abstract class Wystawa extends ObjectPlus4{
    private String nazwa;
    private String opis;

    public Wystawa(String nazwa, String opis) {
        this.nazwa = nazwa;
        this.opis = opis;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public static void zorganizujNowaWystawe() {

    }
}
