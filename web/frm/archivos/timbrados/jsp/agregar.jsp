
<%@page import="modelos.Puestos"%>
<%@page import="utiles.Utiles"%>
<%@page import="modelos.Establecimientos"%>
<%@page import="controladores.TimbradosControlador"%>
<%@page import="modelos.Timbrados"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_timbrado = Integer.parseInt(request.getParameter("id_timbrado"));
    String numero_timbrado =request.getParameter("numero_timbrado");
    String sfecha_inicio = request.getParameter("fecha_inicio");
    java.sql.Date Fecha_inicio = Utiles.stringToSqlDate(sfecha_inicio);
    String sfecha_vencimiento = request.getParameter("fecha_vencimiento");
    java.sql.Date Fecha_vencimiento = Utiles.stringToSqlDate(sfecha_vencimiento);
    String sfecha_actual = request.getParameter("fecha_actual");
    java.sql.Date Fecha_actual = Utiles.stringToSqlDate(sfecha_actual);
    int desde_timbrado = Integer.parseInt(request.getParameter("desde_timbrado"));
    int hasta_timbrado = Integer.parseInt(request.getParameter("hasta_timbrado"));
    int id_puesto = Integer.parseInt(request.getParameter("id_puesto"));
    int id_establecimiento = Integer.parseInt(request.getParameter("id_establecimiento"));
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Puestos puesto = new Puestos();
    puesto.setId_puesto(id_puesto);
    Timbrados timbrado = new Timbrados();
    Establecimientos establecimiento = new Establecimientos();
    establecimiento.setId_establecimiento(id_establecimiento);
    
    
    timbrado.setId_timbrado(id_timbrado);
    timbrado.setNumero_timbrado(numero_timbrado);
    timbrado.setFecha_inicio(Fecha_inicio);
    timbrado.setFecha_vencimiento(Fecha_vencimiento);
    timbrado.setFecha_actual(Fecha_actual);
    timbrado.setDesde_timbrado(desde_timbrado);
    timbrado.setHasta_timbrado(hasta_timbrado);
    timbrado.setPuesto(puesto);
    timbrado.setEstablecimiento(establecimiento);
    if (TimbradosControlador.agregar(timbrado)) {
        tipo = "success";
        mensaje = "Datos agregados";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>