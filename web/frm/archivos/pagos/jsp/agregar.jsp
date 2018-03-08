


<%@page import="modelos.Pagos"%>
<%@page import="controladores.PagosControlador"%>
<%@page import="org.json.simple.JSONObject" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    //int id_pago=Integer.parseInt(request.getParameter("id_pago"));
    String tipo_pago=request.getParameter("tipo_pago");
 
    Pagos pago=new Pagos();
    //pago.setId_pago(id_pago);
    pago.setTipo_pago(tipo_pago);
    String mensaje="Datos no Agregados";
    if(PagosControlador.agregar(pago)){
        mensaje="Datos agregados correctamente";
    };
    JSONObject obj=new JSONObject();
    obj.put("mensaje",String.valueOf(mensaje));
    out.print(obj);
    out.flush();
    %>
    

