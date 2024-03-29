package com.MBD.CabBooking.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.MBD.CabBooking.Entity.Customer;
import com.MBD.CabBooking.Entity.Driver;
import com.MBD.CabBooking.Entity.TripBooking;
import com.MBD.CabBooking.Exceptionhandling.ResourceNotFoundExcpetion;
import com.MBD.CabBooking.Repo.CabRepo;
import com.MBD.CabBooking.Repo.CustomerRepo;
import com.MBD.CabBooking.Repo.DriverRepo;
import com.MBD.CabBooking.Repo.TripRepo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;

@Service
public class TripService {
	@Autowired
   private TripRepo tripRepo;
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private DriverRepo driverRepo;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private CabRepo cabRepo;
////	
	@Autowired
	private DistanceCalculator distanceCalculator;
//	@Autowired
//	private RedisTemplate<String, Double> redisTemplate;
	
    @Transactional
	public TripBooking AddTrip(@Valid TripBooking trip) {
		
		   
		   Customer cus=customerRepo.findById(trip.getCustomerId()).orElseThrow(()->new ResourceNotFoundExcpetion("Customer","id", trip.getCustomerId()));
//		   
//		   List<Driver> drivers= driverRepo.findByAvailable() ;
		   
		   
		   // get all the drivers having cabtype same as the requested 
		   List<Driver>drivers=cabRepo.findDriversByCabType(trip.getCabType().toLowerCase());
		   
		   
		// Filter the list of drivers to get only the available ones
	        List<Driver> availableDrivers = drivers.stream()
	                                              .filter(Driver::isAvailable) // Assuming 'isAvailable' is a method in Driver entity
	                                              .collect(Collectors.toList());

		   
//		   Collections.sort(drivers, Comparator.comparing(obj->obj.getRatePerKm()).thenComparing(null));
		   Comparator<Driver> customComparator = Comparator.comparing(Driver::getRatePerKm)
                   .thenComparing(Driver::getRating, Comparator.reverseOrder());
		   
		   Collections.sort(availableDrivers, customComparator);

		   
		     if(availableDrivers.size()==0)
		     {
		    	  throw new ResourceNotFoundExcpetion("Sorry No driver Available just now...");
		     }
		     
		     //sorting the available driver based first based on the rateperKm and if the rates are same then compare of rating 
//		     availableDrivers.forEach(obj->System.out.println(obj.getRatePerKm()+"and "+obj.getRating()));
		     Driver selected_Driver=availableDrivers.get(0);
		     
//		       double distance=Distance(,);   
		       double distance=distanceCalculator.Distance(trip.getFrom_location(), trip.getTo_location());
		    		   trip.setTotalamount(selected_Driver.getRatePerKm()*(int)distance);	   
		        
		        trip.setKm((int)distance);
		        trip.setDriver(selected_Driver);
		        selected_Driver.setAvailable(false);
		        
		        String otp=generateOtp();
		        sendBookingConfirmationEmail(cus.getEmail(), trip, otp, cus);
//		        System.out.println(otp+"this is otp"+OTP);
		        
		        
//		        String bodyString = "Dear " + cus.getUsername() + ",\r\n"
//		        		+ "\r\n"
//		        		+ "Your cab booking has been confirmed.\r\n"
//		        		+ "\r\n"
//		        		+ "Booking Details:\r\n"
//		        		+ "- Pickup Location: " + trip.getFrom_location() + "\r\n"
//		        		+ "- Destination: " + trip.getTo_location() + "\r\n"
//		        		+ "- Date and Time: " + trip.getFromdate_time() + "\r\n"
//		        		+ "- Cab Type: " + trip.getCabType() + "\r\n"
//		        		+ "\r\n"
//		        		+ "Please use the following OTP to confirm your booking:\r\n"
//		        		+ "OTP: " + otp + "\r\n"
//		        		+ "\r\n"
//		        		+ "Thank you for choosing our cab service. We look forward to serving you!\r\n"
//		        		+ "\r\n"
//		        		+ "Best regards,\r\n"
//		        		+ "Your Company Name";
//		        
//		        emailService.emailSimpleMesaage(cus.getEmail(),"OlaCab Booking Confirmation 🚖 OTP🔑",bodyString );
		        
		        
		        
		        driverRepo.save(selected_Driver);
		        tripRepo.save(trip);
		       
		       
		        
		        		        		
		        		
		        return trip;
		        
		       
            

}
    @Async
    private void sendBookingConfirmationEmail(String email, TripBooking trip, String otp,Customer cus) {
        String bodyString = "Dear " + cus.getUsername()+ ",\r\n"
                            + "\r\n"
                            + "Your cab booking has been confirmed.\r\n"
                            + "\r\n"
                            + "Booking Details:\r\n"
                            + "- Pickup Location: " + trip.getFrom_location() + "\r\n"
                            + "- Destination: " + trip.getTo_location() + "\r\n"
                            + "- Date and Time: " + trip.getFromdate_time() + "\r\n"
                            + "- Cab Type: " + trip.getCabType() + "\r\n"
                            + "\r\n"
                            + "Please use the following OTP to confirm your booking:\r\n"
                            + "OTP: " + otp + "\r\n"
                            + "\r\n"
                            + "Thank you for choosing our cab service. We look forward to serving you!\r\n"
                            + "\r\n"
                            + "Best regards,\r\n"
                            + "Your Company Name";
        
        emailService.emailSimpleMesaage(email, "OlaCab Booking Confirmation 🚖 OTP🔑", bodyString);
    }
    @Async
    private String generateOtp() {
    	 StringBuilder OTP=new StringBuilder();
	        Random random=new Random();
	        
	        OTP.append((char)(random.nextInt(9)+'1'));
	        
	        for(int i=0;i<3;i++)
	        {
	        	OTP.append((char)(random.nextInt(10)+'0'));	 
	        }
	        return OTP.toString();
    }
   

	public List<TripBooking> alltrip() {
		// TODO Auto-generated method stub
		
		return tripRepo.findAll();
//		return null;
	}


	public TripBooking updateTrip(Integer id) {
		// TODO Auto-generated method stub
		
		
		TripBooking trip1= tripRepo.findById(id).orElseThrow(()->new ResourceNotFoundExcpetion("Trip","Id",id));
		trip1.setPayment(true);
		tripRepo.save(trip1);
		return trip1;
//		return null;
	}


	public String deletetrip(Integer id) {
		// TODO Auto-generated method stub
		
		
		TripBooking trip= tripRepo.findById(id).orElseThrow(()->new ResourceNotFoundExcpetion("Trip","Id",id));
	     Driver driver= trip.getDriver();
	     driver.setAvailable(true);
	     driverRepo.save(driver);
	     tripRepo.delete(trip);
		return "Trip got Canceled , Driver get freed";
	}


	public TripBooking pay(int tripId) {
		// TODO Auto-generated method stub
		
	 TripBooking tempBooking=	tripRepo.findById(tripId).orElseThrow(()->new ResourceNotFoundExcpetion("Trip","Id",tripId));
		tempBooking.setPayment(true);
		tripRepo.save(tempBooking);
		return tempBooking;
		
//		return null;
	}
}
