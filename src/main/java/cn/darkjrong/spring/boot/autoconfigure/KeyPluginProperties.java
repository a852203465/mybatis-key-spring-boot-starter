package cn.darkjrong.spring.boot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *  主键策略 配置文件
 * @author Rong.Jia
 * @date 2020/03/28 13:20
 */
@ConfigurationProperties(KeyPluginProperties.PREFIX)
public class KeyPluginProperties {

    protected static final String PREFIX = "key-plugin";
    private static final String KEY_TYPE = "2";
    private static final String ENABLED = "false";

    /**
     * 主键策略,
     * 0(AUTO),
     * 1(INPUT),
     * 2(ASSIGN_ID),
     * 3(ASSIGN_UUID),
     */
    private String keyType = KEY_TYPE;

    /**
     * 是否开启主键策略插件
     */
    private String enabled = ENABLED;

    public String getKeyType() {
        return keyType;
    }

    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }
}