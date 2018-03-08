package controladores;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import modelos.Ajustes;
import modelos.DetallesAjustes;
import modelos.Productos;
import modelos.Stocks;
import utiles.Conexion;


public class DetallesAjustesControlador {

    public static DetallesAjustes buscarId(int id) throws SQLException {
        DetallesAjustes detalleajuste = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallesajustes dp "
                        + "left join ajustes p on p.id_ajuste=dp.id_ajuste "
                        + "left join productos a on a.id_producto=dp.id_producto "
                        + "where dp.id_detalleajuste=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        detalleajuste = new DetallesAjustes();
                        detalleajuste.setId_detalleajuste(rs.getInt("id_detalleajuste"));
                        detalleajuste.setCantidad_ajuste(rs.getInt("cantidad_ajuste"));

                        Productos producto = new Productos();
                        producto.setId_producto(rs.getInt("id_producto"));
                        producto.setNombre_producto(rs.getString("nombre_producto"));
                        producto.setCosto_compra(rs.getInt("costo_compra"));
                        producto.setIva_producto(rs.getInt("iva_producto"));
                        
                        detalleajuste.setProducto(producto);

                        Ajustes ajuste = new Ajustes();
                        ajuste.setId_ajuste(rs.getInt("id_ajuste"));
                        detalleajuste.setAjuste(ajuste);

                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return detalleajuste;
    }
    
    


