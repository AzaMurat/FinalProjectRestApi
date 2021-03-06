package peaksoft.finalprojectrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.finalprojectrestapi.model.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query("select case when count (g)>0 then true else false end from Group g where g.groupName = :name")
    Boolean existsByName(@Param("name") String groupName);
}




