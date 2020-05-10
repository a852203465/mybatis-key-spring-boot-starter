package cn.darkjrong.plugin.annotation;


/**
 * 生成ID类型枚举类
 * @author Rong.Jia
 * @date 2020/03/27 22:40
 */
public enum IdType {

	/**
	 * 数据库ID自增
	 */
	AUTO(0),

	/**
	 * 用户输入ID
	 * 该类型可以通过自己注册自动填充插件进行填充
	 */
	INPUT(1),

	/**
	 * 分配ID (主键类型为number或string）,
	 */
	ASSIGN_ID(2),

	/**
	 * 分配UUID (主键类型为 string)
	 */
	ASSIGN_UUID(3),

	;


	private final int key;

	IdType(int key) {
		this.key = key;
	}

	public int getKey() {
		return key;
	}
}
