package com.charity.charityapp.donation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query (value = "SELECT COUNT(id) FROM donations", nativeQuery = true)
    int countDonations();

    @Query (value = "SELECT SUM(quantity) FROM donations", nativeQuery = true)
    int countBags();



}
