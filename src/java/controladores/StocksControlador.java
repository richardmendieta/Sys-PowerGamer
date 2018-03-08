package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelos.Stocks;
import utiles.Conexion;

public class StocksControlador {

    public static boolean agregar(Stocks stock) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into stocks(id_producto,cantidad_min,cantidad_max,cantidad_existente) "
                    + "values('" + stock.getProducto().getId_producto() + "','"
                    + stock.getCantidad_min() + "','"
                    + stock.getCantidad_max() + "','"
                    + stock.getCantidad_existente() + "')";
            System.out.println("--> " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_stock = keyset.getInt(1);
                    stock.setId_stock(id_stock);
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

    public static boolean agregarProdStock(Stocks stock) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update stocks set cantidad_existente=cantidad_existente + '" + stock.getCantidad_existente()
                    + "' where id_producto=" + stock.getProducto().getId_producto();
            System.out.println(sql);
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static boolean RestarProdStock(Stocks stock) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update stocks set cantidad_existente=cantidad_existente - '" + stock.getCantidad_existente()
                    + "' where id_producto=" + stock.getProducto().getId_producto();
            System.out.println(sql);
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }

 public static Stocks buscarId(Stocks stock) {
        if (Conexion.conectar()) {
            String sql = "select * from stocks where id_producto='" + stock.getProducto().getId_producto() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    stock.setCantidad_max(rs.getInt("cantidad_max"));
                    stock.setCantidad_min(rs.getInt("cantidad_min"));
                } else {
                    stock.setCantidad_max(0);
                    stock.setCantidad_min(0);
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return stock;
    }

    public static boolean restarDetalle(Stocks stock) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update stocks set cantidad_existente=? where id_producto=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {

                ps.setInt(1, stock.getCantidad_existente());
                ps.setInt(2, stock.getProducto().getId_producto());
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
public static boolean agregarProdStockVenta(Stocks stock) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update stocks set cantidad_existente=cantidad_existente - '" + stock.getCantidad_existente()
                    + "' where id_producto=" + stock.getProducto().getId_producto();
            System.out.println(sql);
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }
 public static boolean SumarProdStock(Stocks stock) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update stocks set cantidad_existente=cantidad_existente + '" + stock.getCantidad_existente()
                    + "' where id_producto=" + stock.getProducto().getId_producto();
            System.out.println(sql);
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;
    }
 
 public static Stocks buscarexistencia(Stocks stock) {
        if (Conexion.conectar()) {
            String sql = "select * from stocks where id_producto='" + stock.getProducto().getId_producto() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    stock.setCantidad_existente(rs.getInt("cantidad_existente"));
                    
                } else {
                    stock.setCantidad_existente(0);
                   
                }
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return stock;
    }
}