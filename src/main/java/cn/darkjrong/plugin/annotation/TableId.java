package cn.darkjrong.plugin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自动主键生成，配合mybatis Interceptor使用
 * @author Rong.Jia
 * @date 2020/03/27 22:40
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TableId {

    IdType value() default IdType.ASSIGN_ID;
}