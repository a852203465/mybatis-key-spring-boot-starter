# mybatis-key-spring-boot-starter
mybatis主键生成策略，支持分布式。支持UUID（UUID-String主键）、ASSIGN_ID(默认雪花算法)。支持注解@TableId指定主键，注解优先级大于全局配置。

#使用方式
自己下载install引入使用

```
<dependency>
    <groupId>cn.darkjrong</groupId>
    <artifactId>mybatis-key-spring-boot-starter</artifactId>
    <version>1.0</version>
</dependency>
```

yml配置，必须配置enabled: true，否则默认false不起作用
```yml
keyplugin:
  enabled: true #开启插件
  keyType: 2 #主键策略
```

实体示例
```java
public class User {

    @TableId(IdType.ASSIGN_ID)
    private String id;

    private String userName;

    private String password;
}

```
