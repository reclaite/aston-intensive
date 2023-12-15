package com.reclaite.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.reclaite.repository")
@EntityScan(basePackages = "com.reclaite.model")
public class PCConfiguration {
}
