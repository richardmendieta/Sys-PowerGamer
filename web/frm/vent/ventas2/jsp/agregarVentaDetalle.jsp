
<%@page import="controladores.StocksControlador"%>
<%@page import="modelos.Stocks"%>
<%@page import="controladores.ProductosControlador"%>
<%@page import="controladores.DetallesVentasControlador"%>
<%@page import="modelos.Productos"%>
<%@page import="modelos.Ventas"%>
<%@page import="modelos.DetallesVentas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%

    int id_detalleventa = Integer.parseInt(request.getParameter("id_detalleventa"));
    int cantidad_productoventa = Integer.parseInt(request.getParameter("cantidad_productoventa"));
    int id_venta = Integer.parseInt(request.getParameter("id_venta"));
    int id_producto = Integer.parseInt(request.getParameter("id_producto"));
    int costo_venta = Integer.parseInt(request.getParameter("costo_venta"));
    int iva_producto = Integer.parseInt(request.getParameter("iva_producto"));
    int totald = cantidad_productoventa * costo_venta;

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    DetallesVentas detalleventa = new DetallesVentas();
    detalleventa.setId_detalleventa(id_detalleventa);
    detalleventa.setCantidad_productoventa(cantidad_productoventa);
    detalleventa.setTotal(totald);

    Ventas venta = new Ventas();
    venta.setId_venta(id_venta);

    Productos producto = new Productos();
    producto.setId_producto(id_producto);
    producto.setCosto_venta(costo_venta);
    producto.setIva_producto(iva_producto);
    detalleventa.setVenta(venta);
    detalleventa.setProducto(producto);

    if (DetallesVentasControlador.agregar(detalleventa)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    
    producto = new Productos();

    producto.setId_producto(id_producto);
    producto.setCosto_venta(costo_venta);

    //ProductosControlador.modificarc(producto);

    Stocks stock = new Stocks();

    stock.setCantidad_existente(cantidad_productoventa);
  
    stock.setProducto(producto);

    StocksControlador.agregarProdStockVenta(stock);
    producto = new Productos();
    producto.setId_producto(id_producto);
    producto.setCosto_venta(costo_venta);
    //ProductosControlador.modificarc(producto);

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>