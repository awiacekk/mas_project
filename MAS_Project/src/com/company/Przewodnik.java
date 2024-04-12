package com.company;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Przewodnik extends Pracownik{
    private int id;
    private static Set<Integer> ids = new HashSet<>();
    public Przewodnik(String imie, String nazwisko, LocalDate dataUrodzenia, String nrTelefonu, String email,
                      LocalDate data_zatrudnienia, BigDecimal pensja, int id) throws Exception {
        super(imie, nazwisko, dataUrodzenia, nrTelefonu, email, data_zatrudnienia, pensja);
        setId(id);
    }

    public void setId(int id) {
        if(Przewodnik.getIds().contains(id)){
            System.out.println("Takie id juz istnieje");
        }else{
            Przewodnik.ids.add(id);
        }
    }

    public int getId() {
        return id;
    }

    public static Set<Integer> getIds() {
        return ids;
    }
}
