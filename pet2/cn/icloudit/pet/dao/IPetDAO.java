package cn.icloudit.pet.dao;

import java.sql.Connection;
import java.util.List;

import cn.icloudit.pet.entity.Pet;

public interface IPetDAO extends IBaseDAO {
	/**
	 * 查询全部宠物 
	 * 
	 * @param con
	 *            数据库连接
	 * @return 查询出来的结果集
	 */
	public List<Pet> queryAll(Connection con);
	/**
	 * 查询指定宠物
	 * 
	 * @param con
	 * @return
	 */
	public Pet queryById(Connection con, Integer id);

	/**
	 * 查询指定主人的宠物
	 * 
	 * @param con
	 * @return
	 */
	public List<Pet> queryByOwner(Connection con, Integer ownerId);

	/**
	 * 查询指定商店的宠物
	 * 
	 * @param con
	 * @return
	 */
	public List<Pet> queryByStore(Connection con, Integer storeId);

	/**
	 * 查询库存宠物
	 * 
	 * @param con
	 * @return
	 */
	public List<Pet> queryByKuCun(Connection con);

	/**
	 * 查询新培育宠物
	 * 
	 * @param con
	 * @return
	 */
	public List<Pet> queryByNew(Connection con);
}
