package com.MBD.CabBooking.Service;

import java.util.List;

import javax.lang.model.element.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MBD.CabBooking.Entity.Driver;
import com.MBD.CabBooking.Exceptionhandling.ResourceNotFoundExcpetion;
import com.MBD.CabBooking.Repo.DriverRepo;

import jakarta.validation.Valid;

@Service
public class DriverService {

	@Autowired
	private DriverRepo driverRepo;
	
	public Driver save(@Valid Driver driver) {
		// TODO Auto-generated method stub
		
		return driverRepo.save(driver);
		
//		return null;
	}

	public Driver update(int driver_id, int lic, Boolean avail) {
		// TODO Auto-generated method stub
		
	    Driver ansDriver=	driverRepo.findById(driver_id).orElseThrow(()->new ResourceNotFoundExcpetion("DRiver", "Id", driver_id));
	    ansDriver.setLicenseNo(lic);
	    ansDriver.setAvailable(avail);
	    
	    driverRepo.save(ansDriver);
	    return ansDriver;
//		return null;
	}

	public List<Driver> viewBestDriver() {
		// TODO Auto-generated method stub
		List<Driver> ansDrivers=driverRepo.findByRatingGreaterThanAndAvailableIsTrue();
		return ansDrivers;
	}

	public String deleteDriverById(Integer id) {
		// TODO Auto-generated method stub
//		driverRepo.findById(id).orElseThrow(()->new res) 
		Driver ansDriver=	driverRepo.findById(id).orElseThrow(()->new ResourceNotFoundExcpetion("DRiver", "Id", id));
		
		driverRepo.delete(ansDriver);
		return "Driver Deleted SuccessFully";
	}

	public Driver viewDriverById(int id) {
		// TODO Auto-generated method stub
		return driverRepo.findById(id).orElseThrow(()->new ResourceNotFoundExcpetion("DRiver", "Id", id));
		
	}

	public List<Driver> allDriver() {
		// TODO Auto-generated method stub
		return driverRepo.findAll();
//		return null;
	}

	public List<Driver> makeAllAvl() {
		// TODO Auto-generated method stub
	    List<Driver>tempDrivers=	driverRepo.findAll();
	    
	    for( Driver ele :tempDrivers)
	    {
	        ele.setAvailable(true);	
//	        driverRepo.save(ele);
	    }
	    
	    driverRepo.saveAll(tempDrivers);
	    
	    
	    
	    
	    
		return tempDrivers;
	}
	public List<Driver> makeAllUnAvl() {
		// TODO Auto-generated method stub
	    List<Driver>tempDrivers=	driverRepo.findAll();
	    
	    for( Driver ele :tempDrivers)
	    {
	        ele.setAvailable(false);	
//	        driverRepo.save(ele);
	    }
	    
	      driverRepo.saveAll(tempDrivers);
	    
	    
	    
	    
	    
		return tempDrivers;
	}

}
