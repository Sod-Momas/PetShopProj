package cn.icloudit.pet.dao;

public interface IBaseDAO {
	/**
	 * ִ�в�ѯsql���
	 * 
	 * @param preparedSql
	 *            sql���
	 * @param param
	 *            prearedSql�����������
	 * @return Ӱ������
	 */
	public int executeSQL(String preparedSql, Object[] param);
}
