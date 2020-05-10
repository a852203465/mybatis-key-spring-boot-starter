package cn.darkjrong.spring.boot.autoconfigure;

import cn.darkjrong.plugin.generator.InputKeyGenerator;
import cn.darkjrong.plugin.generator.SnowflakeIdKeyGenerator;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import cn.darkjrong.plugin.generator.StringUUIDKeyGenerator;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import cn.darkjrong.plugin.incrementer.GenerateKeyInterceptor;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Properties;

/**
 * 自定义主键赋值插件
 * @author Rong.Jia
 * @date 2020/03/28 13:20
 */
@Configuration
@ConditionalOnBean(SqlSessionFactory.class)
@EnableConfigurationProperties(KeyPluginProperties.class)
@AutoConfigureAfter({MybatisAutoConfiguration.class})
@ConditionalOnProperty(prefix = "key-plugin", name = "enabled", havingValue = "true")
public class KeyPluginAutoConfiguration {
    private static final Log log = LogFactory.getLog(KeyPluginAutoConfiguration.class);

    private static final String ID_TYPE = "idType";

    @Autowired
    private List<SqlSessionFactory> sqlSessionFactoryList;

    @Autowired
    private KeyGeneratorFactory keyGeneratorFactory;

    private final KeyPluginProperties keyPluginProperties;

    public KeyPluginAutoConfiguration(KeyPluginProperties keyPluginProperties) {
        this.keyPluginProperties = keyPluginProperties;
    }

    @PostConstruct
    public void addPageInterceptor() {
        log.debug(keyPluginProperties.toString());
        GenerateKeyInterceptor generateKeyPlugin = new GenerateKeyInterceptor(keyGeneratorFactory);
        Properties properties = new Properties();
        // 全局主键（id）策略
        properties.put(ID_TYPE, keyPluginProperties.getKeyType());
        generateKeyPlugin.setProperties(properties);
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            sqlSessionFactory.getConfiguration().addInterceptor(generateKeyPlugin);
        }
    }

    @Bean
    public KeyGeneratorFactory keyGeneratorFactory() {
        return new KeyGeneratorFactory();
    }

    @Bean
    public SnowflakeIdKeyGenerator snowflakeIdKeyGenerator() {
        return new SnowflakeIdKeyGenerator();
    }

    @Bean
    public InputKeyGenerator inputKeyGenerator() {
        return new InputKeyGenerator();
    }

    @Bean
    public StringUUIDKeyGenerator stringUUIDKeyGenerator() {
        return new StringUUIDKeyGenerator();
    }
}