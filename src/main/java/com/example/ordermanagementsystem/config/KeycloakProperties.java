package com.example.ordermanagementsystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import lombok.Data;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "keycloak")
@Data
public class KeycloakProperties {

    private String realm = "order-management";
    private String resource = "order-management-client";
    private String authServerUrl = "http://localhost:8180/auth";
    private String clientSecret = "your-client-secret";
    private String sslRequired = "external";
    private int confidentialPort = 0;

    /**
     * Config for Docker environment
     */
    @Configuration
    @Profile("docker")
    @ConfigurationProperties(prefix = "keycloak")
    @Data
    public static class DockerKeycloakProperties extends KeycloakProperties {

        public DockerKeycloakProperties() {
            setAuthServerUrl("http://keycloak:8180/auth");
        }
    }
}
