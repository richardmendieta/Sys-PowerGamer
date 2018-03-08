<%@page import="controladores.ClientesControlador"%>
<%@page import="modelos.Clientes"%>
<%@page import="org.json.simple.JSONObject"%>

<%
    int cedula_cliente = 0;
    if (request.getParameter("cedula_cliente") != "") {
        cedula_cliente = Integer.parseInt(request.getParameter("cedula_cliente"));
    }
    
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
   
    
    
    Clientes cliente = ClientesControlador.buscarCedula(cedula_cliente);
    if (cliente!= null) {
    
        tipo = "success";
        mensaje = "cedula ya existe";
        nuevo = "false";
    }else{
        
         tipo = "success";
        mensaje = "Agregar Cedula";
        nuevo = "true";
        cliente=new Clientes();
        cliente.setCedula_cliente(cedula_cliente);
        
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    
    obj.put("cedula_cliente", cliente.getCedula_cliente());
   
    out.print(obj);
    out.flush();
%>