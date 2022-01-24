package com.charity.charityapp.donation;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationServiceImpl implements DonationService{

    DonationRepository donationRepository;

    public DonationServiceImpl(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    @Override
    public void save(Donation donation) {
        donationRepository.save(donation);
    }

    @Override
    public int countDonations() {
        return donationRepository.countDonations();
    }

    @Override
    public int countBags() {
        return donationRepository.countBags();
    }

    @Override
    public List<Donation> showAllDonations() {
        return donationRepository.findAll();
    }
}
