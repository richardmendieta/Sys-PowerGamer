

<%@page import="controladores.PagosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String tipo_pago=request.getParameter("btipo_pago");
    int pagina=Integer.parseInt(request.getParameter("bpagina"));
    
    String mensaje="Busqueda exitosa";
    String contenido=PagosControlador.buscarNombre(tipo_pago,pagina);
    JSONObject obj=new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido",contenido);
    out.print(obj);
    out.flush();
%>
