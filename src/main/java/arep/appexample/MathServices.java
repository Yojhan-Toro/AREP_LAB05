package arep.appexample;

import arep.HttpServer;
import java.io.IOException;
import java.net.URISyntaxException;

import static arep.HttpServer.get;
import static arep.HttpServer.staticfiles;

public class MathServices {

    public static void main(String[] args) throws IOException, URISyntaxException {

        staticfiles("/webroot/public");

        get("/App/hello", (req, res) -> "Hello " + req.getValues("name") + "!");
        get("/App/pi",    (req, res) -> "PI = " + Math.PI);
        get("/App/e",     (req, res) -> getEuler());
        get("/App/sqrt",  (req, res) -> {
            String numStr = req.getValues("value");
            try {
                double num = Double.parseDouble(numStr);
                return "sqrt(" + num + ") = " + Math.sqrt(num);
            } catch (NumberFormatException ex) {
                return "Por favor, pasa un número con ?value=<numero>";
            }
        });

        HttpServer.main(args);
    }

    private static String getEuler() {
        return "e = " + Math.E;
    }
}