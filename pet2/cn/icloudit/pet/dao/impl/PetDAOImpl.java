package cn.icloudit.pet.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.icloudit.pet.dao.IPetDAO;
import cn.icloudit.pet.entity.Pet;
import cn.icloudit.pet.utils.DBHelper;

public class PetDAOImpl extends BaseDAO implements IPetDAO {

	/**
	 * 查询全部宠物 
	 * 
	 * @param con
	 *            数据库连接
	 * @return 查询出来的结果集
	 */
	public List<Pet> queryAll(Connection con) {
		String sql = "SELECT * FROM pet";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Pet> list = new ArrayList<Pet>();
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Pet p = new Pet();
				p.setBirthday(rs.getDate("birthday"));
				p.setHealth(rs.getInt("health"));
				p.setId(rs.getInt("id"));
				p.setLove(rs.getInt("love"));
				p.setName(rs.getString("name"));
				p.setStoreId(rs.getInt("store_id"));
				p.setOwnerId(rs.getInt("owner_id"));
				p.setTypeName(rs.getString("typename"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs, ps, null, null);
			sql = null;
			rs = null;
			ps = null;
		}
		return list;
	}

	/**
	 * 查询指定宠物
	 * 
	 * @param con
	 * @return
	 */
	public Pet queryById(Connection con, Integer id) {
		String sql = "SELECT * FROM pet WHERE id=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Pet p = null;
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				p = new Pet();
				p.setBirthday(rs.getDate("birthday"));
				p.setHealth(rs.getInt("health"));
				p.setId(rs.getInt("id"));
				p.setLove(rs.getInt("love"));
				p.setName(rs.getString("name"));
				p.setStoreId(rs.getInt("store_id"));
				p.setOwnerId(rs.getInt("owner_id"));
				p.setTypeName(rs.getString("typename"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs, ps, null, null);
			sql = null;
			rs = null;
			ps = null;
		}
		return p;
	}

	/**
	 * 查询指定主人的宠物
	 * 
	 * @param con
	 * @return
	 */
	public List<Pet> queryByOwner(Connection con, Integer ownerId) {
		String sql = "SELECT * FROM pet WHERE owner_id=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Pet> list = new ArrayList<Pet>();
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, ownerId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Pet p = new Pet();
				p.setBirthday(rs.getDate("birthday"));
				p.setHealth(rs.getInt("health"));
				p.setId(rs.getInt("id"));
				p.setLove(rs.getInt("love"));
				p.setName(rs.getString("name"));
				p.setStoreId(rs.getInt("store_id"));
				p.setOwnerId(rs.getInt("owner_id"));
				p.setTypeName(rs.getString("typename"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs, ps, null, null);
			sql = null;
			rs = null;
			ps = null;
		}
		return list;
	}

	/**
	 * 查询指定商店的宠物
	 * 
	 * @param con
	 * @return
	 */
	public List<Pet> queryByStore(Connection con, Integer storeId) {
		String sql = "SELECT * FROM pet WHERE store_id=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Pet> list = new ArrayList<Pet>();
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, storeId);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Pet p = new Pet();
				p.setBirthday(rs.getDate("birthday"));
				p.setHealth(rs.getInt("health"));
				p.setId(rs.getInt("id"));
				p.setLove(rs.getInt("love"));
				p.setName(rs.getString("name"));
				p.setStoreId(rs.getInt("store_id"));
				p.setOwnerId(rs.getInt("owner_id"));
				p.setTypeName(rs.getString("typename"));
				
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs, ps, null, null);
			sql = null;
			rs = null;
			ps = null;
		}
		return list;
	}

	/**
	 * 查询库存宠物
	 * 
	 * @param con
	 * @return
	 */
	public List<Pet> queryByKuCun(Connection con) {
		String sql = "SELECT * FROM pet WHERE typename in('dog','penguin')  AND owner_id IS NULL";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Pet> list = new ArrayList<Pet>();
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Pet p = new Pet();
				p.setBirthday(rs.getDate("birthday"));
				p.setHealth(rs.getInt("health"));
				p.setId(rs.getInt("id"));
				p.setLove(rs.getInt("love"));
				p.setName(rs.getString("name"));
				p.setStoreId(rs.getInt("store_id"));
				p.setOwnerId(rs.getInt("owner_id"));
				p.setTypeName(rs.getString("typename"));
				
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs, ps, null, null);
			sql = null;
			rs = null;
			ps = null;
		}
		return list;
	}

	/**
	 * 查询新培育宠物
	 * 
	 * @param con
	 * @return
	 */
	public List<Pet> queryByNew(Connection con) {
		String sql = "SELECT * FROM pet WHERE typename NOT IN('dog','penguin') AND owner_id IS NULL";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Pet> list = new ArrayList<Pet>();
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Pet p = new Pet();
				p.setBirthday(rs.getDate("birthday"));
				p.setHealth(rs.getInt("health"));
				p.setId(rs.getInt("id"));
				p.setLove(rs.getInt("love"));
				p.setName(rs.getString("name"));
				p.setStoreId(rs.getInt("store_id"));
				p.setOwnerId(rs.getInt("owner_id"));
				p.setTypeName(rs.getString("typename"));
				
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs, ps, null, null);
			sql = null;
			rs = null;
			ps = null;
		}
		return list;
	}
}
