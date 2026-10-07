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
public class StavkaRacuna extends AbstractDomainObject{
    
    private Racun racun;
    private int rb;
    private int kolicina;
    private double cena;
    private Poslastica poslastica;

    public StavkaRacuna(Racun racun, int rb, int kolicina, double cena, Poslastica poslastica) {
        this.racun = racun;
        this.rb = rb;
        this.kolicina = kolicina;
        this.cena = cena;
        this.poslastica = poslastica;
    }

    public StavkaRacuna() {
    }

    public Racun getRacun() {
        return racun;
    }

    public void setRacun(Racun racun) {
        this.racun = racun;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Poslastica getPoslastica() {
        return poslastica;
    }

    public void setPoslastica(Poslastica poslastica) {
        this.poslastica = poslastica;
    }
    

    @Override
    public String nazivTabele() {
        return " StavkaRacuna ";
    }

    @Override
    public String alijas() {
       return " sr ";
    }

    @Override
    public String join() {
         return " JOIN POSLASTICA P ON (P.POSLASTICAID = SR.POSLASTICAID) "
                + "JOIN TIPPOSLASTICE TP ON (TP.TIPPOSLASTICEID = P.TIPPOSLASTICEID) "
                + "JOIN RACUN R ON (R.RACUNID = SR.RACUNID) "
                + "JOIN ADMINISTRATOR A ON (A.ADMINISTRATORID = R.ADMINISTRATORID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Administrator a = new Administrator(rs.getLong("AdministratorID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Username"), rs.getString("Password"));

            Racun r = new Racun(rs.getLong("racunID"), rs.getTimestamp("datumVreme"),
                    rs.getDouble("r.cena"), a, null);

            TipPoslastice tp = new TipPoslastice(rs.getLong("TipPoslasticeID"),
                    rs.getString("tp.Naziv"));

            Poslastica p = new Poslastica(rs.getLong("poslasticaID"), rs.getString("p.naziv"),
                    rs.getDouble("cenaPoKomadu"), rs.getString("opis"), tp, null);

            StavkaRacuna sr = new StavkaRacuna(r, rs.getInt("rb"), rs.getInt("kolicina"),
                    rs.getDouble("sr.cena"), p);

            lista.add(sr);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
       return " (racunID, rb, kolicina, cena, poslasticaID) ";
    }

    @Override
    public String uslov() {
        return " racunID = " + racun.getRacunID() + " AND RB = " + rb;
    }

    @Override
    public String vrednostiZaInsert() {
         return " " + racun.getRacunID() + ", " + rb + ", "
                + " " + kolicina + ", " + cena + ", " + poslastica.getPoslasticaID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslovZaSelect() {
        if (racun != null) {
            return " WHERE R.RACUNID = " + racun.getRacunID();
        }
        return " WHERE P.POSLASTICAID = " + poslastica.getPoslasticaID();
    }
    
}
