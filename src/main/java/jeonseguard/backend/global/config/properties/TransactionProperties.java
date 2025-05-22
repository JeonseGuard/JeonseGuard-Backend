package jeonseguard.backend.global.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.Delimiter;

import java.util.List;

@ConfigurationProperties(prefix = "transaction")
public record TransactionProperties(
        @Delimiter(",")
        List<String> contractYearMonths
) {
}
