
<%@page import="controladores.FormulariosControlador"%>
<%@page import="modelos.Formularios"%>
<%@page import="org.json.simple.JSONObject" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int id_formulario=Integer.parseInt(request.getParameter("id_formulario"));
    String nombre_formulario=request.getParameter("nombre_formulario");
    String codigo_formulario=request.getParameter("codigo_formulario");
    
    String tipo="error";
    Formularios formulario=new Formularios();
    formulario.setId_formulario(id_formulario);
    formulario.setNombre_formulario(nombre_formulario);
    formulario.setCodigo_formulario(codigo_formulario);
    String mensaje="Datos no Modificados";
    if(FormulariosControlador.modificar(formulario)){
        tipo="success";
        mensaje="Datos modificados correctamente";
        
    };
    JSONObject obj=new JSONObject();
    obj.put("tipo",tipo);
    obj.put("mensaje",String.valueOf(mensaje));
    out.print(obj);
    out.flush();
    %>
