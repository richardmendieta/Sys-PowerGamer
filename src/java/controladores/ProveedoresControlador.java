/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelos.Ciudades;
import modelos.Proveedores;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author RNMG
 */
public class ProveedoresControlador {
    public static boolean agregar(Proveedores proveedor){
        boolean valor =false;
        if(Conexion.conectar()){
            String sql = "insert into proveedores ( nombre_proveedor, ruc_proveedor, telefono1_proveedor, telefono2_proveedor, direccion_proveedor, correo_proveedor, id_ciudad)"
                    + ""+ "values('"+ proveedor.getNombre_proveedor()
                    +"','"+ proveedor.getRuc_proveedor() 
                    +"','"+ proveedor.getTelefono1_proveedor()
                    +"','"+ proveedor.getTelefono2_proveedor()
                    +"','"+ proveedor.getDireccion_proveedor() 
                    +"','"+ proveedor.getCorreo_proveedor()
                    +"','"+proveedor.getCiudad().getId_ciudad()+"')";
           System.out.println(sql);
            try {
                Conexion.getSt().executeUpdate(sql);
                valor= true;
                
            }catch (SQLException ex){
                System.err.println("Error: "+ ex);
            }
        }
        return valor;
    }
    public static Proveedores buscarId(Proveedores proveedor){
        if (Conexion.conectar()){
            String sql= "select * from proveedores p ,ciudades c  where p.id_ciudad=c.id_ciudad and  id_proveedor='"+proveedor.getId_proveedor()+"'";
             System.out.println(sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if(rs.next ()){
                    proveedor.setNombre_proveedor(rs.getString("nombre_proveedor"));
                    proveedor.setRuc_proveedor(rs.getString("ruc_proveedor"));
                    proveedor.setTelefono1_proveedor(rs.getInt("telefono1_proveedor"));
                    proveedor.setTelefono2_proveedor(rs.getInt("telefono2_proveedor"));
                    proveedor.setDireccion_proveedor(rs.getString("direccion_proveedor"));
                    proveedor.setCorreo_proveedor(rs.getString("correo_proveedor"));
                    Ciudades ciudad = new Ciudades();
                    ciudad.setId_ciudad(rs.getInt("id_ciudad"));
                    ciudad.setNombre_ciudad(rs.getString("nombre_ciudad"));
                }else{
                    proveedor.setId_proveedor(0);
                    proveedor.setNombre_proveedor("");
                    proveedor.setRuc_proveedor("");
                    proveedor.setTelefono1_proveedor(0);
                    proveedor.setTelefono2_proveedor(0);
                    proveedor.setDireccion_proveedor("");
                    proveedor.setCorreo_proveedor("");
                    Ciudades ciudad = new Ciudades();
                    ciudad.setId_ciudad(0);
                    ciudad.setNombre_ciudad("");
                    proveedor.setCiudad(ciudad);
                    
                }
            }catch (SQLException ex){
                System.err.println("Error:"+ ex);
            }
        }
        return proveedor;
    }
     public static boolean eliminar(Proveedores proveedor){
        boolean valor=false;
        if(Conexion.conectar()){
            String sql="delete from proveedores where id_proveedor="+proveedor.getId_proveedor();
            try{
                Conexion.getSt().executeUpdate(sql);
                valor=true;
            }catch(SQLException ex){
                System.err.println("Error:"+ex);
            }
        }
        return valor;
    }
      public static boolean modificar (Proveedores proveedor){
        boolean valor=false;
        if(Conexion.conectar()){
             String sql = "update proveedores set nombre_proveedor='" + proveedor.getNombre_proveedor() 
                    + "', ruc_proveedor='" + proveedor.getRuc_proveedor() 
                    + "', telefono1_proveedor='" + proveedor.getTelefono1_proveedor() 
                    + "', telefono2_proveedor='" + proveedor.getTelefono2_proveedor()
                     + "', direccion_proveedor='" + proveedor.getDireccion_proveedor()
                    + "', correo_proveedor='" + proveedor.getCorreo_proveedor() 
                    + "' where id_proveedor=" + proveedor.getId_proveedor();
            try{
                Conexion.getSt().executeUpdate(sql);
                valor=true;
            }catch(SQLException ex){
                System.err.println("error: "+ex);
            }
        }
        return valor;
    }
      public static String buscarNombre (String nombre, int pagina){
        int offset = (pagina - 1)* Utiles.REGISTROS_PAGINA;
        String valor= "";
        if(Conexion.conectar ()){
            try{
                String sql ="select * from proveedores where upper (nombre_proveedor) like'%"+nombre.toUpperCase()+"%'"+"order by id_proveedor offset "+ offset +" limit "+ Utiles.REGISTROS_PAGINA;
                System.out.println("--->" +sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla="";
                    while (rs.next()){
                        tabla += "<tr>"
                                +"<td>" +rs.getString("id_proveedor")+"</td>"
                                +"<td>" +rs.getString("nombre_proveedor")+ "</td>"
                                +"<td>" +rs.getString("Ruc_proveedor")+ "</td>"
                                +"<td>" +rs.getInt("telefono1_proveedor")+ "</td>"
                                +"<td>" +rs.getInt("telefono2_proveedor")+ "</td>"
                                +"<td>" +rs.getString("direccion_proveedor")+ "</td>"
                                +"<td>" +rs.getString("correo_proveedor")+ "</td>"
                                +"</tr>";
                    }
                    if(tabla.equals("")){
                        tabla="<tr><td colspan=2>No existen registros...</td></tr>";
                    }
                    ps.close();
                    valor=tabla;
                }catch (SQLException ex){
                    System.err.println("Error: "+ ex);
                }
                Conexion.cerrar();
            }catch (Exception ex){
                System.err.println("Error: "+ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }
}