package com.lss.universal;

import com.lss.universal.config.UniversalConfig;
import com.lss.universal.controller.UniversalController;
import com.lss.universal.service.UniversalService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(UniversalConfig.class)
public class UniversalAutoConfiguration {

    @Bean(value = "UniversalController")
    @ConditionalOnProperty(prefix = "universal.config", value = "initialization",havingValue = "true")
    public UniversalController universalController(UniversalConfig universalConfig){
        UniversalService universalService=new UniversalService();
        universalService.setUniversalConfig(universalConfig);
        UniversalController universalController=new UniversalController();
        universalController.setUniversalService(universalService);
        return universalController;
    }
}
