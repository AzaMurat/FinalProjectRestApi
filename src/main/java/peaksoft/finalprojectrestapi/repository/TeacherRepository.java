package peaksoft.finalprojectrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.finalprojectrestapi.model.Teacher;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, UUID> {

    @Query("select t from Teacher t where t.firstName = : name")
    Optional<Teacher> findByTeacherName(@Param("name") String name);

    @Query("select case when count (t)>0 then true else false end from Teacher t where t.firstName = :name")
    Boolean existsByName(@Param("name") String firstName);
}
