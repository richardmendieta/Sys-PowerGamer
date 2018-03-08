
<%@page import="modelos.Establecimientos"%>
<%@page import="controladores.EstablecimientosControlador"%>
<%@page import="org.json.simple.JSONObject"%>

<%
    int id_establecimiento = 0;
    if (request.getParameter("id_establecimiento") != "") {
        id_establecimiento = Integer.parseInt(request.getParameter("id_establecimiento"));
        
      
    }
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Establecimientos establecimiento = new Establecimientos();
    establecimiento.setId_establecimiento(id_establecimiento);

    establecimiento = EstablecimientosControlador.buscarId(establecimiento);
    if (id_establecimiento != 0) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_establecimiento", establecimiento.getId_establecimiento());
    obj.put("nombre_establecimiento", establecimiento.getNombre_establecimiento());
    obj.put("actividad_economica", establecimiento.getActividad_economica());
    obj.put("ruc_establecimiento", establecimiento.getRuc_establecimiento());
    obj.put("representante_establecimiento", establecimiento.getRepresentante_establecimiento());
    obj.put("direccion_establecimiento", establecimiento.getDireccion_establecimiento());
    obj.put("telefono_establecimiento", establecimiento.getTelefono_establecimiento());
    obj.put("id_ciudad", establecimiento.getCiudad().getId_ciudad());
    obj.put("nombre_ciudad", establecimiento.getCiudad().getNombre_ciudad());

   
    out.print(obj);
    out.flush();
%>