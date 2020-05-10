package cn.darkjrong.plugin.generator;

import cn.darkjrong.plugin.toolkit.Sequence;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import cn.darkjrong.plugin.annotation.IdType;

import java.lang.reflect.Field;

/**
 * String类型主键赋值
 * @author Rong.Jia
 * @date 2020/03/28 13:20
 */
public class StringUUIDKeyGenerator extends AbstractKeyGenerator {

    @Override
    public void process(Field field, Object paramObj) throws Exception {
        if (null == field) {
            defaultGeneratorKey(paramObj);
            return;
        }
        if (!field.getType().isAssignableFrom(String.class)) {
            throw new IllegalArgumentException("主键策略UUID==》对应主键属性类型必须为String");
        }
        if (super.checkField(field, paramObj)) {
            field.set(paramObj, Sequence.uniqueUUID());
        }
    }

    @Override
    public IdType getType() {
        return IdType.ASSIGN_UUID;
    }

    @Override
    protected void defaultGeneratorKey(Object paramObj) {
        MetaObject metaObject = SystemMetaObject.forObject(paramObj);
        if (metaObject.getValue(DEFAULT_ID) == null) {
            if (!metaObject.getSetterType(DEFAULT_ID).isAssignableFrom(String.class)) {
                throw new IllegalArgumentException("主键策略UUID==》对应主键属性类型必须为String");
            }
            metaObject.setValue(DEFAULT_ID, Sequence.uniqueUUID());
        }
    }


}
