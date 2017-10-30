package cn.icloudit.pet.dao;

public interface IBaseDAO {
	/**
	 * 执行查询sql语句
	 * 
	 * @param preparedSql
	 *            sql语句
	 * @param param
	 *            prearedSql里面所需参数
	 * @return 影响行数
	 */
	public int executeSQL(String preparedSql, Object[] param);
}
