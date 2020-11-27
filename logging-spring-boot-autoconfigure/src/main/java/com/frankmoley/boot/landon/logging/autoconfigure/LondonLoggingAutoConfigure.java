package com.frankmoley.boot.landon.logging.autoconfigure;

import com.frankmoley.landon.aop.LoggableAspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(LoggableAspect.class)
@EnableConfigurationProperties(LondenLoggingProperties.class)
public class LondonLoggingAutoConfigure {

    private final LondenLoggingProperties logging;
    @Autowired
    public LondonLoggingAutoConfigure(LondenLoggingProperties logging) {
        this.logging = logging;
    }

    @Bean
    public LoggableAspect loggableAspect() {
        return new LoggableAspect(logging.getLoggerName());
    }
}
