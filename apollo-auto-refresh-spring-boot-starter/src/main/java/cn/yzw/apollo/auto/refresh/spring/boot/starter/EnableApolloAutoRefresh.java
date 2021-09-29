package cn.yzw.apollo.auto.refresh.spring.boot.starter;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 描述：Apollo自动刷新启动类
 *
 * @author w.dehi.2021-09-29
 */
@Target(TYPE)
@Retention(RUNTIME)
@Import(ApolloAutoRefreshConfig.class)
public @interface EnableApolloAutoRefresh {

    /**
     * 需要自动刷新的namespace数组，例如{"dev", "test"}
     */
    String[] value() default {};
}
