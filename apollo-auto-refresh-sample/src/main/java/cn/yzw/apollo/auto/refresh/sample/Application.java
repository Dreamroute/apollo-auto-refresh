package cn.yzw.apollo.auto.refresh.sample;

import cn.yzw.apollo.auto.refresh.sample.config.DemoBean;
import cn.yzw.apollo.auto.refresh.spring.boot.starter.EnableApolloAutoRefresh;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * 描述：// TODO
 *
 * @author w.dehi.2021-09-29
 */
@SpringBootApplication
@EnableApolloAutoRefresh({"a"})
@EnableApolloConfig({"jc.base", "jc.wm-api"})
public class Application implements CommandLineRunner {

    @Resource
    private DemoBean demoBean;

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            System.err.println(demoBean.getDemoProperties().getName());
            SECONDS.sleep(1);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
