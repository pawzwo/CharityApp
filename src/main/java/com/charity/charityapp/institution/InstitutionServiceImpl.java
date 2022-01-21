package com.charity.charityapp.institution;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstitutionServiceImpl implements InstitutionService{

    private final InstitutionRepository institutionRepository;

    public InstitutionServiceImpl(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @Override
    public List<Institution> showAllInstitutions() {
        return institutionRepository.findAll();

    }

    @Override
    public void enableInstitution(long id) {
        institutionRepository.enableInstitution(id);

    }

    @Override
    public void disableInstitution(long id) {
        institutionRepository.disableInstitution(id);

    }
}
