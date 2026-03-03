package arep.appexample;

import arep.HttpServer;
import arep.WebMethod;

import java.io.IOException;
import java.net.URISyntaxException;

import  static arep.HttpServer.get;

public class MathServices {

    public static void main(String[] args) throws IOException, URISyntaxException {
        get("/pi", (req, res) -> "PI= " + Math.PI);
        get("/hello", (req, res) -> "Hello word");
        get("/frommethod", (req, res) -> getEuler());
        HttpServer.main(args);
    }

    private  static String getEuler(){
        return "e= " + Math.E;
    }

}
