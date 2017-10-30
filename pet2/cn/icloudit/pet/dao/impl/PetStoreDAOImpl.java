package cn.icloudit.pet.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.icloudit.pet.dao.IPetStoreDAO;
import cn.icloudit.pet.entity.PetStore;
import cn.icloudit.pet.utils.DBHelper;

public class PetStoreDAOImpl extends BaseDAO implements IPetStoreDAO{

	/**
	 * 查询全部商店
	 * @param con 
	 * @return 查询到的结果集
	 */
	public List<PetStore> queryAll(Connection con){
		String sql = "SELECT * FROM petStore";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<PetStore> list = new ArrayList<PetStore>();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				PetStore pst = new PetStore();
				pst.setBalance(rs.getDouble("balance"));
				pst.setId(rs.getInt("id"));
				pst.setName(rs.getString("name"));
				pst.setPassword(rs.getString("password"));
				list.add(pst);
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
	 * 根据条件查询结果集
	 */
	public List<PetStore> queryByTj(Connection con, String where, Object[] param) {
		String sql = "SELECT * FROM petStore ";
		if(where != null){
			sql += " WHERE " + where;
		}
		System.out.println(sql);
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<PetStore> list = new ArrayList<PetStore>();
		try {
			ps = con.prepareStatement(sql);
			
			if(param != null){
				for(int i = 0; i < param.length; i++){
					ps.setObject(i+1, param[i]);
				}
			}
			rs = ps.executeQuery();
			while(rs.next()){
				PetStore pst = new PetStore();
				pst.setBalance(rs.getDouble("balance"));
				pst.setId(rs.getInt("id"));
				pst.setName(rs.getString("name"));
				pst.setPassword(rs.getString("password"));
				list.add(pst);
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
