function agregarPuesto(){
    var datosPuesto=$("#formPrograma").serialize();
    $.ajax({
        type:'POST',
        url:'jsp/agregar.jsp',
        data:datosPuesto,
        dataType:'json',
        beforeSend:function(objeto){
            $("#mensajes").html("Enviando datos al servidor...");
        },
       success: function (json) {
            $("#mensajes").html(json.mensaje);
            limpiarCampos();
          
        },
        error: function(e){
            $("#mensajes").html("No se puede gregar los datos, Error:"+e.status);
        },
        complete: function(objeto, exito,error){
            $("#id_puesto").focus();
            $("#id_puesto").select();
            if(exito==="success"){
                
            }
        }
    });
}

function buscarIdPuesto(){
    var datosPuesto= $("#formPrograma").serialize();
    
    $.ajax({
        type:'POST',
        url:'jsp/buscarId.jsp',
        data:datosPuesto,
        dataType:'json',
        beforeSend: function(objeto){
            $("#mensajes").html("Enviando daatos al servidor...");
        },
        success: function(json){
            $("#mensajes").html(json.mensaje);
            $("#id_puesto").val(json.id_puesto);
            $("#nombre_puesto").val(json.nombre_puesto);
           
            
            $("#id_establecimiento").val(json.id_establecimiento);
            $("#nombre_establecimiento").val(json.nombre_establecimiento);
            if(json.nuevo==="true"){
                $("#botonAgregar").prop('disabled',false);
                $("#botonModificar").prop('disabled',true);
                $("#botonEliminar").prop('disabled',true);
                siguienteCampo("#nombre_puesto","#codigo_puesto",false);
                siguienteCampo("#codigo_puesto","#id_rol",false);
                siguienteCampo("#id_puesto","#botonAgregar",false);
            }
            else{
                $("#botonAgregar").prop('disabled',true);
                $("#botonModificar").prop('disabled',false);
                $("#botonEliminar").prop('disabled',false);
                siguienteCampo("#nombre_puesto","#codigo_puesto",false);
               
                siguienteCampo("#id_puesto","#botonAgregar",false);
            }
        },
        error: function(e){
            $("#mensajes").html("No se pudo recuperar los datos Error:"+e.status);
        },
        complete: function(objeto,exito,error){
            if(exito==="success"){
                
            }
        }
    });
}

function buscarNombrePuesto(){
   
    var datosPuesto= $("#formBuscar").serialize();
    
    $.ajax({
        type:'POST',
        url:'jsp/buscarNombre.jsp',
        data:datosPuesto,
        dataType:'json',
        beforeSend: function(objeto){
            $("#mensajes").html("Enviando daatos al servidor...");
            //$("#contenidoBusqueda").css("display","none");
        },
        success: function(json){
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function(){
                var id=$(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_puesto").val(id);
                $("#nombre_puesto").focus();
                buscarIdPuesto();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function(e){
            $("#mensajes").html("No se pudo buscar registros Error:"+e.status);
        },
        complete: function(objeto,exito,error){
            if(exito==="success"){
                
            }
        }
    });
}

function eliminarPuesto(){
    var datosPuesto = $("#formPrograma").serialize();
    $.ajax({
        type:'POST',
        url:'jsp/eliminar.jsp',
        data:datosPuesto,
        dataType:'json',
        beforeSend: function(objeto){
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function(json){
            $("#mensajes").html(json.mensaje);
        },
        error: function (e){
            $("#mensajes").html("No se pudo modificar los datos error"+e.status);
        },
        complete: function(objeto, exito,error){
            limpiarCampos();
           // $("#id_puesto").focus();
           // $("#id_puesto").select();
            if(exito === "success"){
                
            }
        }
    });
}

function limpiarCampos(){
    $("#id_puesto").val("0");
    $("#nombre_puesto").val("");
   
    $("#id_puesto").focus();
    $("#id_establecimiento").val("");
    $("#nombre_establecimiento").val("");
    //$("#id_puesto").focus();
}

function modificarPuesto(){
     var datosPuesto = $("#formPrograma").serialize();
    $.ajax({
        type:'POST',
        url:'jsp/modificar.jsp',
        data:datosPuesto,
        dataType:'json',
        beforeSend: function(objeto){
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function(json){
            $("#mensajes").html(json.mensaje);
            limpiarCampos();
        },
        error: function (e){
            $("#mensajes").html("No se pudo modificar los datos error"+e.status);
        },
        complete: function(objeto, exito,error){
           // limpiarCampos();
            $("#id_puesto").focus();
            $("#id_puesto").select();
            if(exito === "success"){
                
            }
        }
    });
}

function soloLetras(e) {
    key = e.keyCode || e.which;
    tecla = String.fromCharCode(key).toLowerCase();
    letras = " áéíóúabcdefghijklmnñopqrstuvwxyz";
    especiales = "8-37-39-46";

    tecla_especial = false;
    for (var i in especiales) {
        if (key === especiales[i]) {
            tecla_especial = true;
            break;
        }
    }

    if (letras.indexOf(tecla) === -1 && !tecla_especial) {
        return false;
    }
}


function buscarIdEstablecimiento() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdEstablecimientos.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_establecimiento").val(json.id_establecimiento);
            $("#nombre_establecimiento").val(json.nombre_establecimiento);
            $("#actividad_economica").val(json.actividad_economica);
            $("#ruc_establecimiento").val(json.ruc_establecimiento);
            $("#representante_establecimiento").val(json.representante_establecimiento);
            $("#telefono_establecimiento").val(json.telefono_establecimiento);
            $("#direccion_establecimiento").val(json.direccion_establecimiento);
//             $("#id_ciudad").val(json.id_ciudad);
//             $("#nombre_ciudad").val(json.nombre_ciudad);
             
//              if (json.nuevo==="true") {
//                $("#botonAgregar").prop('disabled',false);
//                $("#botonModificar").prop('disabled',true);
//                $("#botonEliminar").prop('disabled',true);
//
//            } else {
//                $("#botonAgregar").prop('disabled', true);
//                $("#botonModificar").prop('disabled', false);
//                $("#botonEliminar").prop('disabled', false);
//            }
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarNombreEstablecimiento() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreEstablecimientos.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
            $("#contenidoBusqueda").css("display", "none");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_establecimiento").val(id);
                $("#nombre_establecimiento").focus();
                buscarIdEstablecimiento();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });

}