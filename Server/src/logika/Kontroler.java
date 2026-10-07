/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logika;

import dbb.DBBroker;
import domen.Administrator;
import domen.Poslastica;
import domen.Racun;
import domen.Sastojak;
import domen.TipPoslastice;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import so.administrator.SODodajAdministratora;
import so.administrator.SOIzmjeniAdministratora;
import so.administrator.SOObrisiAdministratora;
import so.administrator.SOVratiAdministratore;
import so.login.SOLogin;
import so.poslastica.SODodajPoslasticu;
import so.poslastica.SOIzmeniPoslasticu;
import so.poslastica.SOVratiPoslastice;
import so.racun.SOSacuvajRacun;
import so.sastojak.SOVratiSastojke;
import so.tipPoslastice.SODodajTipPoslastice;
import so.tipPoslastice.SOIzmjeniTipPoslastice;
import so.tipPoslastice.SOObrisiTipPoslastice;
import so.tipPoslastice.SOVratiTipovePoslastica;

/**
 *
 * @author Korisnik
 */
public class Kontroler {
    
    private static Kontroler instance;
    private ArrayList<Administrator> ulogovaniAdministratori = new ArrayList<>();
    private Administrator ulogovani;

    public Administrator getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Administrator ulogovani) {
        this.ulogovani = ulogovani;
    }
    
    
    
    private Kontroler() {
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public ArrayList<Administrator> getUlogovaniAdministratori() {
        return ulogovaniAdministratori;
    }

   
    public Administrator login(Administrator administrator) throws Exception {
        SOLogin so=new SOLogin();
        so.izvrsi(administrator);
        return so.getUlogovani();
    }

    public void dodajAdministratora(Administrator novi) throws Exception {
        (new SODodajAdministratora()).izvrsi(novi);
    }

    public ArrayList<Administrator> vratiAdministratore() throws Exception {
        SOVratiAdministratore so=new SOVratiAdministratore();
        so.izvrsi(new Administrator());
        return so.getLista();
    }

    public void obrisiAdministratora(Administrator brisanje) throws Exception {
        (new SOObrisiAdministratora()).izvrsi(brisanje);
    }

    public void izmjeniAdministratora(Administrator izmjena) throws Exception {
       (new SOIzmjeniAdministratora()).izvrsi(izmjena);
    }

    public void dodajTipPoslastice(TipPoslastice tipPoslastice) throws Exception {
        (new SODodajTipPoslastice()).izvrsi(tipPoslastice);
    }

    public ArrayList<TipPoslastice> vratiTipovePoslastica() throws Exception {
        SOVratiTipovePoslastica so=new SOVratiTipovePoslastica();
        so.izvrsi(new TipPoslastice());
        return so.getLista();
    }

    public void obristiTipPoslastice(TipPoslastice tp) throws Exception {
        (new SOObrisiTipPoslastice()).izvrsi(tp);
    }

    public void izmjeniTipPoslastice(TipPoslastice tip) throws Exception {
       (new SOIzmjeniTipPoslastice()).izvrsi(tip);
    }

    public void dodajPoslasticu(Poslastica poslastica) throws Exception {
        (new SODodajPoslasticu()).izvrsi(poslastica);
    }

    public ArrayList<Poslastica> vratiPoslastice(TipPoslastice tp) throws Exception {
        SOVratiPoslastice so=new SOVratiPoslastice();
        
        Poslastica p=new Poslastica();
        p.setTipPoslastice(tp);
        
        so.izvrsi(p);
        return so.getLista();
    }

    public void izmeniPoslasticu(Poslastica posl) throws Exception {
       (new SOIzmeniPoslasticu()).izvrsi(posl);
    }

    public ArrayList<Sastojak> vratiSastojke(Poslastica slastica) throws Exception {
        SOVratiSastojke so=new SOVratiSastojke();
        
        Sastojak s=new Sastojak();
        s.setPoslastica(slastica);
        
        so.izvrsi(s);
        return so.getLista();
    }

    public void sacuvajRacun(Racun racun) throws Exception {
        (new SOSacuvajRacun()).izvrsi(racun);
    }

    public void odjava(Administrator odjava) {
        ulogovaniAdministratori.remove(odjava);
    }

    public ArrayList<Racun> vratiRacune(Administrator a) {
        try {
            return DBBroker.getInstance().vratiRacune(a);
        } catch (SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
