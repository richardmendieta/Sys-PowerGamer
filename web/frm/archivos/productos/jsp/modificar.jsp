
<%@page import="org.json.simple.JSONObject"%>
<%@page import="controladores.ProductosControlador"%>
<%@page import="modelos.Categorias"%>
<%@page import="modelos.Productos"%>
<%
    int id_producto = Integer.parseInt(request.getParameter("id_producto"));
    String nombre_producto = request.getParameter("nombre_producto");
    int costo_venta = Integer.parseInt(request.getParameter("costo_venta"));
    int costo_compra = Integer.parseInt(request.getParameter("costo_compra"));
    int iva_producto = Integer.parseInt(request.getParameter("iva_producto"));
    
    int id_categoria = Integer.parseInt(request.getParameter("id_categoria"));

    String tipo = "error";
    Productos producto = new Productos();
    producto.setId_producto(id_producto);
    producto.setNombre_producto(nombre_producto);
    producto.setCosto_venta(costo_venta);
    producto.setCosto_compra(costo_compra);
    producto.setIva_producto(iva_producto);

    Categorias categoria = new Categorias();
    categoria.setId_categoria(id_categoria);
    producto.setCategoria(categoria);

    

    producto.setCategoria(categoria);
    String mensaje = "Datos no modificados";
    if (ProductosControlador.modificar(producto)) {
        tipo = "success";
        mensaje = "Datos modificados correctamente";
    };

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", String.valueOf(mensaje));
    out.print(obj);
    out.flush();
%>
