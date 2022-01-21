package com.charity.charityapp.institution;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE institutions SET enabled=1 WHERE id=?1", nativeQuery = true)
    void enableInstitution(long id);

    @Modifying
    @Transactional
    @Query (value = "UPDATE institutions SET enabled=1 WHERE id=?1", nativeQuery = true)
    void disableInstitution(long id);


}
