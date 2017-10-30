package cn.icloudit.pet.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.icloudit.pet.dao.IPetOwnerDAO;
import cn.icloudit.pet.entity.PetOwner;
import cn.icloudit.pet.utils.DBHelper;

public class PetOwnerDAOImpl extends BaseDAO implements IPetOwnerDAO{

	/**
	 * 查询全部主人
	 * @param con
	 * @return
	 */
	public List<PetOwner> queryAll(Connection con){
		String sql = "SELECT * FROM petOwner";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<PetOwner> list = new ArrayList<PetOwner>();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				PetOwner po = new PetOwner();
				po.setId(rs.getInt("id"));
				po.setMoney(rs.getDouble("money"));
				po.setName(rs.getString("name"));
				po.setPassword(rs.getString("password"));
				list.add(po);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBHelper.close(rs, ps, null, null);
			sql = null;
			rs = null;
			ps = null;
		}
		return list;
	}
	
	/**
	 * 查询条件查询
	 * @param con
	 * @return
	 * 
	 *   queryByTj(con,"id=?",new Object[]{1});
	 */
	public List<PetOwner> queryByTj(Connection con, String where, Object[] param){
		String sql = "SELECT * FROM petOwner ";
		if(where != null){
			sql += " WHERE " + where;
		}
		System.out.println(sql);
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<PetOwner> list = new ArrayList<PetOwner>();
		
		try {
			ps = con.prepareStatement(sql);
			
			if(where != null && param != null){
				for(int i = 0; i < param.length; i++){
					ps.setObject(i+1, param[i]);
				}
			}
			rs = ps.executeQuery();
			
			while(rs.next()){
				PetOwner po = new PetOwner();
				po.setId(rs.getInt("id"));
				po.setMoney(rs.getDouble("money"));
				po.setName(rs.getString("name"));
				po.setPassword(rs.getString("password"));
				list.add(po);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBHelper.close(rs, ps, null, null);
			sql = null;
			rs = null;
			ps = null;
		}
		return list;
	}
	
	
}
