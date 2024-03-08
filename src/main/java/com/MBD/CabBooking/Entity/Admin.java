package com.MBD.CabBooking.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@PrimaryKeyJoinColumn(name = "admin_id")
@Getter
@Setter
@NoArgsConstructor
public class Admin extends User {
    // Admin-specific fields
	
}