package cn.darkjrong.plugin.generator;

import cn.darkjrong.plugin.toolkit.Sequence;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import cn.darkjrong.plugin.annotation.IdType;

import java.lang.reflect.Field;

/**
 * 雪花算法主键策略
 * @author Rong.Jia
 * @date 2020/03/28 10:46
 */
public class SnowflakeIdKeyGenerator extends AbstractKeyGenerator {

    @Override
    public void process(Field field, Object paramObj) throws Exception {
        if (field == null) {
            defaultGeneratorKey(paramObj);
            return;
        }
        if (super.checkField(field, paramObj)) {
            if (Number.class.isAssignableFrom(field.getType())) {
                field.set(paramObj, Sequence.uniqueLong());
            }else if (field.getType().isAssignableFrom(String.class)) {
                field.set(paramObj, Sequence.uniqueLongHex());
            }
        }
    }

    @Override
    public IdType getType() {
        return IdType.ASSIGN_ID;
    }

    @Override
    protected void defaultGeneratorKey(Object paramObj) {
        MetaObject metaObject = SystemMetaObject.forObject(paramObj);
        Class<?> getterType = metaObject.getGetterType(DEFAULT_ID);
        if (metaObject.getValue(DEFAULT_ID) == null) {
            if (Number.class.isAssignableFrom(getterType)) {
                metaObject.setValue(DEFAULT_ID, Sequence.uniqueLong());
            }else if (getterType.isAssignableFrom(String.class)) {
                metaObject.setValue(DEFAULT_ID, Sequence.uniqueLongHex());
            }
        }

    }
}
