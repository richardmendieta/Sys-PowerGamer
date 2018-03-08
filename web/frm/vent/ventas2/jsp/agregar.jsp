
<%@page import="modelos.Puestos"%>
<%@page import="modelos.Establecimientos"%>
<%@page import="modelos.Timbrados"%>
<%@page import="controladores.VentasControlador"%>
<%@page import="modelos.Ventas"%>
<%@page import="modelos.Clientes"%>
<%@page import="utiles.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%

    //int id_venta = Integer.parseInt(request.getParameter("id_venta"));
    String sfecha_venta = request.getParameter("fecha_venta");
    java.sql.Date fecha_venta = Utiles.stringToSqlDate(sfecha_venta);
    String estado_venta = request.getParameter("estado_venta");
    int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
    int numero_factura = Integer.parseInt(request.getParameter("numero_factura"));
    int id_timbrado = Integer.parseInt(request.getParameter("id_timbrado"));  
    String numero_timbrado = request.getParameter("numero_timbrado");
    int id_establecimiento = Integer.parseInt(request.getParameter("id_establecimiento"));
    int id_puesto = Integer.parseInt(request.getParameter("id_puesto"));

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    Clientes cliente = new Clientes();
    cliente.setId_cliente(id_cliente);
    Ventas venta = new Ventas();
    //venta.setId_venta(id_venta);
    venta.setFecha_venta(fecha_venta);
    venta.setEstado_venta(estado_venta);
    venta.setNumero_factura(numero_factura);
    venta.setCliente(cliente);
    Timbrados timbrado = new Timbrados();
    timbrado.setId_timbrado(id_timbrado);
    timbrado.setNumero_timbrado(numero_timbrado);
    Establecimientos establecimiento = new Establecimientos();
    establecimiento.setId_establecimiento(id_establecimiento);
    timbrado.setEstablecimiento(establecimiento);
    Puestos puesto = new Puestos();
    puesto.setId_puesto(id_puesto);
    timbrado.setPuesto(puesto);
    venta.setTimbrado(timbrado);
    
    if (VentasControlador.agregar(venta)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_venta", String.valueOf(venta.getId_venta()));
    out.print(obj);
    out.flush();

%>