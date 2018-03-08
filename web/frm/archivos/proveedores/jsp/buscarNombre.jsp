
<%@page import="controladores.ProveedoresControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String nombre_proveedor= request.getParameter("bnombre_proveedor");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    
String mensaje = "Busqueda exitosa.";
String contenido =ProveedoresControlador.buscarNombre(nombre_proveedor, pagina);

JSONObject obj = new JSONObject();
obj.put("mensaje", mensaje);
obj.put("contenido", contenido);

out.print(obj);
out.flush();
        
%>