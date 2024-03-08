package com.MBD.CabBooking.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MBD.CabBooking.Entity.Admin;
import com.MBD.CabBooking.Exceptionhandling.ResourceNotFoundExcpetion;
import com.MBD.CabBooking.Repo.AdminRepo;
@Service
public class adminService {

	@Autowired
	 private AdminRepo adminRepo;
	
	
	public  Admin saveAdmin(Admin admin) {
		// TODO Auto-generated method stub
		
		return adminRepo.save(admin);
		
	}

	public  Admin delete(Integer adminId) {
		// TODO Auto-generated method stub
		
		Admin admin=adminRepo.findById(adminId).orElseThrow(()->new ResourceNotFoundExcpetion("admin", "Id", adminId));
		adminRepo.delete(admin);
		return admin;
	}

	public  Admin update(Admin admin) {
		// TODO Auto-generated method stub
		 return adminRepo.save(admin);
		 
	}

}
