<%@page import="modelos.PagosCompras"%>
<%@page import="controladores.ComprasControlador"%>
<%@page import="modelos.Compras"%>
<%@page import="modelos.Cobros"%>
<%@page import="modelos.Productos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_compra = Integer.parseInt(request.getParameter("id_compra"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    
    PagosCompras pagocompra =new PagosCompras();
    
    Compras compra=new Compras();
   compra.setId_compra(id_compra);

     compra = ComprasControlador.buscarIdPago(id_compra,pagocompra);
    if (compra != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } 
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_pagocompra", String.valueOf(pagocompra.getId_pagocompra()));
    obj.put("id_compra", String.valueOf(pagocompra.getCompra().getId_compra()));
    obj.put("total_pago",pagocompra.getTotal_pago());
   
   out.print(obj);
    out.flush();
%>