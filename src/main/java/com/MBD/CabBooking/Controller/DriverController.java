package com.MBD.CabBooking.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MBD.CabBooking.Entity.Driver;
import com.MBD.CabBooking.Service.DriverService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/driver")
public class DriverController {
     
	@Autowired
	private DriverService driverService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Driver> viewDriverById(@PathVariable("id") int id){
		Driver foundDriver= driverService.viewDriverById(id);
		return new ResponseEntity<Driver>(foundDriver,HttpStatus.ACCEPTED);
	} 
	
	@PostMapping("/")
	public ResponseEntity<Driver>  addDriver(@Valid @RequestBody Driver driver)
	{     Driver ans=driverService.save(driver);
		 return new ResponseEntity<>(ans,HttpStatus.CREATED);
//		return driver;
		 
	}
	
	@PutMapping("/{driver_id}")
	public ResponseEntity<Driver>  updateDriver(@PathVariable int driver_id ,@RequestParam int lic, @RequestParam Boolean avail)
	{        Driver ans=driverService.update(driver_id,lic,avail);
		 return new ResponseEntity<>(ans,HttpStatus.CREATED);
		 
	}
	
	@DeleteMapping("/{id}")
	public String deleteDriverById(@PathVariable("id") int id) {
		return driverService.deleteDriverById(id);
	}
	
	
	@GetMapping("/topDrivers")
	public ResponseEntity<List<Driver>> viewBestDrivers(){
		List<Driver> list=driverService.viewBestDriver();
		return new ResponseEntity<>(list,HttpStatus.ACCEPTED);
	}
	@GetMapping("")
	public ResponseEntity<List<Driver>> getallDriver(){
		List<Driver> list=driverService.allDriver();
		return new ResponseEntity<>(list,HttpStatus.ACCEPTED);
	}
}
