package com.MBD.CabBooking.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.MBD.CabBooking.Entity.Cab;
import com.MBD.CabBooking.Service.CabService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cab")
public class CabController {

	 
		@Autowired
		private CabService cabService;
		
		@GetMapping("/{id}")
		public ResponseEntity<Cab> cab_id(@PathVariable("id") int id){
			Cab foundCab= cabService.viewCabById(id);
			return new ResponseEntity<Cab>(foundCab,HttpStatus.ACCEPTED);
		} 
		
		@PostMapping("/driver/{driver_id}")
		public ResponseEntity<Cab>  add_cab(@Valid @RequestBody Cab cab,@PathVariable int driver_id)
		{     Cab ans=cabService.save(cab,driver_id);
			 return new ResponseEntity<>(ans,HttpStatus.CREATED);
//			return Cab;
			 
		}
		
		@PutMapping("/{cab_id}")
		public ResponseEntity<Cab>  update_cab(@PathVariable int cab_id ,@RequestParam String cab_type, @RequestParam String rate_person)
		{        Cab ans=cabService.update(cab_id,cab_type,rate_person);
			 return new ResponseEntity<>(ans,HttpStatus.CREATED);
			 
		}
		
		@DeleteMapping("/{id}")
		public String cab_del(@PathVariable("id") int id) {
			return cabService.deleteCabById(id);
		}
		
		
		
		@GetMapping("")
		public ResponseEntity<List<Cab>> cab_all(){
			List<Cab> list=cabService.allCab();
			return new ResponseEntity<>(list,HttpStatus.ACCEPTED);
		}
}
