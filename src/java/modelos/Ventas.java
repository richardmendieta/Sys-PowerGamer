/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Date;


public class Ventas {
    private int id_venta;
    private Date fecha_venta;
    private String estado_venta;
    private Clientes cliente;
    private int numero_factura;
    private Timbrados timbrado;

    public Ventas() {
    }

    public Ventas(int id_venta, Date fecha_venta, String estado_venta, Clientes cliente, int numero_factura, Timbrados timbrado) {
        this.id_venta = id_venta;
        this.fecha_venta = fecha_venta;
        this.estado_venta = estado_venta;
        this.cliente = cliente;
        this.numero_factura = numero_factura;
        this.timbrado = timbrado;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public Date getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(Date fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public String getEstado_venta() {
        return estado_venta;
    }

    public void setEstado_venta(String estado_venta) {
        this.estado_venta = estado_venta;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public int getNumero_factura() {
        return numero_factura;
    }

    public void setNumero_factura(int numero_factura) {
        this.numero_factura = numero_factura;
    }

    public Timbrados getTimbrado() {
        return timbrado;
    }

    public void setTimbrado(Timbrados timbrado) {
        this.timbrado = timbrado;
    }

   
}
