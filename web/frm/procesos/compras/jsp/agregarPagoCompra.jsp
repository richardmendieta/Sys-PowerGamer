<%@page import="controladores.ComprasControlador"%>
<%@page import="modelos.PagosCompras"%>
<%@page import="modelos.Compras"%>
<%@page import="modelos.Cobros"%>
<%@page import="modelos.Pagos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    //int id_pagocompra = Integer.parseInt(request.getParameter("id_pagocompra"));
    int id_compra = Integer.parseInt(request.getParameter("id_compra"));
    int id_pago = Integer.parseInt(request.getParameter("id_pago"));
    int total_pago = Integer.parseInt(request.getParameter("total_pago"));

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    Compras compra = new Compras();
    compra.setId_compra(id_compra);
    Pagos pago = new Pagos();
    pago.setId_pago(id_pago);

    PagosCompras pagocompra = new PagosCompras();
   // pagocompra.setId_pagocompra(id_pagocompra);
    pagocompra.setTotal_pago(total_pago);
    pagocompra.setCompra(compra);
    pagocompra.setPago(pago);

 ComprasControlador.modificarestadoCompra(compra);
    if (ComprasControlador.agregarPago(pagocompra)) {
        tipo = "success";
        mensaje = "Datos agregados.";
   
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);

    out.print(obj);
    out.flush();

%>