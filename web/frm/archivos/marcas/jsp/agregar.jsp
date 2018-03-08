<%@page import="controladores.MarcasControlador"%>
<%@page import="modelos.Marcas"%>
<%@page import="org.json.simple.JSONObject" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int id_marca=Integer.parseInt(request.getParameter("id_marca"));
    String nombre_marca=request.getParameter("nombre_marca");
    Marcas marca=new Marcas();
    marca.setId_marca(id_marca);
    marca.setNombre_marca(nombre_marca);
    String mensaje="Datos no Agregados";
    if(MarcasControlador.agregar(marca)){
        mensaje="Datos agregados correctamente";
    };
    JSONObject obj=new JSONObject();
    obj.put("mensaje",String.valueOf(mensaje));
    out.print(obj);
    out.flush();
    %>
