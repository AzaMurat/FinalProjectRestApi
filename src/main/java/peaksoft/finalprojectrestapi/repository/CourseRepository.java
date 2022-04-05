package peaksoft.finalprojectrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.finalprojectrestapi.model.Course;

@Repository
public interface CourseRepository extends JpaRepository <Course, Long>{

    @Query("select case when count(c)>0 then true else false end " +
            "from Course c where c.courseName = ?1")
    boolean existsByName(String courseName);

}

