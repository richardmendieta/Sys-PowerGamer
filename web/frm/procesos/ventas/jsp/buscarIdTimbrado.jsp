
<%@page import="modelos.Timbrados"%>
<%@page import="controladores.TimbradosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%
    int id_timbrado = 0;
    if (request.getParameter("id_timbrado") != "") {
        id_timbrado = Integer.parseInt(request.getParameter("id_timbrado"));
    }
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Timbrados timbrado = new Timbrados();
    timbrado.setId_timbrado(id_timbrado);

    timbrado = TimbradosControlador.buscarId(timbrado);
    if (id_timbrado != 0) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_timbrado", timbrado.getId_timbrado());
    obj.put("numero_timbrado", timbrado.getNumero_timbrado());
    obj.put("fecha_inicio", String.valueOf(timbrado.getFecha_inicio()));
    obj.put("fecha_vencimiento", String.valueOf(timbrado.getFecha_vencimiento()));
    obj.put("fecha_actual", String.valueOf(timbrado.getFecha_actual()));
    obj.put("desde_timbrado", timbrado.getDesde_timbrado());
    obj.put("hasta_timbrado", timbrado.getHasta_timbrado());
    obj.put("id_establecimiento", timbrado.getEstablecimiento().getId_establecimiento());
    obj.put("nombre_establecimiento", timbrado.getEstablecimiento().getNombre_establecimiento());
    obj.put("id_puesto", timbrado.getPuesto().getId_puesto());
    obj.put("nombre_puesto", timbrado.getPuesto().getNombre_puesto());

    out.print(obj);
    out.flush();
%>