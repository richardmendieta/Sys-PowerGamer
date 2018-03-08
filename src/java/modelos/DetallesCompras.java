/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

public class DetallesCompras {
    private int id_detallecompra;
    private int cantidad_productocompra;
    private int total;
    private Productos producto;
    private Compras compra;

    public DetallesCompras() {
    }

    public DetallesCompras(int id_detallecompra, int cantidad_productocompra, int total, Productos producto, Compras compra) {
        this.id_detallecompra = id_detallecompra;
        this.cantidad_productocompra = cantidad_productocompra;
        this.total = total;
        this.producto = producto;
        this.compra = compra;
    }

    public int getId_detallecompra() {
        return id_detallecompra;
    }

    public void setId_detallecompra(int id_detallecompra) {
        this.id_detallecompra = id_detallecompra;
    }

    public int getCantidad_productocompra() {
        return cantidad_productocompra;
    }

    public void setCantidad_productocompra(int cantidad_productocompra) {
        this.cantidad_productocompra = cantidad_productocompra;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public Compras getCompra() {
        return compra;
    }

    public void setCompra(Compras compra) {
        this.compra = compra;
    }

   
}
