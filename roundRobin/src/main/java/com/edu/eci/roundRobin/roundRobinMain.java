package com.edu.eci.roundRobin;


import static spark.Spark.*;

public class roundRobinMain {
    public static void main(String[] args){
        System.out.println("Hello World");
        port(getPort());

        HTTPClient client=new HTTPClient();
        get("/", (req, res) -> "Hello World from roundRobin!!!");

        get("/acos",(req, res) -> {
            res.status(200);
            res.type("application/json");
            System.out.println(req);
            System.out.println(req.queryParams("value"));
            String value = req.queryParams("value");
            String response=client.getMessages("/acos?value="+value);
            //client.changePort();
            return response;
        });

        get("/log",(req, res) -> {
            res.status(200);
            res.type("application/json");
            String value = req.queryParams("value");
            String response=client.getMessages("/log?value="+value);
            //client.changePort();
            return response;
        });

    }
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
