package com.company;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

public class Grupa extends ObjectPlus4{
    enum Status{
        ZAPLANOWANA,
        W_TRAKCIE_ZWIEDZANIA,
        ZAKONCZONO_ZWIEDZANIE
    }
    private static int maxLiczbaUczestnikow = 20;
    private int liczbaMiejsc = maxLiczbaUczestnikow;
    private Status status;
    private LocalDateTime data;

    public Grupa(LocalDateTime data) {
        this.status = Status.ZAPLANOWANA;
        this.data = data;
    }
    public static void usunDane(){
        try {
            Iterable<Grupa> grupy = ObjectPlus.getExtent(Grupa.class);
            for (var g : grupy) {
                if (g.status == Status.ZAKONCZONO_ZWIEDZANIE) {
                    Grupa.removeExtent(g);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void przypiszPrzewodnika(Przewodnik przewodnik){
        this.addLink("przewodnik","grupa",przewodnik);
    }

    public static int getMaxLiczbaUczestnikow() {
        return maxLiczbaUczestnikow;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getData() {
        return data;
    }

    public static void setMaxLiczbaUczestnikow(int maxLiczbaUczestnikow) {
        Grupa.maxLiczbaUczestnikow = maxLiczbaUczestnikow;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    @Override
    public String toString() {
        Map<String, String> daysDictionary = new LinkedHashMap<>();
        daysDictionary.put("JANUARY", "01");
        daysDictionary.put("FEBRUARY", "02");
        daysDictionary.put("MARCH", "03");
        daysDictionary.put("APRIL", "04");
        daysDictionary.put("MAY", "05");
        daysDictionary.put("JUNE", "06");
        daysDictionary.put("JULY", "07");
        daysDictionary.put("AUGUST", "08");
        daysDictionary.put("SEPTEMBER", "09");
        daysDictionary.put("OCTOBER", "10");
        daysDictionary.put("NOVEMBER", "11");
        daysDictionary.put("DECEMBER", "12");
        if(getData().getMinute()<10) {
            return getData().getDayOfMonth() + "." + daysDictionary.get(getData().getMonth().toString()) + "." + getData().getYear() +
                    " o " + getData().getHour() + ":0" + getData().getMinute();
        }else{
            return getData().getDayOfMonth() + "." + daysDictionary.get(getData().getMonth().toString()) + "." + getData().getYear() +
                    " o " + getData().getHour() + ":" + getData().getMinute();
        }
    }
    public int getLicznosc() throws Exception {
        return this.getLinks("bilet").length;
    }
}
