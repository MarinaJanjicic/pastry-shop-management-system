/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeliTabela;

import domen.TipPoslastice;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import kontroler.KlijentKontroler;

/**
 *
 * @author Korisnik
 */
public class ModelTabeleTipPoslastice extends AbstractTableModel implements Runnable{

    private ArrayList<TipPoslastice> lista;
    private String[] kolone = {"ID", "Naziv"};
    private String parametar = "";
    
    public ModelTabeleTipPoslastice() {
        try {
            lista = KlijentKontroler.getInstance().vratiTipovePoslastice();
        } catch (Exception ex) {
            Logger.getLogger(ModelTabeleTipPoslastice.class.getName()).log(Level.SEVERE, null, ex);
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
        TipPoslastice tp = lista.get(row);

        switch (column) {
            case 0:
                return tp.getTipPoslasticeID();
            case 1:
                return tp.getNaziv();

            default:
                return null;
        }
    }
    
     @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(10000);
                refreshujTabelu();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ModelTabeleTipPoslastice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

 
    
    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshujTabelu();
    }

    public void refreshujTabelu() {
        try {
            lista = KlijentKontroler.getInstance().vratiTipovePoslastice();
            if (!parametar.equals("")) {
                ArrayList<TipPoslastice> novaLista = new ArrayList<>();
                for (TipPoslastice tp : lista) {
                    if (tp.getNaziv().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(tp);
                    }
                }
                lista = novaLista;
            }

            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public TipPoslastice getSelectedTipPoslastice(int row) {
        return lista.get(row);
    }
    
}
