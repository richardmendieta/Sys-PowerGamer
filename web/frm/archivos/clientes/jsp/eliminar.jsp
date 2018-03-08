
<%@page import="controladores.ClientesControlador"%>
<%@page import="modelos.Clientes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));

    String tipo = "error";
    String mensaje = "Datos no eliminados";

    Clientes cliente = new Clientes();
    cliente.setId_cliente(id_cliente);
    
    if (ClientesControlador.eliminar(cliente)) {
        tipo = "success";
        mensaje = "Datos eliminados correctamente";
    };

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", String.valueOf(mensaje));
    out.print(obj);
    out.flush();
%>
