package resources;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.HitCounter;

@RestController
@EnableAutoConfiguration
public class HelloResource {

    public static final String RESPONSE_FORMAT = "<h1>Hello World</h1><br>This page has been hit %d times FOO";

    @RequestMapping("/hello")
    public String handleGreeting() {
        return String.format(RESPONSE_FORMAT, HitCounter.getInstance().incr());
    }


    public static void main(String[] args) throws Exception {
        SpringApplication.run(HelloResource.class, args);
    }
}
