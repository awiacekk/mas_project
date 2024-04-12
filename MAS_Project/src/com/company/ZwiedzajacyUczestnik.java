package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.List;

public class ZwiedzajacyUczestnik extends Osoba{
    private TypObiektu typObiektu;
    enum TypObiektu{
        ZWIEDZAJACY,
        UCZESTNIK,
        ZWIEDZAJACY_UCZESTNIK
    }
    public ZwiedzajacyUczestnik(String imie, String nazwisko, LocalDate dataUrodzenia, String nrTelefonu, String email) throws Exception {
        super(imie, nazwisko, dataUrodzenia, nrTelefonu, email);
    }
    public void kupBilet(Placowka placowka) throws Exception {
        if(typObiektu==TypObiektu.ZWIEDZAJACY || typObiektu==TypObiektu.ZWIEDZAJACY_UCZESTNIK) {
            JFrame frame = new JFrame("Opcje biletu");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(0,2));
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JLabel iloscLabel = new JLabel("ulgowy:",SwingConstants.CENTER);
            iloscLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            JTextField iloscField = new JTextField("0");
            JLabel rodzajLabel = new JLabel("normalny:",SwingConstants.CENTER);
            rodzajLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            JTextField iloscField2 = new JTextField("0");
            JLabel wystawaLabel = new JLabel("wystawa czasowa:",SwingConstants.CENTER);
            wystawaLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            String[] columnNames = {"Nazwa","Wybierz"};
            ObjectPlusPlus[] linksWystawy = placowka.getLinks("wystawa czasowa");
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(columnNames);
            for (var wystawa : linksWystawy) {
                Object[] row = new Object[2];
                row[0] = ((WystawaCzasowa)wystawa).getNazwa();
                row[1] = false;
                model.addRow(row);
            }
            JTable table = new JTable(model){
                private static final long serialVersionUID = 1L;
                @Override
                public Class getColumnClass(int column) {
                    switch (column) {
                        case 0:
                            return String.class;
                        default:
                            return Boolean.class;
                    }
                }
            };
            JScrollPane scrollPane = new JScrollPane(table);
            JButton button1 = new JButton("Dalej");
            button1.addActionListener(e -> {
                if (!iloscField.getText().matches("^0+[1-9]+$") && !iloscField2.getText().matches("^0+[1-9]+$") &&
                 (iloscField.getText().matches("^[1-9]+$") && iloscField2.getText().matches("^[0-9]+$")
                || iloscField.getText().matches("^[0-9]+$") && iloscField2.getText().matches("^[1-9]+$") )) {
                    JDialog dialog = new JDialog(frame, "Potwierdzenie");
                    JPanel panel5 = new JPanel();
                    panel5.setLayout(new BoxLayout(panel5, BoxLayout.PAGE_AXIS));
                    JLabel label5 = new JLabel("Czy potwierdzasz dane?");
                    label5.setFont(new Font("Arial", Font.BOLD, 20));
                    label5.setAlignmentX(Component.CENTER_ALIGNMENT);
                    StringBuilder text = new StringBuilder("ulgowe: " + iloscField.getText() + " x" + Bilet.getCenaUlgowy() + "zl\nnormalne: " +
                            iloscField2.getText() + " x" + Bilet.getCenaNormalny() + " zl\nwystawy czasowe:");
                    BigDecimal ulgoweCena = Bilet.getCenaUlgowy().multiply(BigDecimal.valueOf(Long.parseLong(iloscField.getText())));
                    BigDecimal normalneCena = Bilet.getCenaNormalny().multiply(BigDecimal.valueOf(Long.parseLong(iloscField2.getText())));
                    BigDecimal razem = ulgoweCena.add(normalneCena);
                    List<WystawaCzasowa> list = new ArrayList<>();
                    for (int i = 0; i < model.getColumnCount(); i++) {
                        if ((boolean) model.getValueAt(i, 1)) {
                            BigDecimal cenaWystawy = ((WystawaCzasowa) linksWystawy[i]).getCena();
                            text.append("\n").append(model.getValueAt(i, 0)).append(" ").append(cenaWystawy).append("zl");
                            razem = razem.add(cenaWystawy);
                            list.add((WystawaCzasowa) linksWystawy[i]);
                        }
                    }
                    JLabel label6 = new JLabel("Razem: " + razem + "zl");
                    label6.setAlignmentX(Component.CENTER_ALIGNMENT);
                    label6.setFont(new Font("Arial", Font.PLAIN, 20));
                    JTextArea area = new JTextArea(text.toString());
                    area.setFont(new Font("Arial", Font.PLAIN, 15));
                    area.setEditable(false);
                    JScrollPane scrollPane1 = new JScrollPane(area);
                    JButton button3 = new JButton("Tak");
                    BigDecimal finalRazem = razem;
                    button3.addActionListener(e12 -> {
                        List<Bilet> bilety = new ArrayList<>();
                        for (int i = 0; i < Integer.parseInt(iloscField.getText()); i++) {
                            Bilet bilet = new Bilet(Bilet.Rodzaj.ULGOWY);
                            ZwiedzajacyUczestnik.super.addLink("bilet", "zwiedzajacy", bilet);
                            for (WystawaCzasowa w : list) {
                                bilet.addLink("wystawaCzasowa", "bilet", w);
                            }
                            bilety.add(bilet);
                        }
                        for (int i = 0; i < Integer.parseInt(iloscField2.getText()); i++) {
                            Bilet bilet = new Bilet(Bilet.Rodzaj.NORMALNY);
                            ZwiedzajacyUczestnik.super.addLink("bilet", "zwiedzajacy", bilet);
                            for (WystawaCzasowa w : list) {
                                bilet.addLink("wystawaCzasowa", "bilet", w);
                            }
                            bilety.add(bilet);
                        }
                        int a = JOptionPane.showConfirmDialog(dialog, "Czy chcesz sie zapisac do grupy?");
                        ZwiedzajacyUczestnik.super.addLink("placowka", "zwiedzajacy", placowka);
                        if (a == JOptionPane.YES_OPTION) {
                            SwingUtilities.invokeLater(() -> zapiszSieDoGrupy(bilety));
                        }
                        try {
                            frame.setVisible(false);
                            JDialog dialog1 = new JDialog(frame, "Platnosc");
                            dialog1.setSize(600, 300);
                            JPanel panel3 = new JPanel();
                            panel3.setLayout(new BorderLayout());
                            JPanel panel2 = new JPanel();
                            GridLayout layout = new GridLayout(0, 2);
                            JLabel label1 = new JLabel("Podaj nr karty:", SwingUtilities.CENTER);
                            JTextField textField = new JTextField();
                            JLabel label2 = new JLabel("Podaj cvv karty:", SwingUtilities.CENTER);
                            JTextField textField1 = new JTextField();
                            JLabel label3 = new JLabel("Podaj termin karty:", SwingUtilities.CENTER);
                            JTextField textField2 = new JTextField();
                            panel2.setLayout(layout);
                            panel2.add(label1);
                            panel2.add(textField);
                            panel2.add(label2);
                            panel2.add(textField1);
                            panel2.add(label3);
                            panel2.add(textField2);
                            JLabel label4 = new JLabel("<html><center>Podaj dane platnosci:</center><br><center>" +
                                    "Do zaplacenia: " + finalRazem + " zl</center></br></html>", SwingUtilities.CENTER);
                            label1.setFont(new Font("Arial", Font.BOLD, 20));
                            label2.setFont(new Font("Arial", Font.BOLD, 20));
                            label3.setFont(new Font("Arial", Font.BOLD, 20));
                            label4.setFont(new Font("Arial", Font.BOLD, 20));
                            JButton button = new JButton("Potwierdz");
                            button.addActionListener(e121 -> {
                                boolean poprawne = textField.getText().matches("(\\d{4}/\\d{4}/\\d{4}/\\d{4})|" +
                                        "(\\d{4} \\d{4} \\d{4} \\d{4})|(\\d{4}\\d{4}\\d{4}\\d{4})");
                                if (!textField1.getText().matches("\\d{3}")) {
                                    poprawne = false;
                                }
                                if (!textField2.getText().matches("(0[1-9]|1[012])/2[3-6]")) {
                                    poprawne = false;
                                }else{
                                    String[] date = textField2.getText().split("/");
                                    if(date[0].matches("0[1-9]")){
                                        int month = date[0].charAt(1)-'0';
                                        String strYear = "20";
                                        int year = Integer.parseInt(strYear.concat(date[1]));
                                        if(month==12) year++;
                                        else month++;
                                        if(LocalDate.of(year,month,1).isBefore(LocalDate.now())){
                                            poprawne = false;
                                        }
                                    }
                                }
                                if (!poprawne) {
                                    JOptionPane.showMessageDialog(null, "Platnosc niepoprawna", "Informacja",
                                            JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Platnosc udana", "Informacja",
                                            JOptionPane.INFORMATION_MESSAGE);
                                    frame.dispose();
                                    try {
                                        var out = new ObjectOutputStream(new FileOutputStream("extent.bin"));
                                        ObjectPlus.writeExtents(out);
                                        out.close();
                                    } catch (Exception e3) {
                                        e3.printStackTrace();
                                    }
                                }
                            });
                            JButton button2 = new JButton("Anuluj");
                            button2.addActionListener(e1212 -> frame.dispose());
                            panel3.add(label4, BorderLayout.PAGE_START);
                            panel3.add(panel2, BorderLayout.CENTER);
                            JPanel panel4 = new JPanel();
                            panel4.setLayout(new FlowLayout());
                            panel4.add(button);
                            panel4.add(button2);
                            panel3.add(panel4, BorderLayout.PAGE_END);
                            dialog1.getContentPane().add(panel3);
                            dialog1.setVisible(true);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    });
                    JButton button4 = new JButton("Nie");
                    button4.addActionListener(e13 -> {
                        frame.setVisible(false);
                        frame.dispose();
                    });
                    panel5.add(label5);
                    panel5.add(label6);
                    panel5.add(scrollPane1);
                    JPanel panel6 = new JPanel();
                    panel6.setLayout(new FlowLayout());
                    panel6.add(button3);
                    panel6.add(button4);
                    panel5.add(panel6);
                    dialog.add(panel5);
                    dialog.pack();
                    dialog.setVisible(true);
                }else {
                    JOptionPane.showMessageDialog(null, "Niepoprawne dane biletu", "Informacja",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            });
            JButton button2 = new JButton("Anuluj");
            button2.addActionListener(e -> {
                int input = JOptionPane.showConfirmDialog(null, "Na pewno chcesz anulowac?");
                if(input==0){
                    frame.setVisible(false);
                    frame.dispose();
                }
            });
            JPanel panel1 = new JPanel();
            panel1.setLayout(new BoxLayout(panel1,BoxLayout.PAGE_AXIS));
            panel.add(iloscLabel);
            panel.add(iloscField);
            panel.add(rodzajLabel);
            panel.add(iloscField2);
            panel.add(wystawaLabel);
            JLabel label = new JLabel("Wybierz opcje biletu:",SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 15));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel1.add(label);
            panel1.add(panel);
            panel1.add(scrollPane);
            JPanel panel2 = new JPanel();
            panel2.setLayout(new FlowLayout());
            panel2.add(button1);
            panel2.add(button2);
            panel1.add(panel2);
            frame.add(panel1);
            frame.pack();
            frame.setVisible(true);
        }
    }
    public void setTypObiektu(TypObiektu typObiektu) {
        this.typObiektu = typObiektu;
    }

    public TypObiektu getTypObiektu() {
        return typObiektu;
    }
    public void zapiszSieDoGrupy(List<Bilet> bilety) {
        if(this.typObiektu == TypObiektu.ZWIEDZAJACY || this.typObiektu == TypObiektu.ZWIEDZAJACY_UCZESTNIK) {
            try {
                JFrame frame = new JFrame("Zapisz sie do grupy");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(572, 515);
                JPanel panel = new JPanel();
                JTable table = new JTable();
                table.setBounds(0, 0, 571, 381);
                JButton button = new JButton("Wybierz");
                ObjectPlusPlus[] placowki = this.getLinks("placowka");
                ObjectPlusPlus[] grupy = placowki[placowki.length - 1].getLinks("grupa");
                DefaultTableModel model = new DefaultTableModel();
                String[] columnNames = {"data", "miejsca"};
                model.setColumnIdentifiers(columnNames);
                for (var g : grupy) {
                    int zostalo = Grupa.getMaxLiczbaUczestnikow();
                    if (g.anyLink("bilet")) {
                        ObjectPlusPlus[] biletLinki = g.getLinks("bilet");
                        zostalo = Grupa.getMaxLiczbaUczestnikow() - biletLinki.length;
                    }
                    String[] row = new String[2];
                    row[0] = g.toString();
                    row[1] = String.valueOf(zostalo);
                    model.addRow(row);
                }
                table.setModel(model);
                JScrollPane scrollPane = new JScrollPane(table);
                button.addActionListener(e -> {
                    try {
                        int index = table.getSelectedRow();
                        int miejsca = Integer.parseInt(model.getValueAt(index, 1).toString()) - bilety.size();
                        if (miejsca < 0) {
                            JOptionPane.showMessageDialog(null, "Nie ma miejsca w tej grupie", "Informacja",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Udalo sie zapisac do grupy", "Informacja",
                                    JOptionPane.INFORMATION_MESSAGE);
                            for (Bilet bilet : bilety) {
                                grupy[index].addLink("bilet", "grupa", bilet);
                            }
                            frame.setVisible(false);
                            frame.dispose();
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                });
                panel.add(button);
                frame.getContentPane().add(BorderLayout.NORTH, scrollPane);
                frame.getContentPane().add(BorderLayout.SOUTH, panel);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public int getWiek() throws Exception {
        if(typObiektu==TypObiektu.UCZESTNIK || typObiektu==TypObiektu.ZWIEDZAJACY_UCZESTNIK){
            return (int) Math.floor(ChronoUnit.YEARS.between(LocalDate.now(),super.getDataUrodzenia()));
        }else{
            throw new Exception("Ta klasa nie jest Uczestnikiem");
        }
    }
}

