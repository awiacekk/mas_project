package com.company;

import java.math.BigDecimal;

public class Bilet extends ObjectPlus4{
    private Rodzaj rodzaj;
    enum Rodzaj{
        ULGOWY,
        NORMALNY
    }

    public Bilet(Rodzaj rodzaj) {
        this.rodzaj = rodzaj;
    }

    public Bilet() {
    }

    public Rodzaj getRodzaj() {
        return rodzaj;
    }

    public static BigDecimal getCenaNormalny() {
        return new BigDecimal(30);
    }
    public static BigDecimal getCenaUlgowy() {
        return new BigDecimal(15);
    }

    public void setRodzaj(Rodzaj rodzaj) {
        this.rodzaj = rodzaj;
    }

    @Override
    public String toString() {
        BigDecimal cena = new BigDecimal("0");
        if(rodzaj==Rodzaj.NORMALNY) cena=getCenaNormalny();
        else cena=getCenaUlgowy();
        return "Bilet{" +
                "rodzaj=" + rodzaj +", cena="+cena+
                '}';
    }
}
