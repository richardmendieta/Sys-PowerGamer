<%@page import="controladores.DetallesAjustesControlador"%>
<%@page import="modelos.DetallesAjustes"%>
<%@page import="controladores.AjustesControlador"%>
<%@page import="modelos.Ajustes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_ajuste = Integer.parseInt(request.getParameter("id_ajuste"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    Ajustes ajuste = new Ajustes();
    ajuste.setId_ajuste(id_ajuste);
    DetallesAjustes detalleajuste = new DetallesAjustes();
    detalleajuste.setAjuste(ajuste);

    if (DetallesAjustesControlador.eliminarc(detalleajuste)) {
        tipo = "success";
        mensaje = "Datos eliminados.";

    }
    ajuste = new Ajustes();
    ajuste.setId_ajuste(id_ajuste);

    AjustesControlador.eliminar(ajuste);

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>