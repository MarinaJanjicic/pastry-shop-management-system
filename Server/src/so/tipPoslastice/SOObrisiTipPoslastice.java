/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.tipPoslastice;

import dbb.DBBroker;
import domen.AbstractDomainObject;
import domen.TipPoslastice;
import so.AbstractSO;

/**
 *
 * @author Korisnik
 */
public class SOObrisiTipPoslastice extends AbstractSO{

    @Override
    protected void provjeriPreduslov(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof TipPoslastice)) {
            throw new Exception("Proslijedjeni objekat nije instanca klase TipPoslastice!");
        }
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }
    
}
