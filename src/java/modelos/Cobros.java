/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author RNMG
 */
public class Cobros {
    private int id_cobro;
    private int total_cobro;
    private Ventas venta;
    private Pagos pago;

    public Cobros() {
    }

    public Cobros(int id_cobro, int total_cobro, Ventas venta, Pagos pago) {
        this.id_cobro = id_cobro;
        this.total_cobro = total_cobro;
        this.venta = venta;
        this.pago = pago;
    }

    public int getId_cobro() {
        return id_cobro;
    }

    public void setId_cobro(int id_cobro) {
        this.id_cobro = id_cobro;
    }

    public int getTotal_cobro() {
        return total_cobro;
    }

    public void setTotal_cobro(int total_cobro) {
        this.total_cobro = total_cobro;
    }

    public Ventas getVenta() {
        return venta;
    }

    public void setVenta(Ventas venta) {
        this.venta = venta;
    }

    public Pagos getPago() {
        return pago;
    }

    public void setPago(Pagos pago) {
        this.pago = pago;
    }

        
}
