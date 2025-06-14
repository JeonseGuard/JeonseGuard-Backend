package jeonseguard.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
		org.springframework.boot.actuate.autoconfigure.metrics.SystemMetricsAutoConfiguration.class,
		org.springframework.boot.actuate.autoconfigure.metrics.web.tomcat.TomcatMetricsAutoConfiguration.class
})
public class JeonseGuardBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(JeonseGuardBackendApplication.class, args);
	}
}
