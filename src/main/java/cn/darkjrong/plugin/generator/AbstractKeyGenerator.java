package cn.darkjrong.plugin.generator;

import cn.darkjrong.plugin.annotation.IdType;

import java.lang.reflect.Field;

/**
 * 主键策略
 * @author Rong.Jia
 * @date 2020/03/28 13:20
 */
public abstract class AbstractKeyGenerator implements KeyGenerator {

    /**
     * 默认主键
     */
    protected static final String DEFAULT_ID = "id";

    @Override
    public abstract void process(Field field, Object paramObj) throws Exception;

    @Override
    public abstract IdType getType();

    /**
     * 默认id主键赋值
     * @param paramObj  参数对象
     */
    protected abstract void defaultGeneratorKey(Object paramObj);

    /**
     * Field值否判断
     *
     * @param field 字段
     * @param object 对象
     * @return 是否true/false
     * @throws IllegalAccessException 权限限定符异常
     */
    protected boolean checkField(Field field, Object object) throws IllegalAccessException {
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        return field.get(object) == null;
    }

}
