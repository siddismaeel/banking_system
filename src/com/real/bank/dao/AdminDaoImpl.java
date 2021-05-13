package com.real.bank.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.real.bank.model.Admin;
import com.real.bank.util.DBConnectionProvider;

public class AdminDaoImpl implements AdminDao {
	private static final Logger LOGGER = Logger.getLogger(AdminDaoImpl.class);
	Connection con = null;
	@Override
	public Admin getAdmin(String userId) {
		LOGGER.info("begins getAdmin method");
		String sql = "select * from admin where user_id = ?";
		PreparedStatement st = null;
		Admin admin = null;
		try {
			con = DBConnectionProvider.getConntection();
			st = con.prepareStatement(sql);
			st.setString(1, userId);
			
			ResultSet rs = st.executeQuery();
			
			if(rs.next())
			{
				admin = new Admin();
				
				admin.setAdminId(rs.getInt(1));
				admin.setfName(rs.getString(2));
				admin.setlName(rs.getString(3));
				admin.setUserId(rs.getString(4));
				admin.setEmail(rs.getString(5));
				admin.setPassword(rs.getString(6));
				LOGGER.info("in getAdmin method admin retrived");
			}
			
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			LOGGER.info("exception in getAdmin method " + e);
		}
		
		LOGGER.info("ends getAdmin method");
		return admin;
	}

	@Override
	public Admin getAdmin(int adminId) {
		LOGGER.info("begins getAdmin method");
		
		String sql = "select * from admin where admin_id = ?";
		PreparedStatement st = null;
		Admin admin = null;
		try {
			con = DBConnectionProvider.getConntection();
			st = con.prepareStatement(sql);
			st.setInt(1, adminId);
			
			ResultSet rs = st.executeQuery();
			
			if(rs.next())
			{
				admin = new Admin();
				
				admin.setAdminId(rs.getInt(1));
				admin.setfName(rs.getString(2));
				admin.setlName(rs.getString(3));
				admin.setUserId(rs.getString(4));
				admin.setEmail(rs.getString(5));
				admin.setPassword(rs.getString(6));
				LOGGER.info("in getAdmin method admin retrived");
			}
			
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			LOGGER.info("exception in getAdmin method " + e);
		}
		LOGGER.info("ends getAdmin method");
		return admin;
	}

	@Override
	public void updateAdmin(Admin admin) {
		LOGGER.info("begins updateAdmin method");
		LOGGER.info("begins updateAdmin method");

	}

	@Override
	public void insertAdmin(Admin admin) {
		LOGGER.info("begins insertAdmin method");
		LOGGER.info("begins insertAdmin method");

	}

}
