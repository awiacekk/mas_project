package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void showGui(Osoba osoba){
        if(osoba.getClass() == ZwiedzajacyUczestnik.class){
            if(((ZwiedzajacyUczestnik)osoba).getTypObiektu() == ZwiedzajacyUczestnik.TypObiektu.ZWIEDZAJACY ||
                    ((ZwiedzajacyUczestnik)osoba).getTypObiektu() == ZwiedzajacyUczestnik.TypObiektu.ZWIEDZAJACY_UCZESTNIK){
                JFrame frame = new JFrame("Panel uzytkownika");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(500,300);
                JPanel panel = new JPanel();
                panel.setBackground(new Color(215,242,246));
                JButton button1 = new JButton("Wyswietl placowki");
                JButton button2 = new JButton("Kup bilet");
                JLabel label= new JLabel("<html>Witaj w panelu uzytkownika, "+osoba.getImie()+"!" +
                        "<br><center>Wybierz opcje:</center></html>", SwingConstants.CENTER);
                label.setOpaque(true);
                label.setBackground(new Color(200,236,241));
                label.setFont(new Font("Arial", Font.BOLD, 20));
                panel.add(button1);
                panel.add(button2);
                button1.setBackground(Color.WHITE);
                button2.setBackground(Color.WHITE);
                frame.getContentPane().add(BorderLayout.CENTER, label);
                frame.getContentPane().add(BorderLayout.SOUTH, panel);
                button2.addActionListener(e -> {
                    try {
                        SwingUtilities.invokeLater(() -> {
                            try {
                                Placowka.wyswietlListePlacowek(osoba);
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }
                        });
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    frame.setVisible(false);
                    frame.dispose();
                });
                frame.setVisible(true);
            }
        }
    }
    public static void main(String[] args) throws Exception {
        Placowka p1 = new Placowka("Muzeum Glowne","876123098","Kwiatowa 8, 23-800 Warszawa");
        Placowka p2 = new Placowka("Muzeum PRL","876253412","Jerozolimskie 20, 23-800 Warszawa");
        Wystawa w1 = new WystawaCzasowa("Sztuka XIX wieku","xyz",LocalDate.of(2020,5,20),new BigDecimal(20));
        Wystawa w2 = new WystawaCzasowa("Sztuka uzytkowa","xyz",LocalDate.of(2020,5,20),new BigDecimal(30));
        p1.addLink("wystawa czasowa","placowka",w1);
        p1.addLink("wystawa czasowa","placowka",w2);
        Osoba os1 = new ZwiedzajacyUczestnik("Janusz","Kowalski", LocalDate.of(1970,5,20),
                "876543210","jkowalski@gmail.com");
        Osoba os2 = new ZwiedzajacyUczestnik("Anna","Kowalska", LocalDate.of(1980,5,20),
                "123456678","akowalska@gmail.com");
        ((ZwiedzajacyUczestnik)os1).setTypObiektu(ZwiedzajacyUczestnik.TypObiektu.ZWIEDZAJACY);
        ((ZwiedzajacyUczestnik)os2).setTypObiektu(ZwiedzajacyUczestnik.TypObiektu.ZWIEDZAJACY);
        Grupa grupa1 = new Grupa(LocalDateTime.of(2023,7,1,10,0));
        Grupa grupa2 = new Grupa(LocalDateTime.of(2023,7,1,10,30));
        Grupa grupa3 = new Grupa(LocalDateTime.of(2023,7,2,11,30));
        p1.addLink("grupa","placowka",grupa1);
        p1.addLink("grupa","placowka",grupa2);
        p1.addLink("grupa","placowka",grupa3);
        os2.addLink("grupa","zwiedzajacy",grupa1);
        Bilet bilet1 = new Bilet(Bilet.Rodzaj.ULGOWY);
        Bilet bilet2 = new Bilet(Bilet.Rodzaj.NORMALNY);
        os2.addLink("bilet","zwiedzajacy", bilet1);
        os2.addLink("bilet","zwiedzajacy", bilet2);
        os2.addLink("placowka","zwiedzajacy", p1);
        bilet1.addLink("grupa","bilet",grupa1);
        bilet2.addLink("grupa","bilet",grupa1);
        Osoba pr = new Prowadzacy("Anna","Kowalska", LocalDate.of(1979,5,20),
                "123456789","a.kowalska@gmail.com", LocalDate.of(2010,6,30), new BigDecimal("5000.0"),
                Prowadzacy.Specjalizacja.LOGOPEDA);
        Warsztaty warsztaty1 = new Warsztaty(10,14,LocalDate.of(2023,8,20),
                LocalDate.of(2023,9,20));
        pr.addLink("posiada","przypisany do",warsztaty1);
        pr.addLink_subset("warsztaty","prowadzacy","posiada",warsztaty1);
        pr.showLinks("posiada", System.out);
        pr.showLinks("warsztaty", System.out);
        Przewodnik pr2 = new Przewodnik("Adam", "Lis", LocalDate.of(1980, 5, 23),
                "756432897","a.lis@gmail.com",LocalDate.of(2018,4,2),new BigDecimal("5000.0"),0);
        pr2.addLink("grupa","przewodnik",grupa1, pr2.getId());
        System.out.println(pr2.getLinkedObject("grupa", pr2.getId()));
        Eksponat eksponat1 = new Eksponat("Obraz1","zyx",LocalDate.of(1880,5,10),"realizm",
                Eksponat.Rodzaj.OBRAZ);
        Eksponat eksponat2 = new Eksponat("Obraz2","zyx",LocalDate.of(2000,5,10),
                "realizm", Eksponat.Rodzaj.OBRAZ);
        Eksponat eksponat3 = new Eksponat("Obraz3","zyx",LocalDate.of(1750,1,1),"abstrakcja", Eksponat.Rodzaj.OBRAZ);
        w1.addPart("part","whole",eksponat1);
        w1.addPart("part","whole",eksponat2);
        w1.addPart("part","whole",eksponat3);
        w1.showLinks("part",System.out);
        w1.removePart("part","whole",eksponat1);
        w1.showLinks("part",System.out);
        try {
//            var out = new ObjectOutputStream(new FileOutputStream("extent.bin"));
//            ObjectPlus.writeExtents(out);
//            out.close();

            var in = new ObjectInputStream(new FileInputStream("extent.bin"));
            ObjectPlus.readExtents(in);
            in.close();
            Iterable<Bilet> objs = ObjectPlus.getExtent(com.company.Bilet.class);
            int i =0;
            for(var b : objs){
                System.out.println(i++ + ".");
                System.out.println(b);
                b.showLinks("zwiedzajacy",System.out);
            }
//            Iterable<ZwiedzajacyUczestnik> objs = ObjectPlus.getExtent(com.company.ZwiedzajacyUczestnik.class);
//            for(var b : objs){
//                if(b.anyLink("bilet")) {
//                    System.out.println(b);
//                    b.showLinks("bilet", System.out);
//                }
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> showGui(os1));
    }
}