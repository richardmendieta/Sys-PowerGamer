<%@page import="modelos.Establecimientos"%>
<%@page import="modelos.Puestos"%>
<%@page import="utiles.Utiles"%>
<%@page import="java.sql.Date"%>
<%@page import="controladores.TimbradosControlador"%>
<%@page import="modelos.Timbrados"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%

    int id_timbrado = Integer.parseInt(request.getParameter("id_timbrado"));
    int desde_timbrado = Integer.parseInt(request.getParameter("desde_timbrado"));
    int hasta_timbrado = Integer.parseInt(request.getParameter("hasta_timbrado"));
    String numero_timbrado = request.getParameter("numero_timbrado");
    String sFecha_inicio = request.getParameter("fecha_inicio");
    java.sql.Date Fecha_inicio = Utiles.stringToSqlDate(sFecha_inicio);
    String sFecha_vencimiento = request.getParameter("fecha_vencimiento");
    java.sql.Date Fecha_vencimiento = Utiles.stringToSqlDate(sFecha_vencimiento);
    String sFecha_actual = request.getParameter("fecha_actual");
    java.sql.Date Fecha_actual = Utiles.stringToSqlDate(sFecha_actual);
    int id_puesto = Integer.parseInt(request.getParameter("id_puesto"));
    int id_establecimiento = Integer.parseInt(request.getParameter("id_establecimiento"));
    String tipo = "error";
    Timbrados timbrado = new Timbrados();
    timbrado.setId_timbrado(id_timbrado);
    timbrado.setNumero_timbrado(numero_timbrado);
    timbrado.setFecha_inicio(Fecha_inicio);
    timbrado.setFecha_vencimiento(Fecha_vencimiento);
    timbrado.setFecha_actual(Fecha_actual);
    timbrado.setDesde_timbrado(desde_timbrado);
    timbrado.setHasta_timbrado(hasta_timbrado);
    Puestos puesto = new Puestos();
    puesto.setId_puesto(id_puesto);
    timbrado.setPuesto(puesto);
    Establecimientos establecimiento = new Establecimientos();
    establecimiento.setId_establecimiento(id_establecimiento);
    timbrado.setEstablecimiento(establecimiento);
    String mensaje = "Datos no modificados";
    if (TimbradosControlador.modificar(timbrado)) {
        tipo = "success";
        mensaje = "Datos modificados correctamente";
    };

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", String.valueOf(mensaje));
    out.print(obj);
    out.flush();
%>
