
<%@page import="modelos.Timbrados"%>
<%@page import="controladores.DetallesVentasControlador"%>
<%@page import="modelos.Clientes"%>
<%@page import="controladores.VentasControlador"%>
<%@page import="modelos.Ventas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_venta = 0;
    if (request.getParameter("id_venta") != "") {
        id_venta = Integer.parseInt(request.getParameter("id_venta"));

    }

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    
    Ventas ventas = VentasControlador.buscarId(id_venta);
    if (id_venta != 0) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } 
    String contenido_detalle = DetallesVentasControlador.buscarIdVenta(id_venta);

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_venta", String.valueOf(ventas.getId_venta()));
    obj.put("fecha_venta", String.valueOf(ventas.getFecha_venta()));
    obj.put("estado_venta", ventas.getEstado_venta());
    obj.put("numero_factura", ventas.getNumero_factura());
    obj.put("id_cliente", String.valueOf(ventas.getCliente().getId_cliente()));
    obj.put("nombre_cliente", ventas.getCliente().getNombre_cliente());
    obj.put("ruc_cliente", ventas.getCliente().getRuc_cliente());
    obj.put("id_timbrado", String.valueOf(ventas.getTimbrado().getId_timbrado()));
    obj.put("numero_timbrado", ventas.getTimbrado().getNumero_timbrado());
    obj.put("contenido_detalle", contenido_detalle);

    out.print(obj);
    out.flush();
%>