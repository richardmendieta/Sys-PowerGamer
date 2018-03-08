
<%@page import="controladores.PermisosControlador"%>
<%@page import="modelos.Permisos"%>
<%@page import="org.json.simple.JSONObject" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int id_permiso=Integer.parseInt(request.getParameter("id_permiso"));
    String nombre_permiso=request.getParameter("nombre_permiso");
    String orden_permiso=request.getParameter("orden_permiso");
    
    String tipo="error";
    Permisos permiso=new Permisos();
    permiso.setId_permiso(id_permiso);
    
    permiso.setNombre_permiso(nombre_permiso);
    permiso.setOrden_permiso(orden_permiso);
    String mensaje="Datos no Modificados";
    if(PermisosControlador.modificar(permiso)){
        tipo="success";
        mensaje="Datos modificados correctamente";
        
    };
    JSONObject obj=new JSONObject();
    obj.put("tipo",tipo);
    obj.put("mensaje",String.valueOf(mensaje));
    out.print(obj);
    out.flush();
    %>
