package cn.icloudit.pet.dao;

import java.sql.Connection;
import java.util.List;

import cn.icloudit.pet.entity.PetOwner;

public interface IPetOwnerDAO extends IBaseDAO {
	/**
	 * 查询全部主人
	 * @param con
	 * @return
	 */
	public List<PetOwner> queryAll(Connection con);
	
	/**
	 * 查询条件查询
	 * @param con
	 * @return
	 * 
	 *   queryByTj(con,"id=?",new Object[]{1});
	 */
	public List<PetOwner> queryByTj(Connection con, String where, Object[] param);
}
