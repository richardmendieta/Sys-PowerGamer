
<%@page import="controladores.DetallesVentasControlador"%>
<%@page import="modelos.Ventas"%>
<%@page import="controladores.StocksControlador"%>
<%@page import="modelos.Stocks"%>
<%@page import="modelos.Productos"%>
<%@page import="modelos.DetallesVentas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_venta = Integer.parseInt(request.getParameter("id_venta"));
    int id_producto = Integer.parseInt(request.getParameter("id_producto"));
    int id_detalleventa = Integer.parseInt(request.getParameter("id_detalleventa"));
    int cantidad_productoventa = Integer.parseInt(request.getParameter("cantidad_productoventa"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Ventas venta = new Ventas();
    venta.setId_venta(id_venta);
    
    
    DetallesVentas detalleventa = new DetallesVentas();
    detalleventa.setId_detalleventa(id_detalleventa);
    detalleventa.setVenta(venta);
    
    if (DetallesVentasControlador.eliminar(detalleventa)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    Productos producto = new Productos();
    producto.setId_producto(id_producto);

    Stocks stock = new Stocks();

    stock.setCantidad_existente(cantidad_productoventa);

    stock.setProducto(producto);

    StocksControlador.agregarProdStock(stock);

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>