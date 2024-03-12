package com.MBD.CabBooking.Entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class TripArchives implements Serializable {
	 @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Integer TripBookingId;
	  
	   private int customerId;
	   
	   private String cabType;
	   
//	   @NotNull(message = "From Location cant be Null")\
	   @NotBlank(message = "From Location cant be Null")
	   private String From_location;
	   
//	   @NotNull(message = "To location cant be null")
	   @NotBlank(message = "To Location cant be Null")
	   private String To_location;
	   
	   
	    @FutureOrPresent(message = "{Please Eneter date of Present or Future}")
	    @NotNull(message = "{Date cant be Null}")
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
     private LocalDate Fromdate_time;
	    
	    
	    @FutureOrPresent(message = "{Please Eneter date of Present or Future}")
	    @NotNull(message = "{Date cant be Null}")
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
      private LocalDate Todate_time;
	   
     private Integer km;
     private Integer  Totalamount;
     private Boolean Payment;
     
     
     private LocalDateTime entryTime;
     
     @PrePersist
     protected void onCreate() {
  	    entryTime=LocalDateTime.now();
     }
     
     
     @ManyToOne()
     @JoinColumn(name="Driver_id", referencedColumnName = "driverId")
     private Driver driver;
}
