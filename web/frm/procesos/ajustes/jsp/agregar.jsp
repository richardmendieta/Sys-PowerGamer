<%@page import="controladores.AjustesControlador"%>
<%@page import="modelos.Usuarios"%>
<%@page import="utiles.Utiles"%>
<%@page import="modelos.Ajustes"%>
<%@page import="modelos.Proveedores"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%

    int id_ajuste = Integer.parseInt(request.getParameter("id_ajuste"));
  //  int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
    int id_usuario = Integer.parseInt(request.getParameter("id_usuario")); 
   
  String sfecha_ajuste = request.getParameter("fecha_ajuste");
  String orden_ajuste = request.getParameter("orden_ajuste");
 
  
    java.sql.Date fecha_ajuste = Utiles.stringToSqlDate(sfecha_ajuste);
    

    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Usuarios usuario = new Usuarios();
    usuario.setId_usuario(id_usuario);

      //   Usuarios usuario = new Usuarios();
     //       usuario.setId_usuario(id_usuario);
      //      usuario.setNombre_usuario(nombre_usuario);
    Ajustes ajuste = new Ajustes();
    ajuste.setId_ajuste(id_ajuste);
   
    ajuste.setFecha_ajuste(fecha_ajuste);
    ajuste.setOrden_ajuste(orden_ajuste);
    ajuste.setUsuario(usuario);
      //ajuste.setUsuario(usuario);
    
    if (AjustesControlador.agregar(ajuste)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_ajuste", String.valueOf(ajuste.getId_ajuste()));
    out.print(obj);
    out.flush();
    
%>