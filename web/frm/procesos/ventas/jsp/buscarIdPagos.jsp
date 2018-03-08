<%@page import="modelos.Pagos"%>
<%@page import="controladores.PagosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
      int id_pago = 0;
    if(request.getParameter("id_pago") != ""){
       id_pago = Integer.parseInt(request.getParameter("id_pago"));
        
    }
    
    String tipo="error";
    String mensaje="Datos no encontrados";
    String nuevo="true";
    Pagos pago=new Pagos();
    pago.setId_pago(id_pago);
    pago=PagosControlador.buscarId(pago);
   
    if(id_pago!= 0){
        tipo="success";
        mensaje="Datos encontrados";
        nuevo="false";
    }
    JSONObject obj= new JSONObject();
    obj.put("tipo",tipo);
    obj.put("mensaje",mensaje);
    obj.put("nuevo",nuevo);
    
    obj.put("id_pago",pago.getId_pago());
    obj.put("tipo_pago",pago.getTipo_pago());

    
    out.print(obj);
    out.flush(); 
%>
