package com.lxd.activiti.config;

import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author lxd
 * @date 2021-01-26 13:48
 */
@Component
public class ShareniuProcessEngineConfigurationConfigurer implements ProcessEngineConfigurationConfigurer {

    @Override
    public void configure(SpringProcessEngineConfiguration processEngineConfiguration) {
        processEngineConfiguration.setActivityFontName("宋体");
        processEngineConfiguration.setLabelFontName("宋体");
        processEngineConfiguration.setAnnotationFontName("宋体");
        System.out.println("ShareniuProcessEngineConfigurationConfigurer#############");
        System.out.println(processEngineConfiguration.getActivityFontName());
    }

}
