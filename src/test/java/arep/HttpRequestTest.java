package arep;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HttpRequestTest {

    @Test
    void testPathSinQueryParams() {
        HttpRequest req = new HttpRequest("/App/pi");
        assertEquals("/App/pi", req.getPath());
        assertEquals("", req.getQuery());
    }

    @Test
    void testGetValuesConUnParametro() {
        HttpRequest req = new HttpRequest("/App/hello?name=Pedro");
        assertEquals("Pedro", req.getValues("name"));
    }

    @Test
    void testGetValuesConVariosParametros() {
        HttpRequest req = new HttpRequest("/App/sqrt?value=16&precision=2");
        assertEquals("16", req.getValues("value"));
        assertEquals("2",  req.getValues("precision"));
    }

    @Test
    void testGetValuesParametroInexistente() {
        HttpRequest req = new HttpRequest("/App/hello?name=Pedro");
        assertEquals("", req.getValues("edad"));
    }

    @Test
    void testPathConQueryGuardaQueryString() {
        HttpRequest req = new HttpRequest("/App/hello?name=Maria");
        assertEquals("/App/hello", req.getPath());
        assertEquals("name=Maria", req.getQuery());
    }
}
