package cn.yzw.apollo.auto.refresh.sample.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.scope.refresh.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * 描述：// TODO
 *
 * @author w.dehi.2021-09-29
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(DemoProperties.class)
public class DemoConfig {

    private DemoProperties demoProperties;
    private RefreshScope refreshScope;
    private ApplicationContext applicationContext;
    private Environment environment;

    public DemoConfig(DemoProperties demoProperties) {
        this.demoProperties = demoProperties;
    }

    @Bean
    public DemoBean demoBean() {
        return new DemoBean(demoProperties);
    }

}
