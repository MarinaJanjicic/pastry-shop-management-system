/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeliTabela;

import domen.Administrator;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import kontroler.KlijentKontroler;

/**
 *
 * @author Korisnik
 */
public class ModelTabeleAdministrator extends AbstractTableModel implements Runnable{
    
    private ArrayList<Administrator> lista;
    private String[] kolone={"ID", "Ime", "Prezime", "Username"};
    private String parametar = "";
    
    public ModelTabeleAdministrator() {
        try {
            lista = KlijentKontroler.getInstance().vratiAdministratore();
        } catch (Exception ex) {
            Logger.getLogger(ModelTabeleAdministrator.class.getName()).log(Level.SEVERE, null, ex);
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
        Administrator a = lista.get(row);

        switch (column) {
            case 0:
                return a.getAdministratorID();
            case 1:
                return a.getIme();
            case 2:
                return a.getPrezime();
            case 3:
                return a.getUsername();

            default:
                return null;
        }
    }
    
     public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshujTabelu();
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(10000);
                refreshujTabelu();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ModelTabeleAdministrator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void refreshujTabelu() {
       try {
            lista = KlijentKontroler.getInstance().vratiAdministratore();
            if (!parametar.equals("")) {
                ArrayList<Administrator> novaLista = new ArrayList<>();
                for (Administrator a : lista) {
                    if (a.getIme().toLowerCase().contains(parametar.toLowerCase())
                            || a.getPrezime().toLowerCase().contains(parametar.toLowerCase())
                            || a.getUsername().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(a);
                    }
                }
                lista = novaLista;
            }

            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Administrator getSelectedAdministrator(int row) {
      return lista.get(row);
    }
    
}
