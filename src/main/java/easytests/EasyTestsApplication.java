package easytests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author malinink
 */
@SpringBootApplication
public final class EasyTestsApplication {
    private EasyTestsApplication() {

    }

    public static void main(String[] args) {
        SpringApplication.run(EasyTestsApplication.class, args);
    }
}
