/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import dbb.DBBroker;
import domen.BrojPoslastica;
import domen.TipPoslastice;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Korisnik
 */
public class ModelTabeleBrojPoslastica extends AbstractTableModel implements Runnable
{    
    private ArrayList<BrojPoslastica> lista;
    private String[] kolone={"Naziv tipa poslastice","Broj poslastica"};
    
    public ModelTabeleBrojPoslastica(){
        try {
            lista=DBBroker.getInstance().vratiBrojPoslastica();
        } catch (SQLException ex) {
            Logger.getLogger(ModelTabeleBrojPoslastica.class.getName()).log(Level.SEVERE, null, ex);
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
        BrojPoslastica bp=lista.get(row);

        switch (column) {
            case 0:
                return bp.getNazivTipa();
            case 1:
                return bp.getBrojPoslastica();

            default:
                return null;
        }
    }

   @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                lista=DBBroker.getInstance().vratiBrojPoslastica();
                Thread.sleep(10000);
                fireTableDataChanged();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ModelTabeleBrojPoslastica.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ModelTabeleBrojPoslastica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
