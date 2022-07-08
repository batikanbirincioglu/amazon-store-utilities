package com.bbirincioglu.amazon.store.utilities;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties
@Configuration
@Data
public class ApplicationProperties {
    private ChromeDriverConfig chromeDriverConfig;

    @Data
    public static class ChromeDriverConfig {
        private String pathKey;
        private String pathValue;
    }
}
