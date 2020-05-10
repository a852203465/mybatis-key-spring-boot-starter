package cn.darkjrong.plugin.generator;

import cn.darkjrong.plugin.annotation.IdType;

import java.lang.reflect.Field;

/**
 * 策略模式：ID赋值操作
 * @author Rong.Jia
 * @date 2020/03/28 13:20
 */
public interface KeyGenerator {

	/**
	 * 主键赋值操作
	 */
	void process(Field field, Object paramObj) throws Exception;

	/**
	 * 返回主键策略类型
	 *
	 * @return  策略类型
	 */
	IdType getType();

}
