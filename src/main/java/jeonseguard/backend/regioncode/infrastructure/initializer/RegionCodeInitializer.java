package jeonseguard.backend.regioncode.infrastructure.initializer;

import jeonseguard.backend.regioncode.domain.service.RegionCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
@RequiredArgsConstructor
public class RegionCodeInitializer implements ApplicationRunner {
    private final RegionCodeService regionCodeService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        InputStream inputStream = new ClassPathResource("lawd/법정동코드.csv").getInputStream();
        regionCodeService.saveNewRegionCodes(inputStream);
    }
}
