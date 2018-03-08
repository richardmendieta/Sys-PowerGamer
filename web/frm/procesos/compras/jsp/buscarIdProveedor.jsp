
<%@page import="modelos.Proveedores"%>
<%@page import="controladores.ProveedoresControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%
    int id_proveedor= 0;
    if(request.getParameter("id_proveedor") != ""){
       id_proveedor = Integer.parseInt(request.getParameter("id_proveedor"));
        
    }
    String tipo= "error";
    String mensaje="Datos no encontrados.";
    String nuevo="true";
    Proveedores proveedor = new Proveedores();
    proveedor.setId_proveedor(id_proveedor);
    
proveedor= ProveedoresControlador.buscarId(proveedor);
if(proveedor.getId_proveedor() != 0){
    tipo="success";
    mensaje="Datos encontados";
    nuevo="false";
    
}
JSONObject obj = new JSONObject();
obj.put("tipo", tipo);
 obj.put("mensaje", mensaje);
 obj.put("nuevo", nuevo);
 
 
 obj.put("id_proveedor", proveedor.getId_proveedor());
 //System.out.println( proveedor.getId_proveedor());
 obj.put("nombre_proveedor", proveedor.getNombre_proveedor());
 //System.out.println(proveedor.getNombre_proveedor());
 obj.put("ruc_proveedor", proveedor.getRuc_proveedor());
 obj.put("telefono1_proveedor", proveedor.getTelefono1_proveedor());
// System.out.println(proveedor.getTelefono1_proveedor());
 obj.put("telefono2_proveedor",proveedor.getTelefono2_proveedor());
 //System.out.println(proveedor.getTelefono2_proveedor());
 obj.put("direccion_proveedor", proveedor.getDireccion_proveedor());
 obj.put("correo_proveedor", proveedor.getCorreo_proveedor());
 //obj.put("id_ciudad", proveedor.getCiudad().getId_ciudad());
 //obj.put("nombre_ciudad", proveedor.getCiudad().getNombre_ciudad());
 
 out.print(obj);
 out.flush();
%>