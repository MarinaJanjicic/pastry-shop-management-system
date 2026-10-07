/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.administrator;

import dbb.DBBroker;
import domen.AbstractDomainObject;
import domen.Administrator;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Korisnik
 */
public class SOVratiAdministratore extends AbstractSO{
    
     private ArrayList<Administrator> lista;

    @Override
    protected void provjeriPreduslov(AbstractDomainObject ado) throws Exception {
         if (!(ado instanceof Administrator)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Administrator!");
        }
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> administratori = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Administrator>) (ArrayList<?>) administratori;
    }

    public ArrayList<Administrator> getLista() {
        return lista;
    }
    
    
    
}
