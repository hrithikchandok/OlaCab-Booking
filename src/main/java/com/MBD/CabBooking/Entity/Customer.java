package com.MBD.CabBooking.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@PrimaryKeyJoinColumn(name = "customer_id")
@Getter
@Setter
@NoArgsConstructor
public class Customer extends User {
    // Customer-specific fields
	
	private String journey_status;
	
	
}