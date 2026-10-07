/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.poslastica;

import dbb.DBBroker;
import domen.AbstractDomainObject;
import domen.Poslastica;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Korisnik
 */
public class SOVratiPoslastice extends AbstractSO{
    
    private ArrayList<Poslastica> lista;

    public ArrayList<Poslastica> getLista() {
        return lista;
    }
    
    

    @Override
    protected void provjeriPreduslov(AbstractDomainObject ado) throws Exception {
         if (!(ado instanceof Poslastica)) {
            throw new Exception("Proslijedjeni objekat nije instanca klase Poslastica!");
        }
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(AbstractDomainObject ado) throws Exception {
         ArrayList<AbstractDomainObject> poslastice = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Poslastica>) (ArrayList<?>) poslastice;
    }
    
}
