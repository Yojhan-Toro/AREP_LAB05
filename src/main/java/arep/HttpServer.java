package arep;
import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class HttpServer {

    static Map<String, WebMethod>  endPoints = new HashMap();


    public static void main(String[] args) throws IOException, URISyntaxException {

        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        Socket clientSocket = null;

        boolean running = true;
        while (running) {

            try {
                System.out.println("Listo para recibir...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream())
            );

            String inputLine, outputLine;

            boolean isFirstLine = true;

            String repath = null;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);

                if (isFirstLine) {
                    String[] flTokens = inputLine.split(" ");

                    String method = flTokens[0];
                    String struripath = flTokens[1];
                    String protocolversion = flTokens[2];

                    URI uripath = new URI(struripath);
                    repath = uripath.getPath();

                    System.out.println("Path: " + repath);

                    isFirstLine = false;
                }

                if (!in.ready()) {
                    break;
                }
            }

            WebMethod currentWm = endPoints.get(repath);

            if (currentWm != null){

                outputLine = "HTTP/1.1 200 OK\n\r" +
                        "Content-Type: text/html\n\r" +
                        "\n\r" +
                        "<!DOCTYPE html>"
                        + "<html>"
                        + "<head>"
                        + "<meta charset=\"UTF-8\">"
                        + "<title>Title of the document</title>"
                        + "</head>"
                        + "<body>"
                        + currentWm.execute()
                        + "</body>"
                        + "</html>";

            }else {

                outputLine = "HTTP/1.1 200 OK\n\r" +
                        "Content-Type: text/html\n\r" +
                        "\n\r" +
                        "<!DOCTYPE html>"
                        + "<html>"
                        + "<head>"
                        + "<meta charset=\"UTF-8\">"
                        + "<title>Title of the document</title>"
                        + "</head>"
                        + "<body>"
                        + "My Web Site"
                        + "</body>"
                        + "</html>";
            }

            out.println(outputLine);

            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    public static void get(String path, WebMethod wm){
        endPoints.put(path, wm);
    }


}