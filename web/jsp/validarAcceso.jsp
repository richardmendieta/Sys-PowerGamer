
<%@page import="org.json.simple.JSONObject"%>
<%@page import="modelos.Usuarios"%>
<%@page import="modelos.Roles"%>
<%@page import="controladores.UsuariosControlador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
     String login_usuario = request.getParameter("login_usuario");
    String password_usuario = request.getParameter("password_usuario");
    
    String acceso = "false";
    
    Usuarios usuario = new Usuarios(0, "", password_usuario, login_usuario,  new Roles());
    if(UsuariosControlador.validarAcceso(usuario, request)!=null){
        acceso = "true";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("acceso", acceso);
    out.print(obj);
    out.flush();
%>