

<%@page import="controladores.DetallesAjustesControlador"%>
<%@page import="controladores.AjustesControlador"%>
<%@page import="modelos.Proveedores"%>
<%@page import="modelos.Ajustes"%>


<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_ajuste = 0;
    if (request.getParameter("id_ajuste") != "") {
        id_ajuste = Integer.parseInt(request.getParameter("id_ajuste"));

    }
  
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    Ajustes ajustes = AjustesControlador.buscarId(id_ajuste);
    if (id_ajuste != 0) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } 
    
    String contenido_detalle = DetallesAjustesControlador.buscarIdAjuste(id_ajuste);
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_ajuste", String.valueOf(ajustes.getId_ajuste()));
   // obj.put("id_usuario", String.valueOf(ajustes.getUsuario().getId_usuario()));
    //obj.put("nombre_usuario", ajustes.getUsuario().getNombre_usuario());
  
    
    
    obj.put("fecha_ajuste", String.valueOf(ajustes.getFecha_ajuste()));
    obj.put("orden_ajuste", ajustes.getOrden_ajuste());
  

   obj.put("contenido_detalle", contenido_detalle);
    
    out.print(obj);
    out.flush();
%>