    public static String buscarIdAjuste(int id) throws SQLException {

        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallesajustes dp "
                        + "left join ajustes p on p.id_ajuste=dp.id_ajuste "
                        + "left join productos a on a.id_producto=dp.id_producto "
//                        + "left join stock m on m.id_stock=dp.id_stock "
                        + "where dp.id_ajuste=" + id + " "
                        + "order by id_detalleajuste";
                System.out.println(" dsadsad" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    DecimalFormat df = new DecimalFormat("#,###");
                    String tabla = "";
//                    int total = 0;
//                    int totale = 0;
//                    int total5 = 0;
//                    int total10 = 0;
//                    int totalf = 0;
//                    //BigDecimal total10 = BigDecimal.ZERO;
//                    int iva = 0;
//
//                    int totalexentas = 0;
//                    int totaliva5 = 0;
//                    int totaliva10 = 0;
//
//                    int liqiva5 = 0;
//                    int liqiva10 = 0;
                    //   BigDecimal totaliva = BigDecimal.ZERO;
                    // int iva0=0;
                    while (rs.next()) {

//                        iva = rs.getInt("iva_producto");
                        int cantidad = rs.getInt("cantidad_ajuste");
//                        int costo = rs.getInt("costo_producto");
//                        if (iva == 0) {
//
//                            totalexentas = costo * cantidad;
//                            totaliva5 = 0;
//                            totaliva10 = 0;
//                            totale = totale + totalexentas;
//
//                        } else if (iva == 5) {
//
//                            totalexentas = 0;
//                            totaliva10 = 0;
//
//                            totaliva5 = costo * cantidad;
//                            total5 = total5 + totaliva5;
//
//                        } else {
//
//                            totalexentas = 0;
//                            totaliva5 = 0;
//                            totaliva10 = costo * cantidad;
//                            total10 = total10 + totaliva10;
//                        }
//
//                        liqiva5 = total5 / 21;
//                        liqiva10 = total10 / 11;
//
//                        total = liqiva5 + liqiva10;
//
//                        totalf = total5 + totale + total10;

                        //totaliva = totald*0.21;
                        // System.out.println("total"+total);
                        tabla += "<tr>"
                                //   + "<td>" + rs.getInt("id_detalleajuste") + "</td>"
                                //  + "<td>" + rs.getInt("id_producto") + "</td>"

                                + "<td class='centrado'>" + df.format(cantidad) + "</td>"
                               + "<td class='centrado'>"  + rs.getString("nombre_producto") + " " + "</td>"
                               + "<td class='centrado'>" + rs.getString("detalle_ajuste") + "</td>"
//                                + "<td>" + df.format(rs.getInt("costo_producto")) + "</td>"
//                                + "<td>" + df.format(totalexentas) + "</td>"
//                                + "<td>" + df.format(totaliva5) + "</td>"
//                                + "<td>" + df.format(totaliva10) + "</td>"
                                + "<td class='centrado'>"
                                + "<button onclick='editarLinea(" + rs.getString("id_detalleajuste") + ")'"
                                + " type='button' class='btn btn-primary btn-sm'><span class='glyphicon glyphicon-pencil'>"
                                + "</span></button></td>"
                                + "</tr>";

                    }

                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=7>No existen registros ...</td></tr>";
                    } else {

//                       tabla += "<tr><td colspan=3><b>Sub-Total:</b></td><td class='left'><b>" + df.format(totale) + "</b></td><td><b>" + df.format(total5) + "</b></td><th>" + df.format(total10) + "</td></tr>";
//                        tabla += "<tr><td colspan=6><b>Lquidacion de IVA:</b>&nbsp;&nbsp;&nbsp; <b>(5%):</b> " + "" + df.format(liqiva5) + "  &nbsp;&nbsp;&nbsp;   <b>(10%):</b>&nbsp;" + df.format(liqiva10) + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b> TOTAL I.V.A:</b> &nbsp; " + df.format(total) + " </td> </tr>";
//
//                        tabla += "<tr><td colspan=5 ><b><P ALIGN=right>Total:</b></td><td class='left'><b>" + df.format(totalf) + "</b></td></tr>";  //+ df.format(totald) +"</td></tr>";
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

//    public static String buscarNombre(String nombre, int pagina) throws SQLException {
//        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
//        String valor = "";
//        if (Conexion.conectar()) {
//            try {
//                String sql = "select * from detallesajustes dp "
//                        + "left join ajustes p on p.id_ajuste=dp.id_ajuste "
//                        + "left join productos a on a.id_producto=dp.id_producto "
//                        + "where upper(a.nombre_producto) like '%"
//                        + nombre.toUpperCase()
//                        + "%' "
//                        + "order by id_detalleajuste "
//                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
//                System.out.println("--> " + sql);
//                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
//                    ResultSet rs = ps.executeQuery();
//                    String tabla = "";
//                    while (rs.next()) {
//                        tabla += "<tr>"
//                                + "<td>" + rs.getInt("id_detalleajuste") + "</td>"
//                                + "<td>" + rs.getInt("id_ajuste") + "</td>"
//                                + "<td>" + rs.getString("id_producto") + "</td>"
//                                + "<td>" + rs.getString("nombre_producto") + "</td>"
//                                + "<td>" + rs.getInt("costo_producto") + "</td>"
//                                + "<td>" + rs.getInt("iva_producto") + "</td>"
//                                + "<td>" + rs.getInt("cantidad_ajuste") + "</td>"
//                                + "</tr>";
//                    }
//                    if (tabla.equals("")) {
//                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
//                    }
//                    ps.close();
//                    valor = tabla;
//                }
//            } catch (SQLException ex) {
//                System.out.println("--> " + ex.getLocalizedMessage());
//            }
//        }
//        Conexion.cerrar();
//        return valor;
//    }

    public static boolean agregar(DetallesAjustes detalleajuste) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into detallesajustes "
                    + "(id_ajuste,id_producto,cantidad_ajuste,detalle_ajuste) "
                    + "values (?,?,?,?)";

            System.out.println(sql);
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalleajuste.getAjuste().getId_ajuste());
                ps.setInt(2, detalleajuste.getProducto().getId_producto());

                ps.setInt(3, detalleajuste.getCantidad_ajuste());
                ps.setString(4, detalleajuste.getDetalle_ajuste());
              
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

    public static boolean modificar(DetallesAjustes detalleajuste) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update detallesajustes set "
                    + "id_ajuste=?,"
                    + "id_producto=?,"
                    + "cantidad_ajuste=? "
                    + "detalle_ajuste=? "
                   
                    + "where id_detalleajuste=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalleajuste.getAjuste().getId_ajuste());
                ps.setInt(2, detalleajuste.getProducto().getId_producto());
                ps.setInt(3, detalleajuste.getCantidad_ajuste());
               ps.setString(4, detalleajuste.getDetalle_ajuste());
                ps.setInt(5, detalleajuste.getId_detalleajuste());
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

    public static boolean eliminar(DetallesAjustes detalleajuste) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallesajustes where id_detalleajuste=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalleajuste.getId_detalleajuste());
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

    public static boolean eliminarc(DetallesAjustes detalleajuste) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallesajustes dp "
                        + "left join ajustes p on p.id_ajuste=dp.id_ajuste "
                        + "left join productos a on a.id_producto=dp.id_producto "
                        + " where p.id_ajuste= " + detalleajuste.getAjuste().getId_ajuste();
                System.out.println("detalle " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                       
                        String sqli = "update stocks set cantidad_stock= cantidad_stock - "+ rs.getInt("cantidad_productoajuste") + " where id_producto="+ rs.getInt("id_producto")+"";
                        
                        System.out.println(" descontar stcok" + sqli);
                        try (PreparedStatement psi = Conexion.getConn().prepareStatement(sqli)) {

                            psi.executeUpdate();
                            psi.close();
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

                    ps.close();
                    valor = true;
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean eliminarProductoAjuste(Ajustes ajuste) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallesajustes where id_ajuste=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, ajuste.getId_ajuste());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                System.out.println("--> " + ajuste.getId_ajuste());
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
