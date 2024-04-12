package com.company;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Warsztaty extends ObjectPlus4{
    private int minWiek;
    private int maxWiek;
    private LocalDate dataRozpoczecia;
    private LocalDate dataZakonczenia;
    private static List<PlacowkaWarsztaty> lista = new ArrayList<>();

    public Warsztaty(int minWiek, int maxWiek, LocalDate dataRozpoczecia, LocalDate dataZakonczenia) throws Exception {
        this.maxWiek=maxWiek;
        setMinWiek(minWiek);
        this.dataZakonczenia=dataZakonczenia;
        setDataRozpoczecia(dataRozpoczecia);
    }
    public static Warsztaty zorganizujWarsztaty(int minWiek, int maxWiek, LocalDate dataRozpoczecia, LocalDate dataZakonczenia) throws Exception {
        return new Warsztaty(minWiek,maxWiek,dataRozpoczecia,dataZakonczenia);
    }
    public static void dodajPlacowkaWarsztaty(PlacowkaWarsztaty w){
        lista.add(w);
    }
    public int getMinWiek() {
        return minWiek;
    }

    public int getMaxWiek() {
        return maxWiek;
    }

    public LocalDate getDataRozpoczecia() {
        return dataRozpoczecia;
    }

    public LocalDate getDataZakonczenia() {
        return dataZakonczenia;
    }

    public void setMinWiek(int minWiek) throws Exception {
        if(maxWiek > minWiek)
            this.minWiek = minWiek;
        else throw new Exception("Wiek minimalny jest wiekszy od wieku maksymalnego.");
    }

    public void setMaxWiek(int maxWiek) throws Exception {
        if(maxWiek > minWiek)
            this.maxWiek = maxWiek;
        else throw new Exception("Wiek maksymalny jest wiekszy od wieku minimalnego.");
    }

    public void setDataRozpoczecia(LocalDate dataRozpoczecia) throws Exception {
        if(dataZakonczenia.isAfter(dataRozpoczecia))
            this.dataRozpoczecia = dataRozpoczecia;
        else throw new Exception("Data rozpoczecia jest pozniejsza niz data zakonczenia.");
    }

    public void setDataZakonczenia(LocalDate dataZakonczenia) throws Exception {
        if(dataZakonczenia.isAfter(dataRozpoczecia))
            this.dataZakonczenia = dataZakonczenia;
        else throw new Exception("Data zakonczenia jest pozniejsza niz data rozpoczecia.");
    }
}
