/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.sastojak;

import dbb.DBBroker;
import domen.AbstractDomainObject;
import domen.Sastojak;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Korisnik
 */
public class SOVratiSastojke extends AbstractSO{
    
    private ArrayList<Sastojak> lista;

    public ArrayList<Sastojak> getLista() {
        return lista;
    }
    
    

    @Override
    protected void provjeriPreduslov(AbstractDomainObject ado) throws Exception {
         if (!(ado instanceof Sastojak)) {
            throw new Exception("Proslijedjeni objekat nije instanca klase Sastojak!");
        }
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> sastojci = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Sastojak>) (ArrayList<?>) sastojci;
    }
    
}
