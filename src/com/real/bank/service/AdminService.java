package com.real.bank.service;

import com.real.bank.model.Admin;

public interface AdminService {
	
	public Admin getAdmin(String userId);
	public Admin getAdmin(int adminId);
	public void updateAdmin(Admin admin);
	public void insertAdmin(Admin admin);

}
