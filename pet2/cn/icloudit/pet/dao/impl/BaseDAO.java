package cn.icloudit.pet.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.icloudit.pet.utils.DBHelper;

public abstract class BaseDAO {
	/**
	 * 执行查询sql语句
	 * 
	 * @param preparedSql
	 *            sql语句
	 * @param param
	 *            prearedSql里面所需参数
	 * @return 影响行数
	 */
	public int executeSQL(String preparedSql, Object[] param) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int num = 0;

		/* 处理SQL,执行SQL */
		try {
			conn = new DBHelper().getConnection(); // 得到数据库连接
			pstmt = conn.prepareStatement(preparedSql); // 得到PreparedStatement对象
			if (param != null) {// 参数列表不为空
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]); // 为预编译sql设置参数
				}
			} // 如果是空参数,则直接执行语句

			num = pstmt.executeUpdate(); // 执行SQL语句,并返回影响行数
		} catch (SQLException e) {
			e.printStackTrace(); // 处理SQLException异常
		} finally {// 释放资源
			DBHelper.close(null, pstmt, null, null);
		}
		return num;
	}
}
