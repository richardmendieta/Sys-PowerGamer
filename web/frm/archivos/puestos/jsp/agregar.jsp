
<%@page import="modelos.Establecimientos"%>
<%@page import="controladores.PuestosControlador"%>
<%@page import="modelos.Puestos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    //int id_puesto=Integer.parseInt(request.getParameter("id_puesto"));
    String nombre_puesto=request.getParameter("nombre_puesto");
   
    int id_establecimiento=Integer.parseInt(request.getParameter("id_establecimiento"));
    
    Establecimientos establecimiento=new Establecimientos();
    establecimiento.setId_establecimiento(id_establecimiento);
    
    
    Puestos puesto=new Puestos();
    
//    puesto.setId_puesto(id_puesto);
    puesto.setNombre_puesto(nombre_puesto);
  puesto.setEstablecimiento(establecimiento);
    String mensaje="Datos no Agregados";
    if(PuestosControlador.agregar(puesto)){
        mensaje="Datos agregados correctamente";
    };
    JSONObject obj=new JSONObject();
    obj.put("mensaje",String.valueOf(mensaje));
    out.print(obj);
    out.flush();
    %>
