<%@page import="controladores.ClientesControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String nombre_cliente= request.getParameter("bnombre_cliente");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    
String mensaje = "Busqueda exitosa.";
String contenido =ClientesControlador.buscarNombre(nombre_cliente, pagina);

JSONObject obj = new JSONObject();
obj.put("mensaje", mensaje);
obj.put("contenido", contenido);

out.print(obj);
out.flush();
        
%>