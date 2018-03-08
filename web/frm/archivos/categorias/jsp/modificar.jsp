
<%@page import="controladores.CategoriasControlador"%>
<%@page import="modelos.Categorias"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int id_categoria=Integer.parseInt(request.getParameter("id_categoria"));
    String nombre_categoria=request.getParameter("nombre_categoria");
    
    String tipo="error";
    Categorias categoria=new Categorias();
    categoria.setId_categoria(id_categoria);
    categoria.setNombre_categoria(nombre_categoria);
    String mensaje="Datos no Modificados";
    if(CategoriasControlador.modificar(categoria)){
        tipo="success";
        mensaje="Datos modificados correctamente";
    };
    JSONObject obj=new JSONObject();
    obj.put("tipo",tipo);
    obj.put("mensaje",String.valueOf(mensaje));
    out.print(obj);
    out.flush();
    %>
