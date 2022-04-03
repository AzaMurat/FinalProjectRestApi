package peaksoft.finalprojectrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.finalprojectrestapi.model.Student;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

    @Query("select s from Student s where s.firstName = : name")
    Optional<Student> findByStudentName(@Param("name") String name);

    @Query("select case when count (s)>0 then true else false end from Student s where s.firstName = :name")
    Boolean existsByName(@Param("name") String studentName);
}
