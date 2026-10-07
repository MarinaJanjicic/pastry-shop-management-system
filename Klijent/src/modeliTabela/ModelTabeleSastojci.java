/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeliTabela;

import domen.Poslastica;
import domen.Sastojak;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import kontroler.KlijentKontroler;

/**
 *
 * @author Korisnik
 */
public class ModelTabeleSastojci extends AbstractTableModel{
    private ArrayList<Sastojak> lista;
    private String[] kolone = {"Rb", "Sastojak"};
    private int rb = 0;

    public ModelTabeleSastojci() {
        lista = new ArrayList<>();
    }

    public ModelTabeleSastojci(Poslastica p) {
        try {
            lista = KlijentKontroler.getInstance().vratiSastojke(p);
        } catch (Exception ex) {
            Logger.getLogger(ModelTabeleSastojci.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        Sastojak s = lista.get(row);

        switch (column) {
            case 0:
                return s.getRb();
            case 1:
                return s.getNaziv();

            default:
                return null;
        }
    }

    public void dodajSastojak(Sastojak s) {
       rb=lista.size();
       s.setRb(++rb);
       lista.add(s);
       fireTableDataChanged();
    }

    public void obrisiSastojak(int row) {
       lista.remove(row);
       
       int rb=0;
       for(Sastojak sastojak:lista){
           sastojak.setRb(++rb);
       }
       
       fireTableDataChanged();
    }

    public ArrayList<Sastojak> getLista() {
       return lista;
    }
    
}
