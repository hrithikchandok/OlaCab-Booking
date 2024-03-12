package com.MBD.CabBooking.Entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "driverId")
public class Driver extends User implements Serializable { 
    // You can add your fields here
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Min(value=0, message="License number should be more than or equal to 1")
//    private int driverId;
      //for cab Id 
//    private int driverId;
	
	@Min(value=1, message="id should be more than 1")
	@Column(unique = true)
    private int  licenseNo;
    private Double rating;
    private boolean available;
    
    @Min(value = 10, message = "price must be atleast 10 ")
    private int ratePerKm;
    
    
    @OneToOne( mappedBy = "driver", cascade = CascadeType.ALL)
    @JoinColumn(name = "cab_id")
    @JsonIgnore
    private Cab cab;
    
//    @JsonIgnore
//    @OneToOne
//    private Cab cab;
    
    
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "driver",orphanRemoval = true)
    @JsonIgnore
    private List<TripBooking> tripBookings;
    
    
    public boolean isAvailable() {
        return available;
    }
    
    
//   
       
    
    
    
   
	 
}
