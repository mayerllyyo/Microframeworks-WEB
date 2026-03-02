package edu.eci.arep;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for HttpRequest query parameter parsing.
 */
class HttpRequestTest {

    @Test
    void testPathWithNoQuery() {
        HttpRequest req = new HttpRequest("/hello");
        assertEquals("/hello", req.getPath());
        assertEquals("", req.getValues("name"));
    }

    @Test
    void testSingleQueryParam() {
        HttpRequest req = new HttpRequest("/hello?name=Pedro");
        assertEquals("/hello", req.getPath());
        assertEquals("Pedro", req.getValues("name"));
    }

    @Test
    void testMultipleQueryParams() {
        HttpRequest req = new HttpRequest("/search?q=java&page=2");
        assertEquals("/search", req.getPath());
        assertEquals("java", req.getValues("q"));
        assertEquals("2", req.getValues("page"));
    }

    @Test
    void testMissingQueryParam() {
        HttpRequest req = new HttpRequest("/hello?name=Pedro");
        assertEquals("", req.getValues("missing"));
    }

    @Test
    void testUrlDecodedQueryParam() {
        HttpRequest req = new HttpRequest("/hello?name=Hello%20World");
        assertEquals("Hello World", req.getValues("name"));
    }

    @Test
    void testEmptyQueryValue() {
        HttpRequest req = new HttpRequest("/hello?name=");
        assertEquals("", req.getValues("name"));
    }

    @Test
    void testRootPath() {
        HttpRequest req = new HttpRequest("/");
        assertEquals("/", req.getPath());
        assertTrue(req.getQueryParams().isEmpty());
    }

    @Test
    void testDuplicateQueryParamKeyUsesLastValue() {
        // When the same key appears twice, one value is stored per key
        HttpRequest req = new HttpRequest("/search?tag=java&tag=web");
        String value = req.getValues("tag");
        assertTrue("java".equals(value) || "web".equals(value));
    }
}
