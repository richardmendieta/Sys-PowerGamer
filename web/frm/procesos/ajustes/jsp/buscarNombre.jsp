<%@page import="controladores.AjustesControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String orden_ajuste = request.getParameter("borden_ajuste");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
   
    String mensaje = "Búsqueda exitosa.";
    String contenido = AjustesControlador.buscarNombre(orden_ajuste, pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido", contenido);
    
    out.print(obj);
    out.flush();
%>