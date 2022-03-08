package aforo255.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MsAwsHistoricalApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsAwsHistoricalApplication.class, args);
    }

}
