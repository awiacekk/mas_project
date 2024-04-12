package com.company;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Osoba extends ObjectPlus4{
    private String imie;
    private String nazwisko;
    private LocalDate dataUrodzenia;
    private String nrTelefonu;
    private String email;
    private static final Map<String,Set<String>> map = new HashMap<>();

    public Osoba(String imie, String nazwisko, LocalDate dataUrodzenia, String nrTelefonu, String email) throws Exception {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataUrodzenia = dataUrodzenia;
        setNrTelefonu(nrTelefonu);
        setEmail(email);
    }
    public void setNrTelefonu(String nrTelefonu) throws Exception {
        boolean flag = nrTelefonu.matches("^[0-9]{9}$");
        if(flag) {
            if (nrTelefonu != null) {
                if (!map.containsKey("nrTelefonu")) {
                    Set<String> numbers = new HashSet<String>();
                    numbers.add(nrTelefonu);
                    map.put("nrTelefonu", numbers);
                } else if (map.get("nrTelefonu").contains(nrTelefonu)) {
                    throw new Exception("Ten nr telefonu juz istnieje");
                } else {
                    map.get("nrTelefonu").add(nrTelefonu);
                    this.nrTelefonu = nrTelefonu;
                }
            } else throw new NullPointerException();
        }else throw new Exception("Niepoprawny format numeru");
    }
    public void setEmail(String email) throws Exception{
        if (email != null) {
            if (!map.containsKey("email")) {
                Set<String> emails = new HashSet<String>();
                emails.add(email);
                map.put("email", emails);
            } else if (map.get("email").contains(email)) {
                throw new Exception("Ten email juz istnieje");
            } else {
                map.get("email").add(email);
                this.email = email;
            }
        } else throw new NullPointerException();
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

    public String getNrTelefonu() {
        return nrTelefonu;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Osoba{" +
                "imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", dataUrodzenia=" + dataUrodzenia +
                ", nrTelefonu='" + nrTelefonu + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
