package cn.icloudit.pet.dao;

import java.sql.Connection;
import java.util.List;

import cn.icloudit.pet.entity.Pet;

public interface IPetDAO extends IBaseDAO {
	/**
	 * ��ѯȫ������ 
	 * 
	 * @param con
	 *            ���ݿ�����
	 * @return ��ѯ�����Ľ����
	 */
	public List<Pet> queryAll(Connection con);
	/**
	 * ��ѯָ������
	 * 
	 * @param con
	 * @return
	 */
	public Pet queryById(Connection con, Integer id);

	/**
	 * ��ѯָ�����˵ĳ���
	 * 
	 * @param con
	 * @return
	 */
	public List<Pet> queryByOwner(Connection con, Integer ownerId);

	/**
	 * ��ѯָ���̵�ĳ���
	 * 
	 * @param con
	 * @return
	 */
	public List<Pet> queryByStore(Connection con, Integer storeId);

	/**
	 * ��ѯ������
	 * 
	 * @param con
	 * @return
	 */
	public List<Pet> queryByKuCun(Connection con);

	/**
	 * ��ѯ����������
	 * 
	 * @param con
	 * @return
	 */
	public List<Pet> queryByNew(Connection con);
}
