package com.edu.eci.Calculadora;


import org.json.JSONObject;
import spark.Request;
import com.edu.eci.*;
import spark.Response;

import static spark.Spark.*;

public class CalculadoraMain {

    public static Funciones funciones = new Funciones();

    public static void main(String[] args){

        System.out.println("Hello World");
        port(getPort());
        get("/hello", (req, res) -> "Hello Heroku");
        get("/acos", (req, res) -> getRespuesta(req, res, "acos"));
        get("/log", (req,res) -> getRespuesta(req, res, "log"));
    }


    /**
     * Funcion que retorna un objeto JSON con el resultado de la operacion solicitada
     * @param rec
     * @param res
     * @param operacion
     * @return JsonObject ( "operation": "operacion", "input":  rec, "output":  resultado)
     */

    public static JSONObject getRespuesta(Request rec, Response res, String operacion){
        JSONObject respuesta = new JSONObject();
        Double valorACalcular = Double.valueOf(rec.queryParams("value"));
        Double resultado;
        if(operacion.equals("log")){
            resultado = funciones.logaritmo(valorACalcular);
        } else if(operacion.equals("acos")){
            resultado = funciones.arcoseno(valorACalcular);
        } else {
            resultado = null;
        }
        respuesta.put("operation", operacion);
        respuesta.put("input", valorACalcular);
        respuesta.put("output", resultado);
        return respuesta;
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 8001;
    }
}
