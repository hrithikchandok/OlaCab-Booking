package com.MBD.CabBooking.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MBD.CabBooking.Entity.TripArchives;

public interface TripArchiveRepo extends JpaRepository<TripArchives, Integer> {

}
