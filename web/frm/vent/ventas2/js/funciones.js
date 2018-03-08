function buscarIdVenta() {
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
            $("#id_venta").val(json.id_venta);
            $("#fecha_venta").val(json.fecha_venta);
            $("#estado_venta").val(json.estado_venta);
            $("#numero_factura").val(json.numero_factura);
            $("#id_cliente").val(json.id_cliente);
            $("#nombre_cliente").val(json.nombre_cliente);
            $("#ruc_cliente").val(json.ruc_cliente);
            $("#id_timbrado").val(json.id_timbrado);
            $("#contenidoDetalle").html(json.contenido_detalle);
//            buscarIdTimbrado();
//            buscarIdCliente();
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                $("#botonCobro").prop('disabled', true);
                siguienteCampo("#id_tipoventa", "#botonAgregar", true);
                $("#detalle").prop('hidden', true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
                $("#botonCobro").prop('disabled', false);
                $("# botonBuscarIdCliente").prop('disabled', false);
                $("# id_cliente").prop('disabled', false);
                siguienteCampo("#id_tipoventa", "#botonModificar", true);
                $("#detalle").prop('hidden', false);
            }

            if (json.estado_venta === "ANULADO") {
                $("#botonAgregar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                $("#agregar").prop('disabled', true);
                $(".botonpen").prop('disabled', true);
                $("#botonCobro").prop('disabled', true);
                $("# botonBuscarIdCliente").prop('disabled', true);
                $("# id_cliente").prop('disabled', true);

            }
            if (json.estado_venta === "FACTURADO") {
                $("#botonAgregar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                $("#agregar").prop('disabled', true);
                $(".botonpen").prop('disabled', true);
                $("#botonCobro").prop('disabled', true);
                $("# botonBuscarIdCliente").prop('disabled', true);
                $("# id_cliente").prop('disabled', true);

            }
             if (json.estado_venta === "ACTIVO") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', true);
                $("#agregar").prop('disabled', false);
                $(".botonpen").prop('disabled', false);
                $("#botonCobro").prop('disabled', false);
                $("# botonBuscarIdCliente").prop('disabled', false);
                $("# id_cliente").prop('disabled', false);

            }
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
function buscarNombreVenta() {
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
                $("#id_venta").val(id);
                $("#nombre_cliente").focus();
                buscarIdVenta();
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
function agregarVenta() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/agregar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {

            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            limpiarFormulario();
            $("#mensajes").html(json.mensaje);

            $("#botonAgregar").prop('disabled', true);
            $("#detalle").prop('hidden', false);
            $("#id_venta").val(json.id_venta);
            buscarIdVenta();
            $("#id_venta").focus;
            $("#id_venta").select();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function modificarVenta() {
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
            $("#id_venta").focus;
            $("#id_venta").select();
            buscarIdVenta();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function eliminarVenta() {
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
            eliminarVentaDetalle();
            limpiarFormulario();
            $("#mensajes").html(json.mensaje);
            $("#id_venta").focus;
            $("#id_venta").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

function buscarIdCliente() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdCliente.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_cliente").val(json.id_cliente);
            $("#nombre_cliente").val(json.nombre_cliente);
            $("#ruc_cliente").val(json.ruc_cliente);

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
function buscarNombreCliente() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreCliente.jsp',
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
                $("#id_cliente").val(id);
                $("#nombre_cliente").focus();
                buscarIdCliente();
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


function validarFormulario() {
    var valor = true;
    if ($("#nombre_venta").val().length < 3) {
        valor = false;
        $("#mensajes").html("Nombre Venta no puede estar vacio.");
        $("#nombre_venta").focus();
    }

    if ($("#nombre_cliente").val().length < 2) {
        valor = false;
        $("#mensajes").html("Cliente no puede estar vacio.");
        $("#id_cliente").focus();
    }



    if ($("#id_pago").val().length < 2) {
        valor = false;
        $("#mensajes").html("Pagos no puede estar Vacio");
        $("#id_pago").focus();
    }
    return valor;
}
function limpiarFormulario() {
    $("#id_venta").val("0");
    $("#nombre_venta").val("");
    $("#nombre_tipoventa").val("");
    $("#nombre_cliente").val("");
    $("#id_cliente").val("0");
    $("#id_tipocventa").val("0");
    $("#estado_venta").val("");


}
function agregarLinea() {
    $("#id_detalleventa").val("0");
    $("#id_producto").val("0");
    $("#nombre_producto").val("");
    $("#costo_venta").val("0");
    $("#iva_producto").val("");
    $("#cantidad_productoventa").val("0");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_producto").focus();
    $("#id_producto").select();
    $("#botonAgregarLinea").prop('disabled', false);
    $("#botonModificarLinea").prop('disabled', true);
    $("#botonEliminarLinea").prop('disabled', true);
    siguienteCampo("#horas_detalleventa", "#botonAgregarLinea", true);
}
function editarLinea(id) {
    $("#id_detalleventa").val(id);
    $("#id_producto").val("0");
    $("#nombre_producto").val("");
    $("#costo_venta").val("0");
    $("#iva_producto").val("");
    $("#cantidad_productoventa").val("0");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_producto").focus();
    $("#id_producto").select();
    $("#botonAgregarLinea").prop('disabled', true);
    $("#botonModificarLinea").prop('disabled', false);
    $("#botonEliminarLinea").prop('disabled', false);
    buscarIdVentaDetalle();
    siguienteCampo("#cantidad_productoventa", "#botonModificarLinea", true);
}



// ventasproductos
function buscarIdVentaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdVentaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_producto").val(json.id_producto);
            $("#nombre_producto").val(json.nombre_producto);
            $("#costo_venta").val(json.costo_venta);
            $("#iva_producto").val(json.iva_producto);
            $("#cantidad_productoventa").val(json.cantidad_productoventa);
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
function agregarVentaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_venta = $("#id_venta").val();
    datosFormulario += "&id_venta=" + id_venta;
    $.ajax({
        type: 'POST',
        url: 'jsp/agregarVentaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdVenta();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function modificarVentaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_venta = $("#id_venta").val();
    datosFormulario += "&id_venta=" + id_venta;
    $.ajax({
        type: 'POST',
        url: 'jsp/modificarVentaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdVenta();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function eliminarVentaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_venta = $("#id_venta").val();
    datosFormulario += "&id_venta=" + id_venta;
    $.ajax({
        type: 'POST',
        url: 'jsp/anularVentaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json)
        {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdVenta();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
//// productos
function buscarIdProducto() {
    var datosFormulario = $("#formLinea").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdProducto.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_producto").val(json.id_producto);
            $("#nombre_producto").val(json.nombre_producto);
            $("#costo_venta").val(json.costo_venta);
            $("#iva_producto").val(json.iva_producto);


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
function buscarNombreProducto() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreProducto.jsp',
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
                $("#id_producto").val(id);
                $("#nombre_producto").focus();
                buscarIdProducto();
                $("#buscar").fadeOut("slow");
                $("#panelLinea").fadeIn("slow");
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
function buscarIdTimbrado() {
    var datosFormulario = $("#formPrograma").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdTimbrado.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_timbrado").val(json.id_timbrado);
            $("#numero_timbrado").val(json.numero_timbrado);
            $("#fecha_inicio").val(json.fecha_inicio);
            $("#fecha_vencimiento").val(json.fecha_vencimiento);
            $("#fecha_actual").val(json.fecha_actual);
            $("#desde_timbrado").val(json.desde_timbrado);
            $("#hasta_timbrado").val(json.hasta_timbrado);
            $("#id_establecimiento").val(json.id_establecimiento);
            $("#nombre_establecimiento").val(json.nombre_establecimiento);
            $("#id_puesto").val(json.id_puesto);
            $("#nombre_puesto").val(json.nombre_puesto);


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
function buscarNumero() {
    var datosFormulario = $("#formBuscar").serialize();
    // alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNumeroTimbrado.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando daatos al servidor...");
            $("#contenidoBusqueda").css("display", "none");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_timbrado").val(id);
                $("#numero_timbrado").focus();
                buscarIdTimbrado();
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

function agregarcobro(id) {
    $("#id_venta").val(id);
    $("#id_cobro").val("0");
    $("#id_pago").val("");
    $("#total_cobro").val("0");
    $("#panelCobro").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_pago").focus();
    $("#id_pago").select();
    $("#botonAgregarCobro").prop('disabled', false);

    buscarIdVenta();

    siguienteCampo("#botonAgregarCobro", true);
}


function buscarIdCobro() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdCobro.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_venta").val(json.id_venta);
            $("#total_cobro").val(json.total_cobro);
            $("#id_cobro").val(json.id_cobro);
            $("#panelCobro").fadeIn("slow");
            $("#panelPrograma").fadeOut("slow");

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


function agregarCobro() {
    var datosFormulario = $("#formCobro").serialize();
    var id_venta = $("#id_venta").val();
    datosFormulario += "&id_venta=" + id_venta;
    $.ajax({
        type: 'POST',
        url: 'jsp/agregarCobro.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {

            $("#mensajes").html("Enviando datos al Servidor ...");


        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelCobro").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdVenta();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });


}
function sumar() {
    var total = Number(document.getElementById('total_cobro').value);
    var monto = Number(document.getElementById('monto').value);
    var vuelto = monto - total;
    document.getElementById('vuelto').value = vuelto;

}
