
<%@page import="controladores.PuestosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String nombre_puesto= request.getParameter("bnombre_puesto");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    
String mensaje = "Busqueda exitosa.";
String contenido =PuestosControlador.buscarNombre(nombre_puesto, pagina);

JSONObject obj = new JSONObject();
obj.put("mensaje", mensaje);
obj.put("contenido", contenido);

out.print(obj);
out.flush();
        
%>