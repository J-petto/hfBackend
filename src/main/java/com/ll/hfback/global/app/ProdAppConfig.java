package com.ll.hfback.global.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("site")
public class ProdAppConfig {
    @Getter
    private static ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        ProdAppConfig.objectMapper = objectMapper;
    }

    @Getter
    private static String siteFrontUrl;

    @Getter
    private static String siteBackUrl;

    @Value("${custom.site.frontUrl}")
    public void setSiteFrontUrl(String siteFrontUrl) {
        ProdAppConfig.siteFrontUrl = siteFrontUrl;
    }

    @Value("${custom.site.backUrl}")
    public void setSiteBackUrl(String siteBackUrl) {
        ProdAppConfig.siteBackUrl = siteBackUrl;
    }

    public static boolean isNotProd() {
        return true;
    }

}
