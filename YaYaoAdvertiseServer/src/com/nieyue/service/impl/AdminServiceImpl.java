package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nieyue.bean.Admin;
import com.nieyue.dao.AdminDao;
import com.nieyue.service.AdminService;
@Service("adminService")
public class AdminServiceImpl implements AdminService{
	@Resource
	AdminDao adminDao;

	@Override
	public boolean addAdmin(Admin admin) {
		admin.setCreateDate(new Date());
		admin.setLastLoginDate(new Date());
		admin.setMoney("0");
		if(admin.getStatus()==null||admin.getStatus().equals("")){
			admin.setStatus("审核中");
		}
		boolean b = adminDao.addAdmin(admin);
		return b;
	}

	@Override
	public boolean delAdmin(Integer adminId) {
		boolean b = adminDao.delAdmin(adminId);
		return b;
	}

	@Override
	public boolean updateAdmin(Admin admin) {
		boolean b = adminDao.updateAdmin(admin);
		return b;
	}

	@Override
	public Admin loadAdmin(Integer adminId) {
		Admin r = adminDao.loadAdmin(adminId);
		return r;
	}

	@Override
	public int countAll() {
		int c = adminDao.countAll();
		return c;
	}

	@Override
	public List<Admin> browsePagingAdmin(int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Admin> l = adminDao.browsePagingAdmin(pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	@Override
	public Admin loginAdmin(String adminName, String password) {
		
		Admin a = adminDao.loginAdmin(adminName, password);
		a.setLastLoginDate(new Date());
		adminDao.updateAdmin(a);
		return a;
	}

	@Override
	public List<String> browseAllAdminPhone() {
		List<String> l = adminDao.browseAllAdminPhone();
		return l;
	}

	@Override
	public List<String> browseAllAdminEmail() {
		List<String> l = adminDao.browseAllAdminEmail();
		return l;
	}

	@Override
	public List<Admin> browseAllAdmin(String orderName, String orderWay) {
			List<Admin> l = adminDao.browseAllAdmin(orderName, orderWay);
			return l;
	}

	@Override
	public int countAllByRoleId(Integer roleId) {
		int c = adminDao.countAllByRoleId(roleId);
		return c;
	}

	@Override
	public List<Admin> browsePagingAdminByRoleId(Integer roleId, int pageNum,
			int pageSize, String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Admin> l = adminDao.browsePagingAdminByRoleId(roleId, pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
