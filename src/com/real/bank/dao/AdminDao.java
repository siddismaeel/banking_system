package com.real.bank.dao;

import com.real.bank.model.Admin;

public interface AdminDao {
	
	public Admin getAdmin(String userId);
	public Admin getAdmin(int adminId);
	public void updateAdmin(Admin admin);
	public void insertAdmin(Admin admin);

}
