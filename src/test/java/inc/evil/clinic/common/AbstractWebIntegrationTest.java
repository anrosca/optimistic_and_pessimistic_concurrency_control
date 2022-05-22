package inc.evil.clinic.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

public class AbstractWebIntegrationTest extends AbstractIntegrationTest {
    @LocalServerPort
    protected int port;

    @Autowired
    protected TestRestTemplate restTemplate;

    protected <T> RequestEntity<T> makeRequestFor(String urlTemplate, HttpMethod httpMethod) {
        return makeRequestFor(urlTemplate, httpMethod, null);
    }

    protected <T> RequestEntity<T> makeRequestFor(String urlTemplate, HttpMethod httpMethod, T payload) {
        return RequestEntity.method(httpMethod, "http://localhost:{port}" + urlTemplate, port)
                .contentType(MediaType.APPLICATION_JSON)
                .body(payload);
    }
}
