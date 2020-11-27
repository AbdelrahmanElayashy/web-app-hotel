package com.frankmoley.boot.landon.logging.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("londen.logging")
public class LondenLoggingProperties {
    private String loggerName = "LandonAuditLogger";

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }
}
