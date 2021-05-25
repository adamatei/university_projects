package fontys.sem3.it.ticketstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableCaching(proxyTargetClass = true)
@EnableAsync(proxyTargetClass = true)
public class TicketstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketstoreApplication.class, args);
    }

}
