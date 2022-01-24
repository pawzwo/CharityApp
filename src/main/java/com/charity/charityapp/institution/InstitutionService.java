package com.charity.charityapp.institution;

import java.util.List;

public interface InstitutionService {

    List<Institution> showAllInstitutions();

    void enableInstitution(long id);

    void disableInstitution(long id);


}
