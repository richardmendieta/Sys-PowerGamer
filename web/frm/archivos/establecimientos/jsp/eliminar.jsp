
<%@page import="org.json.simple.JSONObject"%>
<%@page import="controladores.EstablecimientosControlador"%>
<%@page import="modelos.Establecimientos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int id_establecimiento = Integer.parseInt(request.getParameter("id_establecimiento"));

    String tipo = "error";
    String mensaje = "Datos no eliminados";

    Establecimientos establecimiento = new Establecimientos();
    establecimiento.setId_establecimiento(id_establecimiento);
    
    if (EstablecimientosControlador.eliminar(establecimiento)) {
        tipo = "success";
        mensaje = "Datos eliminados correctamente";
    };

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", String.valueOf(mensaje));
    out.print(obj);
    out.flush();
%>
