package kz.iitu.springlab.web;

import kz.iitu.springlab.config.AppProperties;
import kz.iitu.springlab.config.EnvironmentBanner;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;

@RestController
public class Lab3ConfigController {

    private final AppProperties appProperties;
    private final Environment environment;
    private final EnvironmentBanner banner;

    public Lab3ConfigController(
            AppProperties appProperties,
            Environment environment,
            EnvironmentBanner banner) {
        this.appProperties = appProperties;
        this.environment = environment;
        this.banner = banner;
    }

    @GetMapping("/api/lab3/config")
    public Map<String, Object> config() {
        return Map.of(
                "owner", appProperties.owner(),
                "group", appProperties.group(),
                "mailEnabled", appProperties.mail().enabled(),
                "retryCount", appProperties.mail().retryCount(),
                "timeout", appProperties.mail().timeout().toString(),
                "serverPort", environment.getProperty("server.port"),
                "activeProfiles", Arrays.toString(environment.getActiveProfiles()),
                "banner", banner.message()
        );
    }
}