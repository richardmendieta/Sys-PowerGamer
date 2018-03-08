
<%@page import="controladores.StocksControlador"%>
<%@page import="modelos.Stocks"%>
<%@page import="modelos.Productos"%>
<%@page import="modelos.DetallesCompras"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_producto = Integer.parseInt(request.getParameter("id_producto"));
    int id_detallecompra = Integer.parseInt(request.getParameter("id_detallecompra"));
    int cantidad_productocompra = Integer.parseInt(request.getParameter("cantidad_productocompra"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    DetallesCompras detallecompra = new DetallesCompras();
    detallecompra.setId_detallecompra(id_detallecompra);

    Productos producto = new Productos();
    producto.setId_producto(id_producto);

    Stocks stock = new Stocks();

    stock.setCantidad_existente(cantidad_productocompra);

    stock.setProducto(producto);

    if (StocksControlador.restarDetalle(stock)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>