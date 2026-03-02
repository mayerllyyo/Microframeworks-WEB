package edu.eci.arep;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the WebFramework class.
 */
class WebFrameworkTest {

    @BeforeEach
    void setUp() {
        WebFramework.reset();
    }

    @Test
    void testGetRegistersRoute() {
        WebFramework.get("/hello", (req, res) -> "hello world");
        assertTrue(WebFramework.getRoutes().containsKey("/hello"));
    }

    @Test
    void testRouteHandlerIsInvoked() {
        WebFramework.get("/hello", (req, res) -> "hello " + req.getValues("name"));
        Route handler = WebFramework.getRoutes().get("/hello");
        assertNotNull(handler);

        HttpRequest req = new HttpRequest("/hello?name=Pedro");
        HttpResponse res = new HttpResponse();
        assertEquals("hello Pedro", handler.handle(req, res));
    }

    @Test
    void testPiRoute() {
        WebFramework.get("/pi", (req, res) -> String.valueOf(Math.PI));
        Route handler = WebFramework.getRoutes().get("/pi");
        assertNotNull(handler);

        HttpRequest req = new HttpRequest("/pi");
        HttpResponse res = new HttpResponse();
        assertEquals(String.valueOf(Math.PI), handler.handle(req, res));
    }

    @Test
    void testStaticfilesDefaultLocation() {
        assertEquals("webroot", WebFramework.getStaticFilesLocation());
    }

    @Test
    void testStaticfilesWithLeadingSlash() {
        WebFramework.staticfiles("/webroot");
        assertEquals("webroot", WebFramework.getStaticFilesLocation());
    }

    @Test
    void testStaticfilesWithoutLeadingSlash() {
        WebFramework.staticfiles("webroot/public");
        assertEquals("webroot/public", WebFramework.getStaticFilesLocation());
    }

    @Test
    void testMultipleRoutes() {
        WebFramework.get("/a", (req, res) -> "a");
        WebFramework.get("/b", (req, res) -> "b");
        assertEquals(2, WebFramework.getRoutes().size());
    }

    @Test
    void testResetClearsState() {
        WebFramework.get("/hello", (req, res) -> "hello");
        WebFramework.staticfiles("/custom");
        WebFramework.reset();
        assertTrue(WebFramework.getRoutes().isEmpty());
        assertEquals("webroot", WebFramework.getStaticFilesLocation());
    }
}
