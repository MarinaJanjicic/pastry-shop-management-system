/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeliTabela;

import domen.StavkaRacuna;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Korisnik
 */
public class ModelTabeleStavke extends AbstractTableModel{
    
    private ArrayList<StavkaRacuna> lista;
    private String[] kolone = {"Rb", "Poslastica", "Kolicina", "Cena"};
    private int rb = 0;

    public ModelTabeleStavke() {
        lista = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int row, int column) {
        StavkaRacuna sr = lista.get(row);

        switch (column) {
            case 0:
                return sr.getRb();
            case 1:
                return sr.getPoslastica().getNaziv();
            case 2:
                return sr.getKolicina() + "kom";
            case 3:
                return sr.getCena() + "RSD";

            default:
                return null;
        }
    }

    public void dodajStavku(StavkaRacuna sr) {
            for (StavkaRacuna stavkaRacuna : lista) {
            if (stavkaRacuna.getPoslastica().getPoslasticaID().equals(sr.getPoslastica().getPoslasticaID())) {
                stavkaRacuna.setKolicina(stavkaRacuna.getKolicina() + sr.getKolicina());
                stavkaRacuna.setCena(stavkaRacuna.getCena() + sr.getCena());
                fireTableDataChanged();
                return;
            }
        }

        rb = lista.size();
        sr.setRb(++rb);
        lista.add(sr);
        fireTableDataChanged();
    }

    public double vratiCenu() {
        double cenaRacuna = 0;
        
        for (StavkaRacuna stavkaRacuna : lista) {
            cenaRacuna += stavkaRacuna.getCena();
        }
        
        return cenaRacuna;
    }

    public void obrisiStavku(int row) {
       lista.remove(row);

        int rb = 0;
        for (StavkaRacuna stavkaRacuna : lista) {
            stavkaRacuna.setRb(++rb);
        }

        fireTableDataChanged();
    }

    public ArrayList<StavkaRacuna> getLista() {
      return lista;
    }
    
}
