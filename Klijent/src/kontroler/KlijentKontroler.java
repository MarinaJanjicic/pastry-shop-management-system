/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Administrator;
import domen.Poslastica;
import domen.Racun;
import domen.Sastojak;
import domen.TipPoslastice;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import sesija.Session;
import transfer.KlijentskiZahtjev;
import transfer.ServerskiOdgovor;
import transfer.util.Operacije;
import transfer.util.StatusOdgovora;

/**
 *
 * @author Korisnik
 */
public class KlijentKontroler {
    
    private static KlijentKontroler instance;

    private KlijentKontroler() {
    }
    
     public static KlijentKontroler getInstance() {
        if (instance == null) {
            instance = new KlijentKontroler();
        }
        return instance;
    }
    
     
    private Object posaljiZahtjev(int operacija, Object data) throws Exception {
         KlijentskiZahtjev kz=new KlijentskiZahtjev(operacija, data);

        ObjectOutputStream out = new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(kz);

        ObjectInputStream in = new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        ServerskiOdgovor so =  (ServerskiOdgovor) in.readObject();

        if (so.getStatusOdgovora().equals(StatusOdgovora.Greska)) {
            throw so.getExc();
        } else {
            return so.getData();
        }

    }

    public Administrator login(Administrator a) throws Exception {
        return (Administrator) posaljiZahtjev(Operacije.LOGIN, a);
    }

    public void dodajAdministratora(Administrator novi) throws Exception {
        posaljiZahtjev(Operacije.DODAJ_ADMINISTRATORA, novi);
    }

    public ArrayList<Administrator> vratiAdministratore() throws Exception {
        return (ArrayList<Administrator>) posaljiZahtjev(Operacije.VRATI_ADMINISTRATORE, null);
    }

    public void obrisiAdministratora(Administrator a) throws Exception {
        posaljiZahtjev(Operacije.OBRISI_ADMINISTRATORA, a);
    }

    public void izmeniAdministratora(Administrator a) throws Exception {
        posaljiZahtjev(Operacije.IZMJENI_ADMINISTRATORA, a);
    }

    public void dodajTipPoslastice(TipPoslastice tp) throws Exception {
        posaljiZahtjev(Operacije.DODAJ_TIP_POSLASTICE, tp);
    }

    public ArrayList<TipPoslastice> vratiTipovePoslastice() throws Exception {
       return (ArrayList<TipPoslastice>) posaljiZahtjev(Operacije.VRATI_TIPOVE_POSLASTICA, null);
    }

    public void obrisiTipPoslastice(TipPoslastice tp) throws Exception {
        posaljiZahtjev(Operacije.OBRISI_TIP_POSLASTICE, tp);
    }

    public void izmjeniTipPoslastice(TipPoslastice tp) throws Exception {
        posaljiZahtjev(Operacije.IZMJENI_TIP_POSLASTICE, tp);
    }

    

    public void dodajPoslasticu(Poslastica nova) throws Exception {
        posaljiZahtjev(Operacije.DODAJ_POSLASTICU, nova);
    }

    public ArrayList<Poslastica> vratiPoslastice(TipPoslastice tp) throws Exception {
        return (ArrayList<Poslastica>) posaljiZahtjev(Operacije.VRATI_POSLASTICE, tp);
    }

    public void izmeniPoslasticu(Poslastica p) throws Exception {
        posaljiZahtjev(Operacije.IZMENI_POSLASTICU, p);
    }

    public ArrayList<Sastojak> vratiSastojke(Poslastica p) throws Exception {
       return (ArrayList<Sastojak>) posaljiZahtjev(Operacije.VRATI_SASTOJKE, p);
    }

    public void sacuvajRacun(Racun r) throws Exception {
        posaljiZahtjev(Operacije.SACUVAJ_RACUN, r);
    }

    public void odjava(Administrator ulogovani) throws Exception {
        posaljiZahtjev(Operacije.ODJAVA, ulogovani);
    }

  
    
    
}
