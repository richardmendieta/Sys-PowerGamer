function agregarProveedor() {
    var datosFormulario = $("#formPrograma").serialize();
    alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/agregar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            limpiarCampo();
            $("#id_proveedor").focus();
            $("#id_proveedor").select();
        },
        error: function (e) {
            $("#mensajes").html("no se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#id_proveedor").focus();
        }

    });
}
function buscarIdProveedor() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarId.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");

        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_proveedor").val(json.id_proveedor);
            $("#nombre_proveedor").val(json.nombre_proveedor);
            $("#ruc_proveedor").val(json.ruc_proveedor);
            $("#telefono1_proveedor").val(json.telefono1_proveedor);
            $("#telefono2_proveedor").val(json.telefono2_proveedor);
            $("#direccion_proveedor").val(json.direccion_proveedor);
            $("#correo_proveedor").val(json.correo_proveedor);
            $("#id_ciudad").val(json.id_ciudad);
            $("#nombre_ciudad").val(json.nombre_ciudad);
        },
        error: function (e) {
            $("#mensaje").html("no se pudo recuperar los datos.");

        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarNombreProveedor() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombre.jsp',
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
                $("#id_proveedor").val(id);
                $("#nombre_proveedor").focus();
                buscarIdProveedor();
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
function modificarProveedor() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/modificar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            limpiarCampo();
        },
        error: function (e) {
            $("#mensaje").html("No se puedo modificar los datos error" + e.status);
        },
        complete: function (objeto, exito, error) {
            //limpiarCampos();
            $("#id_proveedor").focus();
            $("#id_proveedor").select();
            if (exito === "success") {

            }
        }
    });
}
function eliminarProveedor() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos error" + e.status);
        },
        complete: function (objeto, exito, error) {
            //limpiarCampo();
            $("#id_proveedor").focus();
            $("#id_proveedor").select();
            if (exito === "success") {

            }
        }
    });
}

function limpiarCampo() {
    $("#id_proveedor").val("0");
    $("#nombre_proveedor").val("");
    $("#ruc_proveedor").val("");
    $("#telefono1_proveedor").val("");
    $("#telefono2_proveedor").val("");
    $("#correo_proveedor").val("");
    $("#direccion_proveedor").val("");
    $("#id_proveedor").focus();
}

function buscarCedula() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarcedula.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);

            $("#ci_proveedor").val(json.ci_proveedor);


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

function buscarIdCiudad() {
    var datosFormulario = $("#formPrograma").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdCiudad.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando daatos al servidor...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_ciudad").val(json.id_ciudad);
            $("#nombre_ciudad").val(json.nombre_ciudad);
//            if(json.nuevo==="true"){
//                $("#botonAgregar").prop('disabled',false);
//                $("#botonModificar").prop('disabled',true);
//                $("#botonEliminar").prop('disabled',true);
//                siguienteCampo("#nombre_ciudad","#botonAgregar",false);
//            }
//            else{
//                $("#botonAgregar").prop('disabled',true);
//                $("#botonModificar").prop('disabled',false);
//                $("#botonEliminar").prop('disabled',false);
//                siguienteCampo("#nombre_ciudad","#botonAgregar",false);
//            }
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos Error:" + e.status);
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {

            }
        }
    });
}

function buscarNombreCiudad() {

    var datosFormulario = $("#formBuscar").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreCiudad.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando daatos al servidor...");
            //$("#contenidoBusqueda").css("display","none");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_ciudad").val(id);
                $("#nombre_ciudad").focus();
                buscarIdCiudad();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros Error:" + e.status);
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {

            }
        }
    });
}


function solonumeros(e) {
    var key = window.Event ? e.which : e.keyCode();
    return (key >= 48 && key <= 57);
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
        $("#mensajes").html("Ingrese Solo Letras.");




        return false;
    }


}
function validateEmail(email) {
  var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  return re.test(email);
}
function validate() {
  $("#result").text("");
  var email = $("#email").val();
  if (validateEmail(email)) {
    $("#result").text(email + " is valid :)");
    $("#result").css("color", "green");
  } else {
    $("#result").text(email + " is not valid :(");
    $("#result").css("color", "red");
  }
  return false;
}

$("#validate").bind("click", validate);

function validarFormulario() {
    var valor = true;
    var nombre = $("#nombre_proveedor").val();
    var ruc = $("#ruc_proveedor").val();
    var ciudad = $("#id_ciudad").val();
    var telefono= $("#telefono1_proveedor").val();
    if (nombre === "") {
        valor = false;
        $("#nombre_proveedor").val("");
        $("#nombre_proveedor").focus();
        $("#mensajes").html("Nombre del Proveedor no puede estar vacio.");
    } else {
        if (ruc === "") {
            $("#ruc_proveedor").val("");
            $("#ruc_proveedor").focus();
            $("#mensajes").html("Apellido del Cliente no puede estar vacio.");
        } else {
            if (telefono === "") {
                $("#telefono1_proveedor").val("");
                $("#telefono1_proveedor").focus();
                $("#mensajes").html("Cedula del Cliente no puede estar vacio.");
            } else {
                if (ciudad === "0") {
                    $("#id_ciudad").val("");
                    $("#id_ciudad").focus();
                    $("#mensajes").html("Ciudad no puede estar vacio.");
                }

            }
        }

        return valor;
    }

}