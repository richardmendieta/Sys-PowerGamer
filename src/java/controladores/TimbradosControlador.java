/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelos.Establecimientos;
import modelos.Puestos;
import modelos.Timbrados;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author Administrator
 */
public class TimbradosControlador {
   public static boolean agregar(Timbrados timbrado) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into timbrados(numero_timbrado,fecha_inicio,fecha_vencimiento,fecha_actual,desde_timbrado,hasta_timbrado,id_establecimiento, id_puesto) "
                    + "values('" + timbrado.getNumero_timbrado() + "','"
                    + timbrado.getFecha_inicio() + "','"
                    + timbrado.getFecha_vencimiento() + "','"
                    + timbrado.getFecha_actual() + "',"
                    + timbrado.getDesde_timbrado() + ","
                    + timbrado.getHasta_timbrado() + ","
                    + timbrado.getEstablecimiento().getId_establecimiento() + ","
                    + timbrado.getPuesto().getId_puesto() + ")";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static Timbrados buscarId(Timbrados timbrado) {
        if (Conexion.conectar()) {
            String sql = "select * from timbrados t,puestos p,establecimientos e "
                    + " where t.id_puesto=p.id_puesto and t.id_establecimiento=e.id_establecimiento and "
                    + "id_timbrado='" + timbrado.getId_timbrado() + "'";
            
            System.out.println("sql" + sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                   
                    timbrado.setNumero_timbrado(rs.getString("numero_timbrado"));
                    timbrado.setFecha_inicio(rs.getDate("fecha_inicio"));
                    timbrado.setFecha_vencimiento(rs.getDate("fecha_vencimiento"));
                    timbrado.setFecha_actual(rs.getDate("fecha_actual"));
                    timbrado.setDesde_timbrado(rs.getInt("desde_timbrado"));
                    timbrado.setHasta_timbrado(rs.getInt("hasta_timbrado"));
                    Puestos puesto = new Puestos();
                    puesto.setId_puesto(rs.getInt("id_puesto"));
                    puesto.setNombre_puesto(rs.getString("nombre_puesto"));
                    timbrado.setPuesto(puesto);
                    
                    Establecimientos establecimiento = new Establecimientos();
                    establecimiento.setId_establecimiento(rs.getInt("id_establecimiento"));
                    establecimiento.setNombre_establecimiento(rs.getString("nombre_establecimiento"));
                    timbrado.setEstablecimiento(establecimiento);
                    
                } else {
                    timbrado.setId_timbrado(0);
                    timbrado.setNumero_timbrado("");
                   timbrado.setDesde_timbrado(0);
                    timbrado.setHasta_timbrado(0);
                    
                    java.sql.Date fecha_inicio = new java.sql.Date(new java.util.Date().getTime());
                    timbrado.setFecha_inicio(fecha_inicio);
                    java.sql.Date fecha_vencimiento = new java.sql.Date(new java.util.Date().getTime());
                    timbrado.setFecha_vencimiento(fecha_vencimiento);
                    java.sql.Date fecha_actual = new java.sql.Date(new java.util.Date().getTime());
                    timbrado.setFecha_actual(fecha_actual);
                    
                    Puestos puesto = new Puestos();
                    puesto.setId_puesto(0);
                    puesto.setNombre_puesto("");
                    timbrado.setPuesto(puesto);
                    
                    Establecimientos establecimiento=new Establecimientos();
                    establecimiento.setId_establecimiento(0);
                    establecimiento.setNombre_establecimiento("");
                    timbrado.setEstablecimiento(establecimiento);
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return timbrado;
    }
    

    public static String buscarNumero(String numero, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from timbrados where upper(numero_timbrado) like '%" + numero.toUpperCase() + "%'"
                        + " order by id_timbrado offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_timbrado") + "</td>"
                                + "<td>" + rs.getString("numero_timbrado") + "</td>"
                                + "<td>" + rs.getDate("fecha_inicio") + "</td>"
                                + "<td>" + rs.getDate("fecha_vencimiento") + "</td>"
                                + "<td>" + rs.getDate("fecha_actual") + "</td>"
                                + "<td>" + rs.getInt("desde_timbrado") + "</td>"
                                + "<td>" + rs.getInt("hasta_timbrado") + "</td>"
                                
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

    public static boolean eliminar(Timbrados timbrado) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from timbrados where id_timbrado=" + timbrado.getId_timbrado();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static boolean modificar(Timbrados timbrado) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update timbrados set numero_timbrado='" + timbrado.getNumero_timbrado() + "',"
                    + "fecha_inicio='" + timbrado.getFecha_inicio() + "',"
                    + "fecha_vencimiento='" + timbrado.getFecha_vencimiento() + "',"
                    + "fecha_actual='" + timbrado.getFecha_actual() + "',"
                    + "desde_timbrado='" + timbrado.getDesde_timbrado() + "',"
                    + "hasta_timbrado='" + timbrado.getHasta_timbrado() + "',"
                    + "id_puesto=" + timbrado.getPuesto().getId_puesto() + ","
                    + "id_establecimiento=" + timbrado.getEstablecimiento().getId_establecimiento() + ""
                    + " where id_timbrado=" + timbrado.getId_timbrado();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }
}
