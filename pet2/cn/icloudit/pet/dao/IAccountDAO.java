package cn.icloudit.pet.dao;

import java.sql.Connection;
import java.util.List;

import cn.icloudit.pet.entity.Account;

public interface IAccountDAO extends IBaseDAO {
	/**
	 * ��ѯȫ���̵�
	 * 
	 * @param con
	 *            ���ݿ�����
	 * @return ��ѯ�����Ľ����
	 */
	public List<Account> queryAll(Connection con);
}
