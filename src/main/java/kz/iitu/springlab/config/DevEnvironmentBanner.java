package kz.iitu.springlab.config;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DevEnvironmentBanner implements EnvironmentBanner {

    @Override
    public String message() {
        return "Development environment";
    }
}
