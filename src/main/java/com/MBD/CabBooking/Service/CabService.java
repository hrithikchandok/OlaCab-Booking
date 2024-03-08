package com.MBD.CabBooking.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.CustomSQLExceptionTranslatorRegistrar;
import org.springframework.stereotype.Service;

import com.MBD.CabBooking.Entity.Cab;
import com.MBD.CabBooking.Entity.Driver;
//import com.MBD.CabBooking.Exceptionhandling.CustomException;
import com.MBD.CabBooking.Exceptionhandling.OneToOneViolationException;
import com.MBD.CabBooking.Exceptionhandling.ResourceNotFoundExcpetion;
import com.MBD.CabBooking.Repo.CabRepo;
import com.MBD.CabBooking.Repo.DriverRepo;

import jakarta.validation.Valid;

@Service
public class CabService {
	
	
	@Autowired
	private CabRepo cabRepo;
	
	@Autowired
	private DriverRepo driverRepo;
	

	public Cab viewCabById(int id) {
		// TODO Auto-generated method stub
		return cabRepo.findById(id).orElseThrow(()->new ResourceNotFoundExcpetion("Cab", "Id", id));
	}

	public Cab update(int cab_id, String cab_type, String rate_person) {
		Cab ansCab=	cabRepo.findById(cab_id).orElseThrow(()->new ResourceNotFoundExcpetion("Cab", "Id", cab_id));
		ansCab.setCabType(cab_type.toLowerCase());
		ansCab.setRatePerson(rate_person);
	    
		cabRepo.save(ansCab);
	    return ansCab;
	}

	public Cab save(@Valid Cab cab, int driver_id) {
		// TODO Auto-generated method stub
//		return cab.getDriver()
		Driver driver= driverRepo.findById(driver_id).orElseThrow(()->new ResourceNotFoundExcpetion("Driver", "Id", driver_id));
		  if (driver.getCab() != null) {
//		        throw new CustomException("Driver already has a cab associated with it");
		        throw new OneToOneViolationException(String.format("Driver with id %s already has a cab associated with it", driver_id));
		    }
		  Cab cab1 = new Cab();
//		  cab1.setCabId(cab.getCabId());
		  cab1.setCabType(cab.getCabType().toLowerCase());
		  cab1.setDriver(driver);
		  cab1.setRatePerson(cab.getRatePerson());
		  
		  
//		 driver.setCab(cab); 
//		cab.setDriver(driver);
		cabRepo.save(cab1);
//		driverRepo.save(driver);
		
		 
	
		return cab1;
	}

	public String deleteCabById(int id) {
		// TODO Auto-generated method stub
		Cab ansCab=	cabRepo.findById(id).orElseThrow(()->new ResourceNotFoundExcpetion("Cab", "Id", id));
		 cabRepo.delete(ansCab);
		return "Cab deleted SuccessFully";
	}

	public List<Cab> allCab() {
		// TODO Auto-generated method stub
		
		return  cabRepo.findAll();
//		return null;
	}

}
