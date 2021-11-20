package com.coffeeshop.admin.config.properties;

import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Slf4j
@Data
@Validated
@ConfigurationProperties(prefix = "app")
@Configuration
public class AppProperties implements InitializingBean {

    @Getter
    private static AppProperties instance;

    @NotNull
    private String test;

    @Override
    public void afterPropertiesSet() {
        instance = this;
        log.info("configuration values: {}", instance);
    }
}
