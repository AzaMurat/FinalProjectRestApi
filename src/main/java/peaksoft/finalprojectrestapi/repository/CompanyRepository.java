package peaksoft.finalprojectrestapi.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.finalprojectrestapi.model.Company;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {

    @Query("select c from Company c where c.email= : email1")
    Optional<Company> findByEmail(@Param("email1") String email);
}

