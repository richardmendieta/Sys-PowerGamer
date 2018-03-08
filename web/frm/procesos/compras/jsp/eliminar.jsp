<%@page import="controladores.DetallesComprasControlador"%>
<%@page import="modelos.Compras"%>
<%@page import="modelos.DetallesCompras"%>
<%@page import="controladores.ComprasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_compra = Integer.parseInt(request.getParameter("id_compra"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    Compras compra = new Compras();
    compra.setId_compra(id_compra);
    DetallesCompras detallecompra = new DetallesCompras();
    detallecompra.setCompra(compra);
    ComprasControlador.modificarestado(compra);
    
    if (DetallesComprasControlador.eliminarc(detallecompra)) {
        tipo = "success";
        mensaje = "Factura Anulado.";
    }
    compra = new Compras();
    compra.setId_compra(id_compra);
    ComprasControlador.modificarestado(compra);

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>