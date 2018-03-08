
<%@page import="controladores.PagosControlador"%>
<%@page import="modelos.Pagos"%>
<%@page import="org.json.simple.JSONObject" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int id_pago=Integer.parseInt(request.getParameter("id_pago"));
    
    String tipo="error";
        String mensaje="Datos no eliminados";
        
    Pagos pago=new Pagos();
    pago.setId_pago(id_pago);
   
  
    if(PagosControlador.eliminar(pago)){
        tipo="success";
        mensaje="Datos eliminados correctamente";
    };
    JSONObject obj=new JSONObject();
    obj.put("tipo",tipo);
    obj.put("mensaje",String.valueOf(mensaje));
    out.print(obj);
    out.flush();
    %>
