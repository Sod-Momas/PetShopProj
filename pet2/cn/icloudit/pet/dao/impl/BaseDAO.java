package cn.icloudit.pet.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.icloudit.pet.utils.DBHelper;

public abstract class BaseDAO {
	/**
	 * ִ�в�ѯsql���
	 * 
	 * @param preparedSql
	 *            sql���
	 * @param param
	 *            prearedSql�����������
	 * @return Ӱ������
	 */
	public int executeSQL(String preparedSql, Object[] param) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int num = 0;

		/* ����SQL,ִ��SQL */
		try {
			conn = new DBHelper().getConnection(); // �õ����ݿ�����
			pstmt = conn.prepareStatement(preparedSql); // �õ�PreparedStatement����
			if (param != null) {// �����б�Ϊ��
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]); // ΪԤ����sql���ò���
				}
			} // ����ǿղ���,��ֱ��ִ�����

			num = pstmt.executeUpdate(); // ִ��SQL���,������Ӱ������
		} catch (SQLException e) {
			e.printStackTrace(); // ����SQLException�쳣
		} finally {// �ͷ���Դ
			DBHelper.close(null, pstmt, null, null);
		}
		return num;
	}
}
