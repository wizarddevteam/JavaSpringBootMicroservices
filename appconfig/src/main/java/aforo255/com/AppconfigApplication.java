package aforo255.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class AppconfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppconfigApplication.class, args);
    }

}
