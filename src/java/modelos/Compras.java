/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Date;


public class Compras {
    private int id_compra;
    private String numero_factura_compra;
    private String ruc_compra;
    private Date fecha_compra;
    private String timbrado_compra;
    private int total_pagar;
    private Proveedores proveedor;
    private String estado_compra;

    public Compras() {
    }

    public Compras(int id_compra, String numero_factura_compra, String ruc_compra, Date fecha_compra, String timbrado_compra, int total_pagar, Proveedores proveedor, String estado_compra) {
        this.id_compra = id_compra;
        this.numero_factura_compra = numero_factura_compra;
        this.ruc_compra = ruc_compra;
        this.fecha_compra = fecha_compra;
        this.timbrado_compra = timbrado_compra;
        this.total_pagar = total_pagar;
        this.proveedor = proveedor;
        this.estado_compra = estado_compra;
    }

    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }

    public String getNumero_factura_compra() {
        return numero_factura_compra;
    }

    public void setNumero_factura_compra(String numero_factura_compra) {
        this.numero_factura_compra = numero_factura_compra;
    }

    public String getRuc_compra() {
        return ruc_compra;
    }

    public void setRuc_compra(String ruc_compra) {
        this.ruc_compra = ruc_compra;
    }

    public Date getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(Date fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public String getTimbrado_compra() {
        return timbrado_compra;
    }

    public void setTimbrado_compra(String timbrado_compra) {
        this.timbrado_compra = timbrado_compra;
    }

    public int getTotal_pagar() {
        return total_pagar;
    }

    public void setTotal_pagar(int total_pagar) {
        this.total_pagar = total_pagar;
    }

    public Proveedores getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedores proveedor) {
        this.proveedor = proveedor;
    }

    public String getEstado_compra() {
        return estado_compra;
    }

    public void setEstado_compra(String estado_compra) {
        this.estado_compra = estado_compra;
    }
 
}
