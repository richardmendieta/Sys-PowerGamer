
package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelos.Clientes;
import modelos.Cobros;
import modelos.Establecimientos;
import modelos.Puestos;
import modelos.Timbrados;
import modelos.Ventas;
import utiles.Conexion;
import utiles.Utiles;

public class VentasControlador {

    public static Ventas buscarId(int id) throws SQLException {
        Ventas ventas = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from ventas v "
                        + "left join clientes c on v.id_cliente=c.id_cliente "
                        + "left join timbrados t on v.id_timbrado=t.id_timbrado "
                        + " where id_venta=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        ventas = new Ventas();
                        ventas.setId_venta(rs.getInt("id_venta"));
                        ventas.setFecha_venta(rs.getDate("fecha_venta"));
                        ventas.setEstado_venta(rs.getString("estado_venta"));
                        ventas.setNumero_factura(rs.getInt("numero_factura"));

                        Clientes cliente = new Clientes();
                        cliente.setId_cliente(rs.getInt("id_cliente"));
                        cliente.setNombre_cliente(rs.getString("nombre_cliente"));
                        cliente.setRuc_cliente(rs.getString("ruc_cliente"));
                        ventas.setCliente(cliente);

                        Timbrados timbrado = new Timbrados();
                        timbrado.setId_timbrado(rs.getInt("id_timbrado"));
                        timbrado.setNumero_timbrado(rs.getString("numero_timbrado"));

                        ventas.setTimbrado(timbrado);

                    } else {

                        try {

                            String sqli = "SELECT COUNT (*) AS Ultimo, COUNT(numero_factura) FROM ventas";
                            try (PreparedStatement psi = Conexion.getConn().prepareStatement(sqli)) {

                                int num = 0;
                                ResultSet rsi = psi.executeQuery();
                                if (rsi.next()) {
                                    ventas = new Ventas();
                                    num = rsi.getInt("Ultimo");
                                    ventas.setId_venta(0);
                                    ventas.setNumero_factura(num + 1);

                                    ventas.setEstado_venta("ACTIVO");
                                    java.sql.Date fecha_venta = new java.sql.Date(new java.util.Date().getTime());
                                    ventas.setFecha_venta(fecha_venta);

                                    Clientes cliente = new Clientes();
                                    cliente.setId_cliente(0);
                                    cliente.setNombre_cliente("");
                                    cliente.setRuc_cliente("");
                                    ventas.setCliente(cliente);

                                    Timbrados timbrado = new Timbrados();
                                    timbrado.setId_timbrado(1);
                                    timbrado.setNumero_timbrado("");
                                    ventas.setTimbrado(timbrado);

                                    Puestos puesto = new Puestos();
                                    puesto.setId_puesto(1);
                                    timbrado.setPuesto(puesto);

                                    Establecimientos establecimiento = new Establecimientos();
                                    establecimiento.setId_establecimiento(1);
                                    timbrado.setEstablecimiento(establecimiento);

                                } else {
                                    num = 1;
                                }

                                psi.close();
                            }
                        } catch (SQLException ex) {
                            System.out.println("--> " + ex.getLocalizedMessage());
                        }
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return ventas;
    }

    public static String buscarNombre(String nombre, int pagina) throws SQLException {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from ventas v "
                        + "left join clientes c on v.id_cliente=c.id_cliente left join timbrados t on v.id_timbrado=t.id_timbrado  "
                        + "where upper(nombre_cliente) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_venta "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_venta") + "</td>"
                                + "<td>" + rs.getString("fecha_venta") + "</td>"
                                + "<td>" + rs.getString("estado_venta") + "</td>"
                                + "<td>" + rs.getString("numero_factura") + "</td>"
                                + "<td>" + rs.getString("id_cliente") + "</td>"
                                + "<td>" + rs.getString("nombre_cliente") + "</td>"
                                + "<td>" + rs.getString("ruc_cliente") + "</td>"
                                + "<td>" + rs.getString("id_timbrado") + "</td>"
                                + "<td>" + rs.getString("numero_timbrado") + "</td>"
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

    public static boolean agregar(Ventas venta) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            int v1 = venta.getCliente().getId_cliente();
            int tm = venta.getTimbrado().getId_timbrado();
            String sql = "insert into ventas(fecha_venta"
                    + ",estado_venta,id_cliente,numero_factura, id_timbrado)"
                    + "values('"
                    + venta.getFecha_venta() + "','"
                    + venta.getEstado_venta() + "','"
                    + v1 + "','"
                    + venta.getNumero_factura() + "','"
                    + tm + "')";

            System.out.println("--> " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_venta = keyset.getInt(1);
                    venta.setId_venta(id_venta);

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

    public static boolean modificar(Ventas venta) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update ventas set id_cliente= " + venta.getCliente().getId_cliente() + " ,"
                    + " fecha_venta= ' " + venta.getFecha_venta() + " ' , "
                    + " estado_venta= ' " + venta.getEstado_venta() + " ' , "
                    + " where id_venta=" + venta.getId_venta();
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {

                ps.setInt(1, venta.getCliente().getId_cliente());
                ps.setInt(2, venta.getId_venta());
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

    public static boolean eliminar(Ventas venta) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from ventas where id_venta=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, venta.getId_venta());
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

    public static boolean modificarestado(Ventas venta) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update ventas set estado_venta='ANULADO'  "
                    + " where id_venta=" + venta.getId_venta();
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

    public static Ventas buscarNumFactura(int id) throws SQLException {
        Ventas ventas = null;

        if (Conexion.conectar()) {
            try {
                String sqli = "SELECT COUNT(*) AS Ultimo, COUNT(numero_factura) FROM ventas ";

                System.out.println("sss" + sqli);
                try (PreparedStatement psi = Conexion.getConn().prepareStatement(sqli)) {

                    ResultSet rsi = psi.executeQuery();
                    if (rsi.next()) {
                        ventas = new Ventas();

                        ventas.setNumero_factura(rsi.getInt("Ultimo"));

                    }
                    psi.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return ventas;
    }

    public static Ventas buscarIdCobro(int id, Cobros cobro) throws SQLException {
        Ventas ventas = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select v.id_venta,SUM(total) as total from ventas v "
                        + " left join detallesventas dv on dv.id_venta=v.id_venta "
                        + " where v.id_venta=? "
                        + " group by v.id_venta ";

                System.out.println(sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {

                        cobro.setId_cobro(0);
                        cobro.setTotal_cobro(rs.getInt("total"));

                        ventas = new Ventas();
                        ventas.setId_venta(rs.getInt("id_venta"));
                        cobro.setVenta(ventas);

                    } else {

                        cobro.setId_cobro(0);
                        cobro.setTotal_cobro(0);

                        ventas = new Ventas();
                        ventas.setId_venta(0);
                        cobro.setVenta(ventas);

                    }
                    ps.close();

                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return ventas;
    }
public static boolean agregarCobro(Cobros cobro) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into cobros "
                    + "(id_venta,id_pago,total_cobro) "
                    + "values (?,?,?)";

            System.out.println(""+ sql);
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, cobro.getVenta().getId_venta());
                ps.setInt(2, cobro.getPago().getId_pago());
                ps.setInt(3, cobro.getTotal_cobro());

           
                
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
public static boolean modificarestadoVenta(Ventas venta) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update ventas set estado_venta= 'FACTURADO'"
                    + " where id_venta=" + venta.getId_venta();
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
