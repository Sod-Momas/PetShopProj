package cn.icloudit.pet.dao;

import java.sql.Connection;
import java.util.List;

import cn.icloudit.pet.entity.PetStore;

public interface IPetStoreDAO extends IBaseDAO{
	/**
	 * ��ѯȫ���̵�
	 * @param con
	 * @return
	 */
	public List<PetStore> queryAll(Connection con);
	
	public List<PetStore> queryByTj(Connection con, String where, Object[] param) ;
}
