
<%@page import="controladores.MenusControlador"%>
<%@page import="modelos.Menus"%>
<%@page import="org.json.simple.JSONObject" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    //int id_menu=Integer.parseInt(request.getParameter("id_menu"));
    String nombre_menu=request.getParameter("nombre_menu");
    String codigo_menu=request.getParameter("codigo_menu");
    Menus menu=new Menus();
    //menu.setId_menu(id_menu);
    menu.setNombre_menu(nombre_menu);
    menu.setCodigo_menu(codigo_menu);
    String mensaje="Datos no Agregados";
    if(MenusControlador.agregar(menu)){
        mensaje="Datos agregados correctamente";
    };
    JSONObject obj=new JSONObject();
    obj.put("mensaje",String.valueOf(mensaje));
    out.print(obj);
    out.flush();
    %>
