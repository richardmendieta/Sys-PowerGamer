

<%@page import="controladores.PagosControlador"%>
<%@page import="modelos.Pagos"%>
<%@page import="org.json.simple.JSONObject" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int id_pago=Integer.parseInt(request.getParameter("id_pago"));
    String tipo_pago=request.getParameter("tipo_pago");

    String tipo="error";
    
    Pagos pago=new Pagos();
    pago.setId_pago(id_pago);
    pago.setTipo_pago(tipo_pago);
 
    
    String mensaje="Datos no Modificados";
    if(PagosControlador.modificar(pago)){
        tipo="success";
        mensaje="Datos modificados correctamente";
    };
    JSONObject obj=new JSONObject();
    obj.put("tipo",tipo);
    obj.put("mensaje",String.valueOf(mensaje));
    out.print(obj);
    out.flush();
    %>
