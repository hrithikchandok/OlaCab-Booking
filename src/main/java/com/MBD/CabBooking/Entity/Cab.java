package com.MBD.CabBooking.Entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cab implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cabId;
    private String cabType;
    private String ratePerson;
    
    @OneToOne(fetch = FetchType.EAGER) 
     @JoinColumn(name="driver_id")
       private Driver driver;
}
