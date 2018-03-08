<%@page import="controladores.DetallesAjustesControlador"%>
<%@page import="modelos.Productos"%>
<%@page import="modelos.Ajustes"%>
<%@page import="modelos.DetallesAjustes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    int id_detalleajuste = Integer.parseInt(request.getParameter("id_detalleajuste"));
    int cantidad_ajuste = Integer.parseInt(request.getParameter("cantidad_ajuste"));
   int id_ajuste = Integer.parseInt(request.getParameter("id_ajuste"));
   
    int id_producto = Integer.parseInt(request.getParameter("id_producto")); 

    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    DetallesAjustes detalleajuste = new DetallesAjustes();
    detalleajuste.setId_detalleajuste(id_detalleajuste);
    detalleajuste.setCantidad_ajuste(cantidad_ajuste);

    Ajustes ajuste = new Ajustes();
    ajuste.setId_ajuste(id_ajuste);
    
    Productos producto = new Productos();
    producto.setId_producto(id_producto);
    
    detalleajuste.setAjuste(ajuste);
    detalleajuste.setProducto(producto);
      
    if (DetallesAjustesControlador.modificar(detalleajuste)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
    
%>