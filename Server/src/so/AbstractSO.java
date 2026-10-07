/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import dbb.DBBroker;
import domen.AbstractDomainObject;
import java.sql.SQLException;

/**
 *
 * @author Korisnik
 */
public abstract class AbstractSO {
      protected abstract void provjeriPreduslov(AbstractDomainObject ado) throws Exception;
    protected abstract void izvrsiKonkretnuOperaciju(AbstractDomainObject ado) throws Exception;

    public void izvrsi(AbstractDomainObject ado) throws Exception {
        try {
            provjeriPreduslov(ado);
            izvrsiKonkretnuOperaciju(ado);
            commit();
        } catch (Exception e) {
            rollback();
            throw e;
        }
    }

    public void commit() throws SQLException {
        DBBroker.getInstance().getConnection().commit();
    }

    public void rollback() throws SQLException {
        DBBroker.getInstance().getConnection().rollback();
    }
}
