package com.charity.charityapp.donation;

import org.apache.catalina.LifecycleState;

import java.util.List;

public interface DonationService {


    void save(Donation donation);

    int countBags();

    int countDonations();

    List<Donation> showAllDonations();



}
