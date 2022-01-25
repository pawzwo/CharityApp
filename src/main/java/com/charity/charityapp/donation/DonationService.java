package com.charity.charityapp.donation;

import java.util.List;

public interface DonationService {


    void save(Donation donation);

    int countBags();

    int countDonations();

    List<Donation> showAllDonations();



}
