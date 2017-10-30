package cn.icloudit.pet.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.icloudit.pet.dao.IAccountDAO;
import cn.icloudit.pet.entity.Account;
import cn.icloudit.pet.utils.DBHelper;

public class AccountDAOImpl extends BaseDAO implements IAccountDAO {
	/**
	 * 查询全部商店
	 * 
	 * @param con
	 *            数据库连接
	 * @return 查询出来的结果集
	 */
	public List<Account> queryAll(Connection con) {
		String sql = "SELECT * FROM account";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Account> list = new ArrayList<Account>();

		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Account ac = new Account();
				ac.setBuyerId(rs.getInt("buyer_id"));
				ac.setDealTime(rs.getDate("deal_time"));
				ac.setDealType(rs.getInt("deal_type"));
				ac.setId(rs.getInt("id"));
				ac.setPetId(rs.getInt("pet_id"));
				ac.setPrice(rs.getDouble("price"));
				ac.setSellerId(rs.getInt("seller_id"));
				list.add(ac);
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
