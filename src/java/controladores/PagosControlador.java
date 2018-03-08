/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelos.Pagos;
import utiles.Conexion;
import utiles.Utiles;


/**
 *
 * @author Administrator
 */
public class PagosControlador {

    public static boolean agregar(Pagos pago) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into pagos(tipo_pago) "
                    + "values('" + pago.getTipo_pago()+
          "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static Pagos buscarId(Pagos pago) {
        if (Conexion.conectar()) {
            String sql = "select * from pagos where id_pago='" + pago.getId_pago() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    pago.setTipo_pago(rs.getString("tipo_pago"));
                } else {
                    pago.setId_pago(0);
                    pago.setTipo_pago("");
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return pago;
    }

    public static String buscarNombre(String tipo, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from pagos where upper(tipo_pago) like '%" + tipo.toUpperCase() + "%'"
                        + " order by id_pago offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_pago") + "</td>"
                                + "<td>" + rs.getString("tipo_pago") + "</td>"
                                
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td colspan=2>No existen registros...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                } catch (SQLException ex) {
                    System.err.println("Error:" + ex);
                }
                Conexion.cerrar();
            } catch (Exception ex) {
                System.err.println("Error: " + ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }
    
    public static boolean eliminar(Pagos pago){
        boolean valor=false;
        if(Conexion.conectar()){
            String sql="delete from pagos where id_pago="+pago.getId_pago();
            try{
                Conexion.getSt().executeUpdate(sql);
                valor=true;
            }catch(SQLException ex){
                System.err.println("Error:"+ex);
            }
        }
        return valor;
    }
    public static boolean modificar(Pagos pago){
        boolean valor=false;
        if(Conexion.conectar()){
            String sql="update pagos set tipo_pago='"
                    +pago.getTipo_pago()+"'"
                    
                    +" where id_pago="+pago.getId_pago();
            try{
                Conexion.getSt().executeUpdate(sql);
                valor=true;
            }catch(SQLException ex){
                System.err.println("Error:"+ex);
            }
        }
        return valor;
    }
}

