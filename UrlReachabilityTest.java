import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UrlReachabilityTest {

    @Test
    public void testUrlIsReachable() throws IOException, InterruptedException {
        // Öncelikle sistem property veya çevre değişkeninden URL al
        String url = System.getProperty("test.url");
        if (url == null || url.isEmpty()) {
            url = System.getenv("TEST_URL");
        }
        if (url == null || url.isEmpty()) {
            url = "https://example.com";
        }

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();

        HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());

        int status = response.statusCode();
        assertTrue(status >= 200 && status < 300, "Expected 2xx response from " + url + " but got: " + status);
    }
}
