package com.MBD.CabBooking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MBD.CabBooking.Entity.Admin;
import com.MBD.CabBooking.Service.adminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
 
	@Autowired
	private adminService adminService1;
   
	@PostMapping("/")
	public ResponseEntity<Admin> insertAdminHandler(@RequestBody Admin admin) {
		Admin savedAdmin = adminService1.saveAdmin(admin);
		return new ResponseEntity<Admin>(savedAdmin,HttpStatus.OK);
	}
	
	
	
	@DeleteMapping("/{adminId}")
	public ResponseEntity<Admin> deleteMappingHandler(@PathVariable("adminId") Integer adminId) {
		Admin returnAdmin = adminService1.delete(adminId); 
		return new ResponseEntity<Admin>(returnAdmin,HttpStatus.OK);
	}
	
	@PutMapping("/")
	public ResponseEntity<String> updateAdminHandler(@RequestBody Admin admin) {
		Admin updatedAdmin = adminService1.update(admin);
		return new ResponseEntity<String>("admin updated "+updatedAdmin,HttpStatus.ACCEPTED); 
	}
}
