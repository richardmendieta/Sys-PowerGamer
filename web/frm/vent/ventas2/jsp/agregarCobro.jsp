<%@page import="controladores.VentasControlador"%>
<%@page import="modelos.Cobros"%>
<%@page import="modelos.Pagos"%>
<%@page import="modelos.Ventas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%

    int id_venta = Integer.parseInt(request.getParameter("id_venta"));
    int id_pago = Integer.parseInt(request.getParameter("id_pago"));
    int id_cobro = Integer.parseInt(request.getParameter("id_cobro"));
    int total = Integer.parseInt(request.getParameter("total"));

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    Ventas venta = new Ventas();
    venta.setId_venta(id_venta);
    Pagos pago = new Pagos();
    pago.setId_pago(id_pago);

    Cobros cobro = new Cobros();
    cobro.setId_cobro(id_cobro);
    cobro.setTotal(total);
    cobro.setVenta(venta);
    cobro.setPago(pago);

 VentasControlador.modificarestadoVenta(venta);
    if (VentasControlador.agregarCobro(cobro)) {
        tipo = "success";
        mensaje = "Datos agregados.";
   
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);

    out.print(obj);
    out.flush();

%>