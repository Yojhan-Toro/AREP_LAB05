package arep;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HttpServerTest {

    @BeforeEach
    void limpiarEndpoints() {
        HttpServer.endPoints.clear();
    }

    @Test
    void testRegistrarEndpointGet() {
        HttpServer.get("/test", (req, res) -> "ok");
        assertTrue(HttpServer.endPoints.containsKey("/test"));
    }

    @Test
    void testEndpointEjecuta() {
        HttpServer.get("/pi", (req, res) -> "PI=" + Math.PI);
        WebMethod wm = HttpServer.endPoints.get("/pi");
        assertNotNull(wm);
        String result = wm.execute(new HttpRequest("/pi"), new HttpResponse());
        assertEquals("PI=" + Math.PI, result);
    }

    @Test
    void testEndpointConQueryParam() {
        HttpServer.get("/hello", (req, res) -> "Hello " + req.getValues("name"));
        WebMethod wm = HttpServer.endPoints.get("/hello");
        String result = wm.execute(new HttpRequest("/hello?name=Pedro"), new HttpResponse());
        assertEquals("Hello Pedro", result);
    }

    @Test
    void testStaticfilesGuardaRuta() {
        HttpServer.staticfiles("/webroot/public");
        assertEquals("webroot/public", HttpServer.staticFilesFolder);
    }

    @Test
    void testStaticfilesQuitaSlashInicial() {
        HttpServer.staticfiles("/webroot/public");
        assertFalse(HttpServer.staticFilesFolder.startsWith("/"));
    }

    @Test
    void testEndpointInexistenteEsNull() {
        assertNull(HttpServer.endPoints.get("/no-existe"));
    }
}
