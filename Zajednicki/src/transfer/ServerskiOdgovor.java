/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transfer;

import java.io.Serializable;
import transfer.util.StatusOdgovora;

/**
 *
 * @author Korisnik
 */
public class ServerskiOdgovor implements Serializable{
    
    private Object data;
    private Exception exc;
    private StatusOdgovora statusOdgovora;

    public ServerskiOdgovor(Object data, Exception exc, StatusOdgovora statusOdgovora) {
        this.data = data;
        this.exc = exc;
        this.statusOdgovora = statusOdgovora;
    }

    public ServerskiOdgovor() {
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Exception getExc() {
        return exc;
    }

    public void setExc(Exception exc) {
        this.exc = exc;
    }

    public StatusOdgovora getStatusOdgovora() {
        return statusOdgovora;
    }

    public void setStatusOdgovora(StatusOdgovora statusOdgovora) {
        this.statusOdgovora = statusOdgovora;
    }
    
}
