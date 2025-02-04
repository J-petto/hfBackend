package com.ll.hfback.global.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevAppConfig {
    @Getter
    private static ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        DevAppConfig.objectMapper = objectMapper;
    }

    @Getter
    private static String siteFrontUrl;

    @Getter
    private static String siteBackUrl;

    @Value("${custom.dev.frontUrl}")
    public void setSiteFrontUrl(String siteFrontUrl) {
        DevAppConfig.siteFrontUrl = siteFrontUrl;
    }

    @Value("${custom.dev.backUrl}")
    public void setSiteBackUrl(String siteBackUrl) {
        DevAppConfig.siteBackUrl = siteBackUrl;
    }

    public static boolean isNotProd() {
        return true;
    }

}
