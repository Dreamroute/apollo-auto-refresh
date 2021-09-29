package cn.yzw.apollo.auto.refresh.sample.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 描述：// TODO
 *
 * @author w.dehi.2021-09-29
 */
@Data
@ConfigurationProperties(prefix = "demo")
public class DemoProperties {
    private String name;
}
