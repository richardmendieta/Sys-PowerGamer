/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelos.Compras;
import modelos.PagosCompras;
import modelos.Proveedores;
import utiles.Conexion;
import utiles.Utiles;


/**
 *
 * @author Administrator
 */
public class ComprasControlador {

    public static Compras buscarId(int id) throws SQLException {
        Compras compras = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from compras c "
                        + "left join proveedores p on c.id_proveedor=p.id_proveedor "
                        + " where id_compra=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        compras = new Compras();
                        compras.setId_compra(rs.getInt("id_compra"));
                        compras.setNumero_factura_compra(rs.getString("numero_factura_compra"));
                        compras.setFecha_compra(rs.getDate("fecha_compra"));
                        compras.setEstado_compra(rs.getString("estado_compra"));

                        Proveedores proveedor = new Proveedores();
                        proveedor.setId_proveedor(rs.getInt("id_proveedor"));
                        proveedor.setNombre_proveedor(rs.getString("nombre_proveedor"));
                        proveedor.setRuc_proveedor(rs.getString("ruc_proveedor"));
                        compras.setProveedor(proveedor);
                       
                    } else {

                        compras = new Compras();

                        compras.setId_compra(0);
                        compras.setNumero_factura_compra("");
                        compras.setEstado_compra("ACTIVO");
                        java.sql.Date fecha_compra = new java.sql.Date(new java.util.Date().getTime());
                        compras.setFecha_compra(fecha_compra);
                        Proveedores proveedor = new Proveedores();
                        proveedor.setId_proveedor(0);
                        proveedor.setNombre_proveedor("");
                        proveedor.setRuc_proveedor("");
                        compras.setProveedor(proveedor);

                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return compras;
    }

    public static String buscarNombre(String nombre, int pagina) throws SQLException {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from compras p "
                        + "left join proveedores c on c.id_proveedor=p.id_proveedor "
                        + "and upper(nombre_proveedor) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_compra "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_compra") + "</td>"
                                + "<td>" + rs.getString("numero_factura_compra") + "</td>"
                                + "<td>" + rs.getString("fecha_compra") + "</td>"
                                + "<td>" + rs.getString("estado_compra") + "</td>"
                                + "<td>" + rs.getString("id_proveedor") + "</td>"
                                + "<td>" + rs.getString("nombre_proveedor") + "</td>"
                                + "<td>" + rs.getString("ruc_proveedor") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=5>No existen registros ...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean agregar(Compras compra) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            int v1 = compra.getProveedor().getId_proveedor();

            String sql = "insert into compras(numero_factura_compra"
                    + ",fecha_compra,id_proveedor,estado_compra)"
                    + "values('"
                    + compra.getNumero_factura_compra() + "','"
                    + compra.getFecha_compra() + "','"
                    + v1 + "','"
                    + compra.getEstado_compra() + "')";

            System.out.println("--> " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_compra = keyset.getInt(1);
                    compra.setId_compra(id_compra);

                    Conexion.getConn().commit();
                }
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
            Conexion.cerrar();
        }

        return valor;
    }

    public static boolean modificar(Compras compra) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update compras set id_proveedor= " + compra.getProveedor().getId_proveedor() + " ,"
                    + " numero_factura_compra= ' " + compra.getNumero_factura_compra() + " ' , "
                    + " fecha_compra= ' " + compra.getFecha_compra() + " ' , "
                    + " fecha_compra= ' " + compra.getFecha_compra() + " ' , "
                    + " estado_compra= ' " + compra.getEstado_compra() + " ' , "
                    + " where id_compra=" + compra.getId_compra();
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {

                ps.setInt(1, compra.getProveedor().getId_proveedor());
                ps.setInt(2, compra.getId_compra());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                System.out.println("--> Grabado");
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean eliminar(Compras compra) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from compras where id_compra=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, compra.getId_compra());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }
     public static boolean modificarestado(Compras compra) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update compras set estado_compra='ANULADO'  "
                  
                    + " where id_compra=" + compra.getId_compra();
            try {
                
                Conexion.getSt().executeUpdate(sql);
                
                Conexion.getConn().commit();
                System.out.println("--> Grabado");
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }
     
      public static Compras buscarIdPago(int id, PagosCompras pagocompra) throws SQLException {
        Compras compra = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select c.id_compra,SUM(total) as total from compras c "
                        + " left join detallescompras dc on dc.id_compra=c.id_compra "
                        + " where c.id_compra=? "
                        + " group by c.id_compra ";

                System.out.println(sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {

                        pagocompra.setId_pagocompra(0);
                        pagocompra.setTotal_pago(rs.getInt("total"));

                        compra = new Compras();
                        compra.setId_compra(rs.getInt("id_compra"));
                        pagocompra.setCompra(compra);

                    } else {

                        pagocompra.setId_pagocompra(0);
                        pagocompra.setTotal_pago(0);

                        compra = new Compras();
                        compra.setId_compra(0);
                        pagocompra.setCompra(compra);

                    }
                    ps.close();

                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return compra;
    }
public static boolean agregarPago(PagosCompras pagocompra) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into pagoscompras "
                    + "(id_compra,id_pago,total_pago) "
                    + "values (?,?,?)";

            System.out.println(""+ sql);
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, pagocompra.getCompra().getId_compra());
                ps.setInt(2, pagocompra.getPago().getId_pago());
                ps.setInt(3, pagocompra.getTotal_pago());

           
                
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }
public static boolean modificarestadoCompra(Compras compra) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update compras set estado_compra= 'PAGADO'"
                    + " where id_compra=" + compra.getId_compra();
            try {

                Conexion.getSt().executeUpdate(sql);

                Conexion.getConn().commit();
                System.out.println("--> Grabado");
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }
    
}
