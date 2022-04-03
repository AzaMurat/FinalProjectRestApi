package peaksoft.finalprojectrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.finalprojectrestapi.model.Course;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository <Course, UUID>{

    @Query("select c from Course c where c.courseName = : name")
    Optional<Course> findByCourseName(@Param("name")String name);

    @Query("select case when count(c)>0 then true else false end from Course c where c.courseName = :name")
    Boolean existsByName(@Param("name") String courseName);
}

