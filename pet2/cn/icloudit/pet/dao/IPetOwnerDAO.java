package cn.icloudit.pet.dao;

import java.sql.Connection;
import java.util.List;

import cn.icloudit.pet.entity.PetOwner;

public interface IPetOwnerDAO extends IBaseDAO {
	/**
	 * ��ѯȫ������
	 * @param con
	 * @return
	 */
	public List<PetOwner> queryAll(Connection con);
	
	/**
	 * ��ѯ������ѯ
	 * @param con
	 * @return
	 * 
	 *   queryByTj(con,"id=?",new Object[]{1});
	 */
	public List<PetOwner> queryByTj(Connection con, String where, Object[] param);
}
