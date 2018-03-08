
<%@page import="org.json.simple.JSONObject"%>
<%@page import="controladores.ClientesControlador"%>
<%@page import="modelos.Clientes"%>
<%
    int id_cliente = 0;
    if (request.getParameter("id_cliente") != "") {
        id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
        
      
    }
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Clientes cliente = new Clientes();
    cliente.setId_cliente(id_cliente);

    cliente = ClientesControlador.buscarId(cliente);
    if (id_cliente != 0) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_cliente", cliente.getId_cliente());
    obj.put("nombre_cliente", cliente.getNombre_cliente());
    obj.put("apellido_cliente", cliente.getApellido_cliente());
    obj.put("id_ciudad", cliente.getCiudad().getId_ciudad());
    obj.put("nombre_ciudad", cliente.getCiudad().getNombre_ciudad());
    obj.put("ruc_cliente", cliente.getRuc_cliente());
    obj.put("cedula_cliente", cliente.getCedula_cliente());
    obj.put("telefono_cliente", cliente.getTelefono_cliente());
    
    out.print(obj);
    out.flush();
%>