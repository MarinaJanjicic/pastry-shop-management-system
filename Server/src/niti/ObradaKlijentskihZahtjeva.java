/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import domen.Administrator;
import domen.Poslastica;
import domen.Racun;
import domen.TipPoslastice;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import logika.Kontroler;
import transfer.KlijentskiZahtjev;
import transfer.ServerskiOdgovor;
import transfer.util.Operacije;
import transfer.util.StatusOdgovora;

/**
 *
 * @author Korisnik
 */
public class ObradaKlijentskihZahtjeva extends Thread{
    
    private Socket socket;

    ObradaKlijentskihZahtjeva(Socket socket) {
        this.socket=socket;
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                KlijentskiZahtjev kz =  (KlijentskiZahtjev) in.readObject();
                ServerskiOdgovor so = obradiZahtjev(kz);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(so);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ServerskiOdgovor obradiZahtjev(KlijentskiZahtjev kz) {
       ServerskiOdgovor so=new ServerskiOdgovor(null, null, StatusOdgovora.Uspjesno);
       try {
            switch (kz.getOperacija()) {
                case Operacije.LOGIN:
                    Administrator administrator=(Administrator) kz.getData();
                    Administrator a=Kontroler.getInstance().login(administrator);
                    Kontroler.getInstance().setUlogovani(a);
                    so.setData(a);
                    break;
                case Operacije.DODAJ_ADMINISTRATORA:
                    Administrator novi=(Administrator) kz.getData();
                    Kontroler.getInstance().dodajAdministratora(novi);
                    break;
                case Operacije.VRATI_ADMINISTRATORE:
                    so.setData(Kontroler.getInstance().vratiAdministratore());
                    break;
                case Operacije.OBRISI_ADMINISTRATORA:
                    Administrator brisanje=(Administrator) kz.getData();
                    Kontroler.getInstance().obrisiAdministratora(brisanje);
                    break;
                case Operacije.IZMJENI_ADMINISTRATORA:
                    Administrator izmjena=(Administrator) kz.getData();
                    Kontroler.getInstance().izmjeniAdministratora(izmjena);
                    break;
                case Operacije.DODAJ_TIP_POSLASTICE:
                    TipPoslastice tipPoslastice=(TipPoslastice) kz.getData();
                    Kontroler.getInstance().dodajTipPoslastice(tipPoslastice);
                    break;
                case Operacije.VRATI_TIPOVE_POSLASTICA:
                    so.setData(Kontroler.getInstance().vratiTipovePoslastica());
                    break;
                case Operacije.OBRISI_TIP_POSLASTICE:
                    TipPoslastice tp=(TipPoslastice) kz.getData();
                    Kontroler.getInstance().obristiTipPoslastice(tp);
                    break;
                case Operacije.IZMJENI_TIP_POSLASTICE:
                    TipPoslastice tip=(TipPoslastice) kz.getData();
                    Kontroler.getInstance().izmjeniTipPoslastice(tip);
                    break;
                case Operacije.DODAJ_POSLASTICU:
                    Poslastica poslastica=(Poslastica) kz.getData();
                    Kontroler.getInstance().dodajPoslasticu(poslastica);
                    break;
                case Operacije.VRATI_POSLASTICE:
                    TipPoslastice tipP=(TipPoslastice) kz.getData();
                    so.setData(Kontroler.getInstance().vratiPoslastice(tipP));
                    break;
                case Operacije.IZMENI_POSLASTICU:
                    Poslastica posl=(Poslastica) kz.getData();
                    Kontroler.getInstance().izmeniPoslasticu(posl);
                    break;
                case Operacije.VRATI_SASTOJKE:
                    Poslastica slastica=(Poslastica) kz.getData();
                    so.setData(Kontroler.getInstance().vratiSastojke(slastica));
                    break;
                case Operacije.SACUVAJ_RACUN:
                    Racun racun=(Racun) kz.getData();
                    Kontroler.getInstance().sacuvajRacun(racun);
                    break;
                case Operacije.ODJAVA:
                    Administrator odjava=(Administrator) kz.getData();
                    Kontroler.getInstance().odjava(odjava);
                    break;
             
            }
        } catch (Exception e) {
            so.setStatusOdgovora(StatusOdgovora.Greska);
            so.setExc(e);
        }
       return so;
    }
    
    
    
    
    
}
