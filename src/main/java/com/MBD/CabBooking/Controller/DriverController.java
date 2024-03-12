package com.MBD.CabBooking.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
import com.fasterxml.jackson.annotation.JacksonInject.Value;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/driver")
public class DriverController {
     
	@Autowired
	private DriverService driverService;
	
	@GetMapping("/{id}")
	@Cacheable(value = "driver",key = "#id")
	public Driver viewDriverById(@PathVariable("id") int id){
//		System.out.println("DB is called");
		Driver foundDriver= driverService.viewDriverById(id);
		return foundDriver;
//		return new ResponseEntity<Driver>(foundDriver,HttpStatus.ACCEPTED);
	} 
	
	@PostMapping("/")
	public ResponseEntity<Driver>  addDriver(@Valid @RequestBody Driver driver)
	{     Driver ans=driverService.save(driver);
		 return new ResponseEntity<>(ans,HttpStatus.CREATED);
//		return driver;
		 
	}
	
	@PutMapping("/{driver_id}")
	@CachePut(value = "driver",key = "#driver_id")
	public Driver  updateDriver(@PathVariable int driver_id ,@RequestParam int lic, @RequestParam Boolean avail)
	{        Driver ans=driverService.update(driver_id,lic,avail);
//		 return new ResponseEntity<>(ans,HttpStatus.CREATED);
	    return ans;
		 
	}
	
	@PutMapping("/avl")
	 @CacheEvict(value = "drivers", allEntries = true)
	public ResponseEntity<List<Driver>>  allavl()
	{        List<Driver> ans=driverService.makeAllAvl();
		 return new ResponseEntity<>(ans,HttpStatus.OK);
		 
	}
	@PutMapping("/unavl")
	 @CacheEvict(value = "drivers", allEntries = true)
	public ResponseEntity<List<Driver>>  allunavl()
	{        List<Driver> ans=driverService.makeAllUnAvl();
		 return new ResponseEntity<>(ans,HttpStatus.OK);
		 
	}
	
	@DeleteMapping("/{id}")
	@CacheEvict(value = "driver",key = "#id")
	public String deleteDriverById(@PathVariable("id") int id) {
		return driverService.deleteDriverById(id);
	}
	
	
	@GetMapping("/topDrivers")
	public ResponseEntity<List<Driver>> viewBestDrivers(){
		List<Driver> list=driverService.viewBestDriver();
		return new ResponseEntity<>(list,HttpStatus.ACCEPTED);
	}
	@GetMapping("")
//	@Cacheable(value = "drivers1", key="'drivers1'")
	public List<Driver> getallDriver(){
		List<Driver> list=driverService.allDriver();
		return list;
	}
}
