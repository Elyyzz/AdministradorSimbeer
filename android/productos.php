<?php
/**
 * Obtiene todos los productos de la base de datos
 */

require 'producto.php';

if ($_SERVER['REQUEST_METHOD'] == 'GET') {

    // Manejar petición GET
    $producto = producto::getAll(); //no se  que  show como funciona esto 

    //if ($producto) {

        $datos["estado"] = 1;
        $datos["productos"] = $producto;

        print json_encode($datos);
    //} else {
       // print json_encode(array(
        //    "estado" => 2,
        //    "mensaje" => "Ha ocurrido un error"
       // ));
    
}