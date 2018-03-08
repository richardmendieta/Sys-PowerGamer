/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelos.Formularios;
import modelos.Menus;
import utiles.Conexion;
import utiles.Utiles;
//import java.sql.Statement;




/**
 *
 * @author Administrator
 */
public class FormulariosControlador {

    public static boolean agregar(Formularios formulario) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into formularios(nombre_formulario,codigo_formulario,id_menu) "
                    + "values('" + formulario.getNombre_formulario() + "','"
                     + formulario.getCodigo_formulario() + "','"
                    + formulario.getMenu().getId_menu()+ "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static Formularios buscarId(Formularios formulario) {
        if (Conexion.conectar()) {
            String sql = "select formularios.*,menus.nombre_menu from formularios,menus where id_formulario='" + formulario.getId_formulario() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    formulario.setNombre_formulario(rs.getString("nombre_formulario"));
                    formulario.setCodigo_formulario(rs.getString("codigo_formulario"));
                    Menus menu=new Menus();
                    menu.setId_menu(rs.getInt("id_menu"));
                    menu.setNombre_menu(rs.getString("nombre_menu"));
                    formulario.setMenu(menu);
               
                } else {
                    formulario.setId_formulario(0);
                    formulario.setNombre_formulario("");
                    formulario.setCodigo_formulario("");
                    Menus menu=new Menus();
                    menu.setId_menu(0);
                    menu.setNombre_menu("");
                    formulario.setMenu(menu);
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return formulario;
    }

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from formularios where upper(nombre_formulario) like '%" + nombre.toUpperCase() + "%'"
                        + " order by id_formulario offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_formulario") + "</td>"
                                + "<td>" + rs.getString("nombre_formulario") + "</td>"
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
    
    public static boolean eliminar(Formularios formulario){
        boolean valor=false;
        if(Conexion.conectar()){
            String sql="delete from formularios where id_formulario="+formulario.getId_formulario();
            try{
                Conexion.getSt().executeUpdate(sql);
                valor=true;
            }catch(SQLException ex){
                System.err.println("Error:"+ex);
            }
        }
        return valor;
    }
    public static boolean modificar(Formularios formulario){
        boolean valor=false;
        if(Conexion.conectar()){
            String sql="update formularios set nombre_formulario='"+formulario.getNombre_formulario()+"',"
                    +"codigo_formulario='"+formulario.getCodigo_formulario()+"'"
                    +" where id_formulario="+formulario.getId_formulario();
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