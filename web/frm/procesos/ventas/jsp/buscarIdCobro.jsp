<%@page import="controladores.VentasControlador"%>
<%@page import="modelos.Cobros"%>
<%@page import="modelos.Productos"%>
<%@page import="modelos.Ventas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_venta = Integer.parseInt(request.getParameter("id_venta"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    
    Cobros cobro=new Cobros();
    
    Ventas venta=new Ventas();
    venta.setId_venta(id_venta);

     venta = VentasControlador.buscarIdCobro(id_venta,cobro);
    if (venta != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } 
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_cobro", String.valueOf(cobro.getId_cobro()));
    obj.put("id_venta", String.valueOf(cobro.getVenta().getId_venta()));
    obj.put("total_cobro",cobro.getTotal_cobro());
   
   out.print(obj);
    out.flush();
%>