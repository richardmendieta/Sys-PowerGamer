/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Date;

/**
 *
 * @author Administrator
 */
public class Timbrados {
     
    private int id_timbrado;
       private String numero_timbrado;
    private Date fecha_inicio;
    private Date fecha_vencimiento;
    private Date fecha_actual;
    private int desde_timbrado;
    private int hasta_timbrado;
 
    private Establecimientos establecimiento;
    private Puestos puesto;

    public Timbrados() {
    }

    public int getId_timbrado() {
        return id_timbrado;
    }

    public void setId_timbrado(int id_timbrado) {
        this.id_timbrado = id_timbrado;
    }

    public String getNumero_timbrado() {
        return numero_timbrado;
    }

    public void setNumero_timbrado(String numero_timbrado) {
        this.numero_timbrado = numero_timbrado;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(Date fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public Date getFecha_actual() {
        return fecha_actual;
    }

    public void setFecha_actual(Date fecha_actual) {
        this.fecha_actual = fecha_actual;
    }

    public int getDesde_timbrado() {
        return desde_timbrado;
    }

    public void setDesde_timbrado(int desde_timbrado) {
        this.desde_timbrado = desde_timbrado;
    }

    public int getHasta_timbrado() {
        return hasta_timbrado;
    }

    public void setHasta_timbrado(int hasta_timbrado) {
        this.hasta_timbrado = hasta_timbrado;
    }

    public Establecimientos getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(Establecimientos establecimiento) {
        this.establecimiento = establecimiento;
    }

    public Puestos getPuesto() {
        return puesto;
    }

    public void setPuesto(Puestos puesto) {
        this.puesto = puesto;
    }

    public Timbrados(int id_timbrado, String numero_timbrado, Date fecha_inicio, Date fecha_vencimiento, Date fecha_actual, int desde_timbrado, int hasta_timbrado, Establecimientos establecimiento, Puestos puesto) {
        this.id_timbrado = id_timbrado;
        this.numero_timbrado = numero_timbrado;
        this.fecha_inicio = fecha_inicio;
        this.fecha_vencimiento = fecha_vencimiento;
        this.fecha_actual = fecha_actual;
        this.desde_timbrado = desde_timbrado;
        this.hasta_timbrado = hasta_timbrado;
        this.establecimiento = establecimiento;
        this.puesto = puesto;
    }

 
}
