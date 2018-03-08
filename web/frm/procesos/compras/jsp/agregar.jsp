
<%@page import="controladores.ComprasControlador"%>
<%@page import="modelos.Compras"%>
<%@page import="modelos.Proveedores"%>
<%@page import="utiles.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%

    int id_compra = Integer.parseInt(request.getParameter("id_compra"));
    String numero_factura_compra = request.getParameter("numero_factura_compra");

    String sfecha_compra = request.getParameter("fecha_compra");
    java.sql.Date fecha_compra = Utiles.stringToSqlDate(sfecha_compra);
    String estado_compra = request.getParameter("estado_compra");
    int id_proveedor = Integer.parseInt(request.getParameter("id_proveedor"));

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    Proveedores proveedor = new Proveedores();
    proveedor.setId_proveedor(id_proveedor);
    Compras compra = new Compras();
    compra.setId_compra(id_compra);
    compra.setNumero_factura_compra(numero_factura_compra);
    compra.setFecha_compra(fecha_compra);

    compra.setEstado_compra(estado_compra);
    compra.setProveedor(proveedor);

    if (ComprasControlador.agregar(compra)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_compra", String.valueOf(compra.getId_compra()));
    out.print(obj);
    out.flush();

%>