package me.bcoffield.valpower;

import io.micronaut.context.annotation.Value;
import jakarta.inject.Singleton;
import lombok.Getter;
import lombok.Setter;

@Singleton
@Getter
@Setter
public class ApplicationProperties {
    @Value("${AWS_INSTANCE_ID}")
    private String instanceId;

    @Value("${AWS_REGION}")
    private String region;
}
