package cz.muni.fi.pa165.rest;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.List;

import static org.testng.AssertJUnit.assertTrue;


public class AllowOriginInterceptorTests {
    private MockHttpServletRequest request = new MockHttpServletRequest("GET", "/");
    private MockHttpServletResponse response = new MockHttpServletResponse();

    @Test
    public void preHandle_withGetRequest_addsAllowOriginHeaders() {
        AllowOriginInterceptor interceptor = new AllowOriginInterceptor();

        interceptor.preHandle(request, response, null);

        List<String> allowOriginHeaders = response.getHeaders("Access-Control-Allow-Origin");
        assertTrue(allowOriginHeaders.contains("*"));
        List<String> allowedMethodsHeader = response.getHeaders("Access-Control-Allow-Methods");
        assertTrue(allowedMethodsHeader.contains("GET, POST, PUT, DELETE, OPTIONS"));
    }
}
