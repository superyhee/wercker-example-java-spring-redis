package resources;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(HelloResource.class)
@WebIntegrationTest
public class HelloResourceTest {

    RestTemplate template = new TestRestTemplate();

    @Test
    public void testRequest() throws Exception {
        String response = template.getForEntity("http://localhost:8080/hello", String.class).getBody();
        Assert.assertEquals(response, String.format(HelloResource.RESPONSE_FORMAT, 1));
    }

}