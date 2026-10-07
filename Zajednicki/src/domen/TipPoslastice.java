/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Korisnik
 */
public class TipPoslastice extends AbstractDomainObject{
    
    private Long tipPoslasticeID;
    private String naziv;

    @Override
    public String toString() {
        return naziv;
    }

    public TipPoslastice() {
    }

    public TipPoslastice(Long tipPoslasticeID, String naziv) {
        this.tipPoslasticeID = tipPoslasticeID;
        this.naziv = naziv;
    }

    public Long getTipPoslasticeID() {
        return tipPoslasticeID;
    }

    public void setTipPoslasticeID(Long tipPoslasticeID) {
        this.tipPoslasticeID = tipPoslasticeID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
   

    @Override
    public String nazivTabele() {
        return " TipPoslastice ";
    }

    @Override
    public String alijas() {
        return " tp ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
         ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            TipPoslastice tp = new TipPoslastice(rs.getLong("TipPoslasticeID"),
                    rs.getString("Naziv"));

            lista.add(tp);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Naziv) ";
    }

    @Override
    public String uslov() {
       return " TipPoslasticeID = " + tipPoslasticeID;
    }

    @Override
    public String vrednostiZaInsert() {
         return " '" + naziv + "' ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " Naziv = '" + naziv + "' ";
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }
    
}
