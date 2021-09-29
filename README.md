### 1. Apollo对于属性动态刷新的支持：
|方式|支持|
|---|---|
|`@Value("${xxx}")`|支持|
|`@ConfigurationProperties(prefix = "xxx")`|不支持|

### 2. 解决`@ConfigurationProperties(prefix = "xxx")`的动态刷新

### 3. Apollo官方给出的解决方案：
1. 基于`@RefreshScope`
2. 基于`EnvironmentChangeEvent`

### 4. Apollo官方的解决方案不足之处：
1. 需要编写一定的代码
2. 需要额外的指出`namespaces`

### 5. 终极解决之道
1. 引入依赖：
```xml
<parent>
    <groupId>cn.yzw</groupId>
    <artifactId>apollo-auto-refresh-spring-boot-starter</artifactId>
    <version>最新版本</version>
</parent>
```
2. 在`main`方法所在的类头顶上加上`@EnableApolloAutoRefresh`注解即可
4. 关于namespaces的配置：
   1. `@EnableApolloConfig`的`value`属性可以配置
   2. `@EnableApolloAutoRefresh`的`value`属性也可以配置
   3. 监控的namespace取第一第二点的`value`的并集
