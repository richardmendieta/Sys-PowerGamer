
<%@page import="controladores.EstablecimientosControlador"%>
<%@page import="modelos.Ciudades"%>
<%@page import="modelos.Establecimientos"%>
<%@page import="org.json.simple.JSONObject"%>

<%
    int id_establecimiento = Integer.parseInt(request.getParameter("id_establecimiento"));
    String nombre_establecimiento = request.getParameter("nombre_establecimiento");
    String actividad_economica = request.getParameter("actividad_economica");
    String ruc_establecimiento = request.getParameter("ruc_establecimiento");
    String representante_establecimiento = request.getParameter("representante_establecimiento");
    String direccion_establecimiento = request.getParameter("direccion_establecimiento");
    String telefono_establecimiento = request.getParameter("telefono_establecimiento");
    int id_ciudad = Integer.parseInt(request.getParameter("id_ciudad"));

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    Establecimientos establecimiento = new Establecimientos();
    establecimiento.setId_establecimiento(id_establecimiento);
    establecimiento.setNombre_establecimiento(nombre_establecimiento);
    establecimiento.setActividad_economica(actividad_economica);
    establecimiento.setRuc_establecimiento(ruc_establecimiento);
    establecimiento.setRepresentante_establecimiento(representante_establecimiento);
    establecimiento.setDireccion_establecimiento(direccion_establecimiento);
    establecimiento.setTelefono_establecimiento(telefono_establecimiento);

    Ciudades ciudad = new Ciudades();
    ciudad.setId_ciudad(id_ciudad);
    establecimiento.setCiudad(ciudad);

    if (EstablecimientosControlador.agregar(establecimiento)) {
        tipo = "success";
        mensaje = "Datos agregados";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>