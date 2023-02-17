package com.github.dreamroute.apollo.auto.refresh.spring.boot.starter;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.cloud.context.scope.refresh.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

/**
 * 描述：自动刷新配置逻辑
 *
 * @author w.dehi.2021-09-29
 */
@Slf4j
public class ApolloAutoRefreshConfig {

    @Resource
    private RefreshScope refreshScope;

    public ApolloAutoRefreshConfig(ApplicationContext applicationContext, Environment environment) {
        Map<String, Object> beansWithAutoRefresh = applicationContext.getBeansWithAnnotation(EnableApolloAutoRefresh.class);
        Map<String, Object> beansWithConfig = applicationContext.getBeansWithAnnotation(EnableApolloConfig.class);

        Set<String> namespaces = newHashSet();
        parseNamespaces(beansWithAutoRefresh, EnableApolloAutoRefresh.class, namespaces);
        parseNamespaces(beansWithConfig, EnableApolloConfig.class, namespaces);

        ConfigChangeListener listener = changeEvent -> {
            applicationContext.publishEvent(new EnvironmentChangeEvent(changeEvent.changedKeys()));
            refreshScope.refreshAll();
        };

        if (!namespaces.isEmpty()) {
            namespaces.forEach(ns -> {
                String resolvedNamespace = environment.resolveRequiredPlaceholders(ns);
                Config config = ConfigService.getConfig(resolvedNamespace);
                config.addChangeListener(listener);
            });
        }

    }

    private void parseNamespaces(Map<String, Object> config, Class<? extends Annotation> type, Set<String> namespaces) {
        if (!config.isEmpty()) {
            config.values().forEach(obj -> {
                Annotation anno = AnnotationUtils.findAnnotation(obj.getClass(), type);
                String[] ns = (String[]) AnnotationUtils.getValue(anno);
                if (ns != null && ns.length > 0) {
                    for (String n : ns) {
                        namespaces.add(n.trim());
                    }
                }
            });
        }
    }

}
