package de.home_skrobanek.manhunt;

import org.junit.Test;

import de.home_skrobanek.manhunt.backend.manager.http.HTTPRequest;

public class HTTPRequestTest {

    @Test
    public void testLogin(){
        HTTPRequest request = HTTPRequest.getInstance();

        request.login(null);
    }
}
