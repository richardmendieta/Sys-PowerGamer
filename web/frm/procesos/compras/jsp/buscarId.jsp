
<%@page import="controladores.ComprasControlador"%>
<%@page import="controladores.DetallesComprasControlador"%>
<%@page import="modelos.Proveedores"%>
<%@page import="modelos.Compras"%>
<%@page import="modelos.Compras"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
     int id_compra = 0;
    if (request.getParameter("id_compra") != "") {
        id_compra = Integer.parseInt(request.getParameter("id_compra"));
        
    }
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    Compras compras = ComprasControlador.buscarId(id_compra);
    if (id_compra != 0) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        compras = new Compras();
        compras.setId_compra(0);
        compras.setEstado_compra("ACTIVO");
       
        
        Proveedores proveedor = new Proveedores();
        compras.setProveedor(proveedor);
        
        java.sql.Date fecha_compra = new java.sql.Date(new java.util.Date().getTime());
        compras.setFecha_compra(fecha_compra);
    }

    String contenido_detalle = DetallesComprasControlador.buscarIdCompra(id_compra);

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_compra", String.valueOf(compras.getId_compra()));
    obj.put("numero_factura_compra", compras.getNumero_factura_compra());
    obj.put("fecha_compra", String.valueOf(compras.getFecha_compra()));
    obj.put("timbrado_compra", compras.getTimbrado_compra());
    obj.put("estado_compra", compras.getEstado_compra());
    obj.put("id_proveedor", String.valueOf(compras.getProveedor().getId_proveedor()));
    obj.put("nombre_proveedor", compras.getProveedor().getNombre_proveedor());
    obj.put("ruc_proveedor", compras.getProveedor().getRuc_proveedor());
    obj.put("contenido_detalle", contenido_detalle);

    out.print(obj);
    out.flush();
%>