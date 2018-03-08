
<%@page import="utiles.Utiles"%>
<%@page import="controladores.ClientesControlador"%>
<%@page import="modelos.Ciudades"%>
<%@page import="modelos.Clientes"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
    String nombre_cliente = request.getParameter("nombre_cliente");
    String apellido_cliente = request.getParameter("apellido_cliente");
    int id_ciudad = Integer.parseInt(request.getParameter("id_ciudad"));
    String telefono_cliente = request.getParameter("telefono_cliente");
    String ruc_cliente = request.getParameter("ruc_cliente");
    int cedula_cliente = Integer.parseInt(request.getParameter("cedula_cliente"));


    String tipo = "error";
    Clientes cliente = new Clientes();
    cliente.setId_cliente(id_cliente);
    cliente.setNombre_cliente(nombre_cliente);
    cliente.setApellido_cliente(apellido_cliente);
    cliente.setRuc_cliente(ruc_cliente);
    cliente.setCedula_cliente(cedula_cliente);
    cliente.setTelefono_cliente(telefono_cliente);


    Ciudades ciudad = new Ciudades();
    ciudad.setId_ciudad(id_ciudad);
    cliente.setCiudad(ciudad);

    String mensaje = "Datos no modificados";
    if (ClientesControlador.modificar(cliente)) {
        tipo = "success";
        mensaje = "Datos modificados correctamente";
    };

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", String.valueOf(mensaje));
    out.print(obj);
    out.flush();
%>
