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
public class Poslastica extends AbstractDomainObject
{
    
    private Long poslasticaID;
    private String naziv;
    private double cenaPoKomadu;
    private String opis;
    private TipPoslastice tipPoslastice;
    private ArrayList<Sastojak> sastojci;

    public Poslastica(Long poslasticaID, String naziv, double cenaPoKomadu, String opis, 
            TipPoslastice tipPoslastice, ArrayList<Sastojak> sastojci) {
        this.poslasticaID = poslasticaID;
        this.naziv = naziv;
        this.cenaPoKomadu = cenaPoKomadu;
        this.opis = opis;
        this.tipPoslastice = tipPoslastice;
        this.sastojci = sastojci;
    }

    public Poslastica() {
    }
    
     @Override
    public String toString() {
        return naziv + " (Cijena po komadu: " + cenaPoKomadu + "RSD)";
    }

    public Long getPoslasticaID() {
        return poslasticaID;
    }

    public void setPoslasticaID(Long poslasticaID) {
        this.poslasticaID = poslasticaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getCenaPoKomadu() {
        return cenaPoKomadu;
    }

    public void setCenaPoKomadu(double cenaPoKomadu) {
        this.cenaPoKomadu = cenaPoKomadu;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public TipPoslastice getTipPoslastice() {
        return tipPoslastice;
    }

    public void setTipPoslastice(TipPoslastice tipPoslastice) {
        this.tipPoslastice = tipPoslastice;
    }

    public ArrayList<Sastojak> getSastojci() {
        return sastojci;
    }

    public void setSastojci(ArrayList<Sastojak> sastojci) {
        this.sastojci = sastojci;
    }
    
    

    @Override
    public String nazivTabele() {
        return " Poslastica ";
    }

    @Override
    public String alijas() {
        return " p ";
    }

    @Override
    public String join() {
        return " JOIN TIPPOSLASTICE TP ON (TP.TIPPOSLASTICEID = P.TIPPOSLASTICEID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista=new ArrayList<>();
        
        while(rs.next()){
             TipPoslastice tp = new TipPoslastice(rs.getLong("TipPoslasticeID"),
                    rs.getString("tp.Naziv"));

            Poslastica p = new Poslastica(rs.getLong("poslasticaID"), rs.getString("p.naziv"),
                    rs.getDouble("cenaPoKomadu"), rs.getString("opis"), tp, null);

            lista.add(p);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (naziv, cenaPoKomadu, opis, TipPoslasticeID) ";
    }

    @Override
    public String uslov() {
        return " poslasticaID = " + poslasticaID;
    }

    @Override
    public String vrednostiZaInsert() {
          return "'" + naziv + "', " + cenaPoKomadu + ", "
                + "'" + opis + "', " + tipPoslastice.getTipPoslasticeID() + " ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " naziv = '" + naziv + "', cenaPoKomadu = " + cenaPoKomadu + ", "
                + "opis = '" + opis + "', "
                + "tipPoslasticeID = " + tipPoslastice.getTipPoslasticeID() + " ";
    }

    @Override
    public String uslovZaSelect() {
      if(tipPoslastice == null){
            return "";
        }
        return " WHERE TP.TIPPOSLASTICEID = " + tipPoslastice.getTipPoslasticeID();
    }
    
}
