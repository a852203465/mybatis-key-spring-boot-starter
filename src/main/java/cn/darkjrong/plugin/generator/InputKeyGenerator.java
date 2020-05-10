package cn.darkjrong.plugin.generator;

import cn.darkjrong.plugin.annotation.IdType;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.lang.reflect.Field;

/**
 *  自定义主键策略
 * @author Rong.Jia
 * @date 2020/03/28 13:20
 */
public class InputKeyGenerator extends AbstractKeyGenerator {

    @Override
    public void process(Field field, Object paramObj) throws Exception {
        if (field == null) {
            defaultGeneratorKey(paramObj);
            return;
        }
        if (super.checkField(field, paramObj)) {

        }
    }

    @Override
    public IdType getType() {
        return IdType.INPUT;
    }

    @Override
    protected void defaultGeneratorKey(Object paramObj) {
        MetaObject metaObject = SystemMetaObject.forObject(paramObj);
        Class<?> getterType = metaObject.getGetterType(DEFAULT_ID);
        if (metaObject.getValue(DEFAULT_ID) == null) {
        }
    }
}
