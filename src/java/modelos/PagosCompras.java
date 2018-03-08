/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author User
 */
public class PagosCompras {
    private int id_pagocompra;
    private Compras compra;
    private Pagos pago;
    private int total_pago;

    public PagosCompras() {
    }

    public PagosCompras(int id_pagocompra, Compras compra, Pagos pago, int total_pago) {
        this.id_pagocompra = id_pagocompra;
        this.compra = compra;
        this.pago = pago;
        this.total_pago = total_pago;
    }

    public int getId_pagocompra() {
        return id_pagocompra;
    }

    public void setId_pagocompra(int id_pagocompra) {
        this.id_pagocompra = id_pagocompra;
    }

    public Compras getCompra() {
        return compra;
    }

    public void setCompra(Compras compra) {
        this.compra = compra;
    }

    public Pagos getPago() {
        return pago;
    }

    public void setPago(Pagos pago) {
        this.pago = pago;
    }

    public int getTotal_pago() {
        return total_pago;
    }

    public void setTotal_pago(int total_pago) {
        this.total_pago = total_pago;
    }
    
}
