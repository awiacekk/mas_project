package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Placowka extends ObjectPlus4{
    private String nazwa;
    private String nrTelefonu;
    private static LocalTime godzina_otwarcia = LocalTime.of(10,0,0);
    private static LocalTime godzina_zamkniecia = LocalTime.of(18,0,0);
    private String adres;
    private static List<PlacowkaWarsztaty> lista = new ArrayList<>();

    public Placowka(String nazwa, String nrTelefonu, String adres) {
        this.nazwa = nazwa;
        this.nrTelefonu = nrTelefonu;
        this.adres = adres;
    }
    public static void wyswietlListePlacowek(Osoba osoba) throws Exception {
        JFrame frame = new JFrame("Placowka");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        JButton button = new JButton("Wybierz");
        String[] columnNames = { "nazwa", "nr telefonu", "czynne", "adres"};

        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();

        Iterable<Placowka> placowki = ObjectPlus.getExtent(Placowka.class);
        model.setColumnIdentifiers(columnNames);
        for (var p : placowki) {
            String[] row = new String[4];
            row[0] = p.getNazwa();
            row[1] = p.getNrTelefonu();
            row[2] = Placowka.getGodzina_otwarcia().getHour() + ":00 -" + Placowka.getGodzina_zamkniecia().getHour()+":00";
            row[3] = p.getAdres();
            model.addRow(row);
        }
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        button.addActionListener(e -> {
            int index = table.getSelectedRow();
            int k = 0;
            for (var p : placowki) {
                if(index==k){
                    try {
                        ((ZwiedzajacyUczestnik)osoba).kupBilet(p);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                }
                k++;
            }
            frame.setVisible(false);
            frame.dispose();
        });
        panel.add(BorderLayout.NORTH, scrollPane);
        panel.add(BorderLayout.SOUTH, button);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
    public static void dodajPlacowkaWarsztaty(PlacowkaWarsztaty w){
        lista.add(w);
    }
    public String getNazwa() {
        return nazwa;
    }

    public String getNrTelefonu() {
        return nrTelefonu;
    }

    public static LocalTime getGodzina_otwarcia() {
        return godzina_otwarcia;
    }

    public static LocalTime getGodzina_zamkniecia() {
        return godzina_zamkniecia;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setNrTelefonu(String nrTelefonu) {
        this.nrTelefonu = nrTelefonu;
    }

    public static void setGodzina_otwarcia(LocalTime godzina_otwarcia) {
        Placowka.godzina_otwarcia = godzina_otwarcia;
    }

    public static void setGodzina_zamkniecia(LocalTime godzina_zamkniecia) {
        Placowka.godzina_zamkniecia = godzina_zamkniecia;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    @Override
    public String toString() {
        return "Placowka{" +
                "nazwa='" + nazwa + '\'' +
                ", nrTelefonu='" + nrTelefonu + '\'' +
                '}';
    }
}
